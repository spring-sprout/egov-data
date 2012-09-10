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

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 회원관리에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("memberDAO")
public class MemberDAO extends EgovAbstractDAO{
	
	/**
	 * 회원의 정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장한다.
	 * @param memberVO 회원정보
	 */
	public void insertMember(MemberVO memberVO){
		insert("memberDAO.insertMember", memberVO);
	}
	
	/**
	 * 화면에 조회된 회원정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영한다.
	 * @param memberVO 회원정보
	 */
	public void updateMember(MemberVO memberVO){
		update("memberDAO.updateMember", memberVO);
	}
	
	/**
	 * 선택된 회원정보를 데이터 베이스에서 삭제한다.
	 * @param memberVO 회원정보
	 */
	public void deleteMember(MemberVO memberVO){
		delete("memberDAO.deleteMember", memberVO);
	}
	
	/**
	 * 입력된 정보로 데이터베이스에 접근하여 회원 여부를 확인하고, 
	 * 회원일 경우 회원의 정보를 가져온다.
	 * @param vo 회원정보
	 * @return MemberVO 회원정보
	 */
	public MemberVO checkMember(MemberVO vo){
		
		return (MemberVO)selectByPk("memberDAO.getMember", vo);
	}
	
	/**
	 * 입력된 정보로 데이터 베이스에 접근하여 회원의 이메일주소와 아이디를 확인하고,
	 * 동일할 경우 회원의 정보를 가져온다.
	 * @param vo 회원정보
	 * @return MemberVO 회원정보
	 */
	public MemberVO checkEmail(MemberVO vo){
		getSqlMapClientTemplate().queryForObject("memberDAO.searchPassword", vo);
		
		return (MemberVO)getSqlMapClientTemplate().queryForObject("memberDAO.searchPassword", vo);
	}

	/**
	 * 입력된 정보로 데이터 베이스에 접근하여 회원의 비밀번호를 변경한다.
	 * @param vo 회원정보
	 */
	public void changPassword(MemberVO vo){
		update("memberDAO.changePassword", vo);
	}
	
    /**
     * 입력한 사용자아이디의 중복여부를 데이터 베이스를 통해 체크하여 사용가능여부를 확인한다.
     * @param checkId 사용자 아이디
     * @return int 사용여부
     */
    public int checkIdDplct(String checkId) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "memberDAO.checkIdDplct", checkId);
    }
}
