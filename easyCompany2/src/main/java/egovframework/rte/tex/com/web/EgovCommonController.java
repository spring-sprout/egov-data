/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.rte.tex.com.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import egovframework.rte.tex.com.service.EgovUserUtil;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 공통 기능을 관리하는 컨트롤러 클래스를 정의한다.
 * @author 실행환경 개발팀 신혜연
 * @since 2011.06.07
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.07  신혜연          최초 생성
 * 
 * </pre>
 */
@Controller
public class EgovCommonController {

	/** LocaleResolver */
	@Resource
	LocaleResolver localeResolver;
	
    /**
     * 권한이 없는 사용자가 접근시 권한제한 화면으로 이동한다.
     * @return "cmmn/EgovAccessDenie"
     * @exception Exception
     */
    @RequestMapping("/com/accessDenied.do")
    public String accessDenied() throws Exception {
        return "cmmn/EgovAccessDenied";
    }
    

    /**
     * 국제화 적용 페이지로 이동한다.
     * @return "main/EgovMain"
     * @exception Exception
     */
	@RequestMapping(value="/com/egovMain.do", method=RequestMethod.GET)
	protected String changeLocale(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Locale locale=new Locale("kr"); //default = kr
		if(request.getParameter("locale") == null){
			locale = localeResolver.resolveLocale(request);
		}
		else{
			locale = new Locale(request.getParameter("locale"));
		}
		// localeResolver에 locale 셋팅
		localeResolver.setLocale(request, response, locale);
		
		MemberVO loginVO = EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);
		
		return "main/EgovMain";
	}
	
	/**
	 * AJAX기능을 jQuery를 이용하여 탭형식으로 나타낸다.
	 * @param tabName 각 탭명
	 * @param request
	 * @return 메인페이지
	 * @throws Exception
	 */
	@Resource ResourceBundleMessageSource messageSource;
	@Resource MappingJacksonJsonView ajaxMainView;
	@RequestMapping(value = "/com/ajax.do")
	protected ModelAndView egovMain(String tabName, HttpServletRequest request) throws Exception {

		Locale locale =  localeResolver.resolveLocale(request); //language 
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("divId", "."+tabName);
		model.put("description", messageSource.getMessage(tabName, null, locale));

		return new ModelAndView(ajaxMainView, model);
	}
}
