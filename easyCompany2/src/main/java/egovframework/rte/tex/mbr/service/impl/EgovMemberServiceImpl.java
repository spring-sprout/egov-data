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
package egovframework.rte.tex.mbr.service.impl;


import javax.annotation.Resource;

import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.tex.com.service.EgovMailService;
import egovframework.rte.tex.com.service.EgovNumberUtil;
import egovframework.rte.tex.com.service.EgovStringUtil;
import egovframework.rte.tex.mbr.service.EgovMemberService;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 회원관리에 관한 비지니스클래스를 정의한다.
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
@Service("memberService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class EgovMemberServiceImpl extends AbstractServiceImpl implements EgovMemberService{

	/** MemberDAO */
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;

    /** ID Generation */
    @Resource(name="egovIdGnrServiceMbr")    
    private EgovIdGnrService egovIdGnrService;
    
    /** MailService */
    @Resource(name="mailService")
    private EgovMailService mailService;
    
    
    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인한다.
     * @param checkId 중복여부를 체크할 회원ID
     * @return int 중복여부
     */
    public int checkIdDplct(String checkId) {
        return memberDAO.checkIdDplct(checkId);
    }
    
	/**
	 * 회원의 정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장한다.
	 * @param memberVO 회원정보
	 * @return String 회원번호
	 * @throws FdlException 
	 */
	public String insertMember(MemberVO memberVO) throws Exception {
		log.debug(memberVO.toString());
		
		/** ID Generation Service */
    	String no = egovIdGnrService.getNextStringId();
    	memberVO.setMemberNo(no);
    	
    	// 패스워드 암호화
    	PasswordEncoder encoder = new Md5PasswordEncoder();
  	    String hashedPass = encoder.encodePassword(memberVO.getPassword(), null);

  	    memberVO.setPassword(hashedPass);
    	log.debug(memberVO.toString());
    	
		memberDAO.insertMember(memberVO);
		return no;
	}
	
	/**
	 * 화면에 조회된 회원정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영한다.
	 * @param memberVO 회원정보
	 * @throws Exception
	 */
	public void updateMember(MemberVO memberVO) throws Exception{
		  // 패스워드 암호화
        PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword(memberVO.getPassword(), null);
	    memberVO.setPassword(hashedPass);
	    
		memberDAO.updateMember(memberVO);
	}

	/**
	 * 선택된 회원정보를 데이터 베이스에서 삭제한다.
	 * @param memberVO 회원정보
	 */
	public void deleteMember(MemberVO memberVO) {
		memberDAO.deleteMember(memberVO);
	}
	
	/**
	 * 입력된 정보로 데이터베이스에 접근하여 회원 여부를 확인하고, 회원일 경우 로그인 후 메인화면으로 이동한다.
	 * @param memberVO 회원정보
	 * @return MemberVO 회원번호
	 * @throws Exception
	 */
	public MemberVO loginMember(MemberVO memberVO) throws Exception {
		
		// 1. 입력한 비밀번호를 암호화한다.
		PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword(memberVO.getPassword(), null);
	    memberVO.setPassword(hashedPass);

        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("PASSWD - ENC : [" + hashedPass + "]");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        
        MemberVO loginVO = memberDAO.checkMember(memberVO);
        
        if (loginVO != null && !loginVO.getId().equals("")
                && !loginVO.getPassword().equals("")) {
                return loginVO;
            } else {
                loginVO = new MemberVO();
          }
		return loginVO;
	}
	
	/**
	 * 회원이 비밀번호를 기억하지 못할 때 가입시 입력한 이메일로 비밀번호를 전송하는 것으로 입력한 아이디와 이메일을 데이터베이스에서 확인하고 회원일경우 메일로 비밀번호를 전송한다.
	 * 전송 후에는 로그인페이지를 출력한다. 
	 * @param vo 회원정보
	 * @return boolean 메일 전송 여부
	 */
	public boolean searchPassword(MemberVO vo) {
		//1. 사용자의 id와 email주소 일치 여부 조회
		MemberVO loginVO= memberDAO.checkEmail(vo);
		 if(loginVO == null || loginVO.getEmail() == null || loginVO.getEmail().equals("")){
			 return false;
		 }
		 
		 // 2. 임시 비밀번호 생성(영+영+숫+영+영+숫=6자리)하고 암호화하여 저장
	        String newpassword = "";

	        for (int i = 1; i <= 6; i++) {
	            // 영자
	            if (i % 3 != 0) {
	                newpassword += EgovStringUtil.getRandomStr('a', 'z');
	                // 숫자
	            } else {
	                newpassword += EgovNumberUtil.getRandomNum(0, 9);
	            }
	        }

	        //3. 메일발송
	        PasswordEncoder encoder = new Md5PasswordEncoder();
		    String hashedPass = encoder.encodePassword(newpassword, null);

		    return mailService.sendEmailTo(loginVO);
	}
	
}
