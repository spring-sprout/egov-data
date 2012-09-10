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
package egovframework.rte.tex.mbr.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.tex.com.service.EgovUserUtil;
import egovframework.rte.tex.mbr.service.EgovMemberService;
import egovframework.rte.tex.mbr.service.MemberVO;
/**
 * 회원 정보를 관리하는 컨트롤러 클래스를 정의한다.
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
@SessionAttributes(types=MemberVO.class)
public class EgovMemberController {

	/**MemberService */
	@Resource(name="memberService")
	private EgovMemberService memberService;
	
    
	/**
	 * 비밀번호 찾기 화면으로 이동한다.
	 * @return "mbr/egovSearchPassword"
	 * @throws Exception
	 */
    @RequestMapping("/mbr/searchPasswordView.do")
	public String searchPassword() throws Exception{
		return "mbr/egovSearchPassword";
	}
    
	/**
	 * 비밀번호를 메일로 전송한 후 메인화면으로 이동한다.
	 * @param id ID
	 * @param email 이메일
	 * @param model
	 * @return "mbr/egovResult"
	 * @throws Exception
	 */
    @RequestMapping("/mbr/sendEmail.do")
	public String sendEmail(String id, String email, Model model) throws Exception{
    	MemberVO memberVO=new MemberVO();
    	memberVO.setId(id);
    	memberVO.setEmail(email);
    	boolean result= memberService.searchPassword(memberVO);
    	 if (result) {
             model.addAttribute("resultMsg", "sendEmailSuccess");
 			return "mbr/egovResult";
 			
         } else {
             model.addAttribute("resultMsg", "sendEmailFail");
 			return "mbr/egovResult";
         }
	}
	
    /**
     * 로그인 화면으로 이동한다. 이미 로그인 되어 있을 경우, 메인페이지로 이동한다.
     * @param request
     * @param model
     * @return "mbr/egovLogin"
     * @throws Exception
     */
    @RequestMapping("/mbr/loginView.do")
    public String loginView(HttpServletRequest request, Model model) throws Exception{
    	MemberVO vo = EgovUserUtil.getMemberInfo();
    	if(vo != null){
    		return "main/EgovMain";
    	}else{
    		model.addAttribute("login_error", request.getParameter("login_error"));
    		return "mbr/egovLogin";
    	}
    }

    /**
     * 로그인 후 메인 화면으로 이동한다. 
     * @param request
     * @param model
     * @return "main/EgovMain"
     * @throws Exception
     */
    @RequestMapping(value = "/mbr/actionMain.do")
    public String actionMain(HttpServletRequest request, Model model)
            throws Exception {
        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        MemberVO memberVO=EgovUserUtil.getMemberInfo();
        if (!isAuthenticated) {
            return "mbr/egovLogin";
        }else{
        	model.addAttribute("loginVO", memberVO);
        	return "main/EgovMain";
        }
    }
    
	/**
	 * 회원가입 화면으로 이동한다.
	 * @param model
	 * @return "mbr/egovMemberRegister"
	 * @throws Exception
	 */
	@RequestMapping("/mbr/insertMemberView.do")
	public String sbscrhMember(Model model) throws Exception{
		model.addAttribute("memberVO", new MemberVO());
		return "mbr/egovMemberRegister";
	}
    
	/**
	 * 회원정보를 등록한 후 결과화면으로 이동한다.
	 * @param memberVO 회원정보
	 * @param bindingResult validation결과
	 * @param model
	 * @param status 세션상태
	 * @return "mbr/egovResult"
	 * @throws Exception
	 */
	@RequestMapping("/mbr/insertMember.do")
	public String insertMember(@ModelAttribute("memberVO") @Valid MemberVO memberVO,  
			BindingResult bindingResult, Model model,
			SessionStatus status) throws Exception{

		if (bindingResult.hasErrors()) {
			model.addAttribute("memberVO", memberVO);
			return "mbr/egovMemberRegister";
		}

		int count = memberService.checkIdDplct(memberVO.getId());
    	
    	if(count == 1){
			model.addAttribute("resultMsg", "idDpl");
			return "mbr/egovMemberRegister";
    	}else{
    		memberService.insertMember(memberVO);
			status.setComplete();
			model.addAttribute("resultMsg", "insertSuccess");
			return "mbr/egovResult";
    	}
	}
	
	/**
	 * 회원정보 수정화면으로 이동한다.
	 * @param model
	 * @return "mbr/egovMemberRegister"
	 * @throws Exception
	 */
	@RequestMapping("/mbr/updateMemberView.do")
	public String updateMemberView(Model model) throws Exception{
		MemberVO memberVO = EgovUserUtil.getMemberInfo();
		model.addAttribute("memberVO", memberVO);
		return "mbr/egovMemberRegister";
	}
	
	/**
	 * 회원정보의 정합성을 체크한 후 수정하고 메인화면으로 이동한다.
	 * @param memberVO 회원정보
	 * @param bindingResult validation결과
	 * @param model
	 * @param status 세션상태
	 * @return "mbr/egovResult"
	 * @throws Exception
	 */
	@RequestMapping("/mbr/updateMember.do")
	public String updateMember(@ModelAttribute("memberVO") @Valid MemberVO memberVO, BindingResult bindingResult, Model model,
			SessionStatus status) throws Exception{
		
	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "mbr/egovMemberRegister"; 
        }

		if (bindingResult.hasErrors()) {
			return "mbr/egovMemberRegister";
		}
		
		memberService.updateMember(memberVO);
		status.setComplete();
		model.addAttribute("loginVO", memberVO);
		
		model.addAttribute("resultMsg", "updateSuccess");
		return "mbr/egovResult";

	}
	
	/**
	 * 회원삭제 후 메인화면으로 이동한다.
	 * @param memberVO 회원정보
	 * @param status 세션상태
	 * @return "redirect:/j_spring_security_logout"
	 * @throws Exception
	 */
	@RequestMapping("/mbr/deleteMember.do")
	public String deleteMember(MemberVO memberVO, SessionStatus status) throws Exception{
		memberService.deleteMember(memberVO);
		status.setComplete();
		 return "redirect:/j_spring_security_logout";
	}
	
}
