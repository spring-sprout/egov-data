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
package egovframework.rte.tex.brd.service;

import java.util.Date;

import egovframework.rte.tex.com.service.SearchVO;

/**
 * 게시판 정보를 저장하는 객체를 정의한다.
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
public class BoardVO extends SearchVO{
	  
	private static final long serialVersionUID = -9469597699265656L;
	/** 글번호 */
	private String bbscttNo;
	/** 제목 */
	private String title;
	/** 내용 */
	private String contents;
	/** 등록일 */
	private Date rgsde;
	/** 회원ID */
	private String id;
	/** 회원 번호 */
	private String memberNo;
	
	/**
	 * 글번호를 가져온다.
	 * @return bbsctt_no 글번호
	 */
	public String getBbscttNo() {
		return bbscttNo;
	}
	/**
	 * 글번호를 입력한다.
	 * @param bbsctt_no 글번호
	 */
	public void setBbscttNo(String bbscttNo) {
		this.bbscttNo = bbscttNo;
	}
	/**
	 * 제목을 가져온다.
	 * @return title 제목
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 제목을 입력한다.
	 * @param title 제목
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 내용을 가져온다.
	 * @return contents 내용
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * 내용을 입력한다.
	 * @param contents 내용
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * 등록일을 가져온다.
	 * @return rgsde 등록일
	 */
	public Date getRgsde() {
		return rgsde;
	}
	/**
	 * 등록일을 입력한다.
	 * @param rgsde 등록일
	 */
	public void setRgsde(Date rgsde) {
		this.rgsde = rgsde;
	}
	/**
	 * 회원아이디를 가져온다.
	 * @retur String 회원 아이디 
	 */
	public String getId() {
		return id;
	}
	/**
	 * 회원아이디를 입력한다.
	 * @param id 회원 아이디
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 회원번호를 가져온다.
	 * @return String 회원번호
	 */
	public String getMemberNo() {
		return memberNo;
	}
	/**
	 * 회원번호를 입력한다.
	 * @param memberNo 회원 번호
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
}
