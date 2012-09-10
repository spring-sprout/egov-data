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
package egovframework.rte.tex.cgr.service;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 카테고리 정보를 담고있는 VO 클래스를 정의한다.
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
@Entity
@Table(name = "RTETNCTGRY")
@XmlRootElement
public class CategoryVO implements Serializable{
	
	
	private static final long serialVersionUID = -2132379045115001026L;
	
	/** 카테고리ID	 */
	@Id
	@Column(name="ctgry_id")
	private String ctgryId;
	
	/** 카테고리명	 */
	@Column(name="ctgry_nm")
	@NotEmpty(message="카테고리명을 입력하세요.") //hibernate validation 적용
	private String ctgryNm;
	
	/** 카테고리 설명	 */
	@Column(name="dc")
	private String dc;
	

	/** 카테고리 아이디를 반환한다.
	 * @return ctgry_Id 카테고리ID
	 */
	public String getCtgryId() {
		return ctgryId;
	}

	/**
	 * 카테고리 아이디를 설정한다.
	 * @param ctgry_Id 카테고리ID
	 */
	public void setCtgryId(String ctgryId) {
		this.ctgryId = ctgryId;
	}

	/**
	 * 카테고리명을 반환한다.
	 * @return ctgry_nm 카테고리명
	 */
	public String getCtgryNm() {
		return ctgryNm;
	}

	/**
	 * 카테고리명을 설정한다.
	 * @param ctgry_nm 카테고리명
	 */
	public void setCtgryNm(String ctgryNm) {
		this.ctgryNm = ctgryNm;
	}

	/** 카테고리 설명을 반환한다.
	 * @return dc 카테고리 설명
	 */
	public String getDc() {
		return dc;
	}

	/** 카테고리 설명을 설정한다.
	 * @param dc 카테고리 설명
	 */
	public void setDc(String dc) {
		this.dc = dc;
	}
	
	
}
