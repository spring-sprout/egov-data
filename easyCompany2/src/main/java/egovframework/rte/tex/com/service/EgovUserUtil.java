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
package egovframework.rte.tex.com.service;

import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 회원 데이터 처리 관련 유틸리티를 정의한다.
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
public class EgovUserUtil {

	/**
	 * 현재 로그인 상태인 회원의 정보를 반환한다. 로그인 상태가 아니면 null값을 반환한다.
	 * @return memberVO 회원 정보
	 */
	public static MemberVO getMemberInfo(){
		if (EgovUserDetailsHelper.isAuthenticated()) {
			return (MemberVO)EgovUserDetailsHelper.getAuthenticatedUser();
		} else {
		    return null;
		}
	}
	
}
