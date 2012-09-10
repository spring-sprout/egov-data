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
package egovframework.rte.tex.mbr.service;

/**
 * 일반회원관리에 관한 인터페이스클래스를 정의한다.
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
public interface EgovMemberService{
	/**
	 * 회원의 정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장한다.
	 * @param memberVO 회원정보
	 * @return String 메인페이지
	 * @throws Exception
	 */
	public String insertMember(MemberVO memberVO) throws Exception; 
	
	/**
	 * 화면에 조회된 회원정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영한다.
	 * @param memberVO 회원정보
	 * @throws Exception
	 */
	public void updateMember(MemberVO memberVO)  throws Exception;
	
	/**
	 * 선택된 회원정보를 데이터 베이스에서 삭제한다.
	 * @param memberVO 회원정보
	 * @throws Exception
	 */
	public void deleteMember(MemberVO memberVO) throws Exception;
	
	/**
	 * 입력된 정보로 데이터베이스에 접근하여 회원 여부를 확인하고, 회원일 경우 로그인 후 메인화면으로 이동한다.
	 * @param memberVO 회원정보
	 * @return MemberVO 회원정보
	 */
	public MemberVO loginMember(MemberVO memberVO) throws Exception;

	/**
	 * 회원이 비밀번호를 기억하지 못할 때 가입시 입력한 이메일로 비밀번호를 전송하는 것으로 입력한 아이디와 이메일을 데이터베이스에서 확인하고 회원일경우 메일로 비밀번호를 전송한다.
	 * 전송 후에는 로그인페이지를 출력한다. 
	 * @param vo 회원정보
	 * @return 메일 전송 확인 페이지
	 * @throws Exception
	 */
	public boolean searchPassword(MemberVO vo) throws Exception;
	
    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인한다.
     * @param checkId 사용자 아이디
     * @return int 중복여부
     * @throws Exception
     */
    public int checkIdDplct(String checkId) throws Exception;
	
}
