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
package egovframework.rte.tex.cgr.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.tex.cgr.service.CategoryVO;
import egovframework.rte.tex.cgr.service.EgovCategoryService;
import egovframework.rte.tex.com.service.EgovUserUtil;
import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 카테고리 정보를 관리하는 컨트롤러 클래스를 정의한다.
 * 
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
@SessionAttributes(types=CategoryVO.class)
public class EgovCategoryController {

	/**CategoryService */
	@Resource(name="categoryService")
	private EgovCategoryService categoryService; 
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
    
	/**
	 * 카테고리 목록을 출력한다.
	 * @param request
	 * @param model
	 * @return "cgr/egovCategoryList"
	 * @throws Exception
	 */
	@RequestMapping(value="/springrest/cgr", method=RequestMethod.GET)
	public String selectCategoryList(HttpServletRequest request, Model model)
			throws Exception {
		List categoryList = categoryService.selectCategoryList();
		
		List category1 = new ArrayList();
		
		for( int i=categoryList.size()-1; i >=0; i--){
			category1.add(categoryList.get(i));
		}
		
		
		model.addAttribute("categoryList", category1);
		
		MemberVO loginVO=EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);
		

		if("popup".equals(request.getParameter("name"))) return "cgr/EgovCategoryPopup";
		else 
			return "cgr/egovCategoryList";
	}
	
    /**
     * 카테고리 등록 화면으로 이동한다.
     * @param model
     * @return "cgr/egovCategoryRegister"
     * @throws Exception
     */
    @RequestMapping(value="/springrest/cgr/new", method=RequestMethod.GET)
    public String insertCategoryView(Model model) throws Exception {
    	model.addAttribute("categoryVO", new CategoryVO());
    	
		MemberVO loginVO=EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);
		
    	return "cgr/egovCategoryRegister";
    }
    
	/**
	 * 카테고리 정보 저장 후 목록조회 화면으로 이동한다.
	 * @param categoryVO 카테고리 정보
	 * @param results validation결과
	 * @param session 세션 정보
	 * @param model
	 * @return "redirect:/springrest/cgr.html"
	 * @throws Exception
	 */
	@RequestMapping(value="/springrest/cgr", method = RequestMethod.POST, headers = "Content-type=application/x-www-form-urlencoded")
	public String create(@Valid CategoryVO categoryVO, BindingResult results,
			HttpSession session, Model model) throws Exception {
		if (results.hasErrors()) {
			return "cgr/egovCategoryRegister";
		}

		categoryService.insertCategory(categoryVO);

		return "redirect:/springrest/cgr.html";
	}
	
	/**
	 * 카테고리 수정 화면으로 이동한다.
	 * @param ctgryId 카테고리ID
	 * @param model
	 * @return "cgr/egovCategoryRegister"
	 * @throws Exception
	 */
	@RequestMapping(value="/springrest/cgr/{ctgryId}", method = RequestMethod.GET)
	public String updtCategoryView(@PathVariable String ctgryId,	 Model model)
			throws Exception {
		CategoryVO vo = new CategoryVO();
		vo.setCtgryId(ctgryId);
		model.addAttribute(categoryService.getCategory(vo));
		
		MemberVO loginVO=EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);
		
		return "cgr/egovCategoryRegister";
	}

	/**
	 * 카데고리 정보를 수정 후 목록조회 화면으로 이동한다.
	 * @param updateCategory 카테고리 정보
	 * @param results validation결과
	 * @param model
	 * @return "redirect:/springrest/cgr.html",  Response Status : 200 OK
	 * @throws Exception
	 */
	@RequestMapping(value = "/springrest/cgr/{ctgryId}", method = RequestMethod.PUT, headers = "Content-type=application/x-www-form-urlencoded")
	public String update(@Valid CategoryVO updateCategory, BindingResult results,
			Model model) throws Exception {
		if (results.hasErrors()) {
			return "cgr/egovCategoryRegister";
		}
		categoryService.updateCategory(updateCategory);
		
		return "redirect:/springrest/cgr.html";
	}
	
	/**
	 * 카테고리 정보 삭제 후 목록조회 화면으로 이동한다.
	 * @param ctgryId 카테고리ID 리스트
	 * @param status 세션 상태
	 * @param model
	 * @return "redirect:/springrest/cgr.html"
	 */
	@RequestMapping(value = "/springrest/cgr/{ctgryId}", method=RequestMethod.DELETE)
	public String deleteCategory(@PathVariable String ctgryId, SessionStatus status, Model model){
		CategoryVO vo = new CategoryVO();
		vo.setCtgryId(ctgryId);
		try {
			categoryService.deleteCategory(vo);
			status.setComplete();
			return "redirect:/springrest/cgr.html";
		} catch (Exception e) {
			return "cgr/EgovCategoryNotDeletable";
		}
	}
}
