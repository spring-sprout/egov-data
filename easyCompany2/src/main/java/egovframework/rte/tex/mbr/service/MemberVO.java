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

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 회원정보 VO클래스를 정의한다.
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
public class MemberVO implements Serializable{

	private static final long serialVersionUID = -4076942847418714109L;
	
	/** 회원번호 */
	private String memberNo;
	
	/** 이름 */
	@NotNull
	@Size(min = 1, max = 50) //JSR-303 적용 - validation
	private String name;
	
	/** 아이디 */
	@NotNull
	@Size(min = 1, max=20)
	@Pattern(regexp="[a-zA-z0-9]+") //JSR-303 적용 - validation
	private String id; 
	
	/** 비밀번호 */
	@NotNull 
	@Size(min = 1) //JSR-303 적용 - validation
	private String password;
	
	/** 이메일 */
	@Pattern(regexp=".+@.+\\.[a-z]+") //JSR-303 적용 - validation
	private String email;
	
	/** 전화번호 */
	private String telno;
	
	/** 휴대폰 번호 */
	private String mobile;
	
	/** 우편번호 */
	private String zip;
	
	/** 주소 */
	private String adres;
	
	/** 상세주소 */
	private String detailAdres;
	
	/** 최종변경일 */
	private Date lastChangeDt;
	
	/** 권한 */
	private String mngrSe;
	

	/**
	 * 회원번호를 반환한다.
	 * @return String 회원번호
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * 회원번호를 설정한다.
	 * @param memberNo 회원번호
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * 회원명을 반환한다.
	 * @return String 회원명
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 회원이름을 설정한다.
	 * @param name 회원명
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 회원ID를 반환한다.
	 * @return String 회원ID
	 */
	public String getId() {
		return id;
	}
	
	/** 회원ID를 설정한다.
	 * @param id 회원ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/** 비밀번호를 반환한다.
	 * @return String 비밀번호
	 */
	public String getPassword() {
		return password;
	}
	
	/** 비밀번호를 설정한다.
	 * @param password 비밀번호
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** 이메일주소를 반환한다.
	 * @return String 이메일주소
	 */
	public String getEmail() {
		return email;
	}
	
	/** 이메일주소를 설정한다.
	 * @param email 이메일주소
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/** 전화번호를 반환한다.
	 * @return String 전화번호
	 */
	public String getTelno() {
		return telno;
	}
	
	/** 전화번호를 설정한다.
	 * @param telno 전화번호
	 */
	public void setTelno(String telno) {
		this.telno = telno;
	}
	
	/** 핸드폰 번호를 반환한다.
	 * @return String 핸드폰 번호
	 */
	public String getMobile() {
		return mobile;
	}
	
	/** 핸드폰 번호를 설정한다.
	 * @param moblphon 핸드폰 번호
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/** 우편번호를 반환한다.
	 * @return String 우편번호
	 */
	public String getZip() {
		return zip;
	}
	
	/** 우편번호를 설정한다.
	 * @param zip 우편번호
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/** 주소를 반환한다.
	 * @return String 주소
	 */
	public String getAdres() {
		return adres;
	}
	
	/** 주소를 설정한다.
	 * @param adres 주소
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	/** 상세주소를 반환한다.
	 * @return String 상세주소
	 */
	public String getDetailAdres() {
		return detailAdres;
	}
	
	/** 상세주소를 설정한다.
	 * @param detail_adres 상세주소
	 */
	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}
	
	/** 최종변경일을 반환한다.
	 * @return Date 최종변경일
	 */
	public Date getLastChangeDt() {
		return lastChangeDt;
	}
	
	/** 최종변경일을 설정한다.
	 * @param last_change_dt 최종변경일 
	 */
	public void setLastChangeDt(Date lastChangeDt) {
		this.lastChangeDt = lastChangeDt;
	}

	/**
	 * 권한코드를 반환한다.
	 * @return String 권한코드
	 */
	public String getMngrSe() {
		return mngrSe;
	}

	/**
	 * 권한 코드를 설정한다.
	 * @param mngrSe 권한코드
	 */
	public void setMngrSe(String mngrSe) {
		this.mngrSe = mngrSe;
	}
}
