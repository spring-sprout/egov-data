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
package egovframework.rte.tex.com.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import egovframework.rte.fdl.security.userdetails.EgovUserDetails;
import egovframework.rte.fdl.security.userdetails.jdbc.EgovUsersByUsernameMapping;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 사용자 정보를 매핑하는 클래스를 정의한다.
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
public class EgovSessionMapping extends EgovUsersByUsernameMapping {
	
	/**
	 * 사용자정보를 테이블에서 조회하여 EgovUsersByUsernameMapping 에 매핑한다.
	 * @param ds DataSource
	 * @param usersByUsernameQuery String
	 */
	public EgovSessionMapping(DataSource ds, String usersByUsernameQuery) {
        super(ds, usersByUsernameQuery);
    }

	/**
	 * mapRow Override
	 * @param rs ResultSet 결과
	 * @param rownum row num
	 * @return Object EgovUserDetails
	 * @exception SQLException
	 */
	@Override
    protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
    	logger.debug("## EgovUsersByUsernameMapping mapRow ##");

        boolean strEnabled  = rs.getBoolean("ENABLED");

        String strUsrNo = rs.getString("USER_NO");
        String strUsrId = rs.getString("USER_ID");
        String strUsrNm    = rs.getString("USER_NM");
        String strPassword = rs.getString("PASSWORD");
        String strUsrEmail = rs.getString("USER_EMAIL");
        String strTelNo = rs.getString("TEL_NO");
        String strMobileNo = rs.getString("MOBLPHON_NO");
        String strUsrZip = rs.getString("USER_ZIP");
        String strUsrAdres = rs.getString("USER_ADRES");
        String strDetailAdres = rs.getString("USER_DETAIL_ADRES");
        String strAuthorCode= rs.getString("AUTHOR_CODE");

        // 세션 항목 설정
        MemberVO loginVO = new MemberVO();

        loginVO.setMemberNo(strUsrNo);
        loginVO.setName(strUsrNm);
        loginVO.setId(strUsrId);
        loginVO.setPassword(strPassword);
        loginVO.setEmail(strUsrEmail);
        loginVO.setTelno(strTelNo);
        loginVO.setMobile(strMobileNo);
        loginVO.setZip(strUsrZip);
        loginVO.setAdres(strUsrAdres);
        loginVO.setDetailAdres(strDetailAdres);
        loginVO.setMngrSe(strAuthorCode);
        
        return  new EgovUserDetails(strUsrId, strPassword, strEnabled, loginVO);
        
    }
}
