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

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 검색 정보를 담고있는 VO 클래스를 정의한다.
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
@XmlRootElement
public class SearchVO implements Serializable{
	
	private static final long serialVersionUID = 472352564053158231L;
	
	/** 현재 페이지 */
	private int pageIndex = 1;
	
	/** 검색조건 */
	private String searchCondition = "";
	
	/** 검색키워드 */
	private String searchKeyword = "";
    
	/** 검색 사용여부 */
	private String searchUseYn = "";
	
	/** 페이지 갯수 */
    private int pageUnit; // properties에서 설정
    
    /** 페이지 사이즈 */
    private int pageSize; // properties에서 설정
    
    /** 시작 인덱스 */
    private int firstIndex = 1;
    
    /** 끝 인덱스 */
    private int lastIndex = 1;
    
    /**페이지 별 레코드 갯수  */
    private int recordCountPerPage = 10; 
    
    /**
     * 검색 조건을 를 설정한다.
     * @param vo 검색조건
     */
    public void setSearchVO(SearchVO vo){
    	this.pageIndex = vo.pageIndex;
    	this.searchCondition = vo.getSearchCondition();
    	this.searchKeyword = vo.getSearchKeyword();
    	this.searchUseYn = vo.getSearchUseYn();
    	this.pageUnit = vo.pageUnit;
    	this.pageSize = vo.pageSize;
    	this.firstIndex = vo.firstIndex;
    	this.lastIndex = vo.lastIndex;
    	this.recordCountPerPage = vo.recordCountPerPage;
    }
	
    /**
	 * 끝인덱스를 반환한다.
	 * @return 끝인덱스 
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * 끝인덱스를 설정한다.
	 * @param lastIndex 를 설정한다.될 끝인덱스 
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * recordCountPerPage를 반환한다.
	 * @return recordCountPerPage
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * recordCountPerPage를 설정한다.
	 * @param recordCountPerPage 를 설정한다.될 recordCountPerPage
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	/**
	 * first Index를 반환한다.
	 * @return first Index
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * first Index를 설정한다.
	 * @param firstIndex 를 설정한다.될 first Index
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * 현재 페이지를 반환한다.
	 * @return int 현재 페이지
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 현재 페이지를 설정한다.
	 * @param pageIndex 를 설정한다.될 현재 페이지
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	
	/**
	 * 검색조건을 반환한다.
	 * @return String 검색조건
	 */
	public String getSearchCondition() {
		return searchCondition;
	}

	/**
	 * 검색조건을 설정한다.
	 * @param searchCondition 검색조건
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * 검색키워드를 반환한다.
	 * @return String 검색키워드
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * 검색키워드를 설정한다.
	 * @param searchKeyword 를 설정한다.될 검색키워드
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * 검색사용여부를 반환한다.
	 * @return String 검색사용여부
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}

	/**
	 * 검색사용여부를 설정한다.
	 * @param searchUseYn 검색사용여부
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	/**
	 * 페이지 수를 반환한다.
	 * @return int 페이지 수 
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * 페이지 수를 설정한다.
	 * @param pageUnit 페이지 수
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * 페이지 사이즈를 반환한다.
	 * @return int 페이지 사이즈
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 페이지 사이즈를 설정한다.
	 * @param pageSize 페이지 사이즈
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/** 
	 * 검색에 대한 정보를 문자열로 반환한다.
	 * @return String 검색에 대한 정보
	 */
	@Override
	public String toString() {
		return "SearchVO [pageIndex=" + pageIndex + ", searchCondition="
				+ searchCondition + ", searchKeyword=" + searchKeyword
				+ ", searchUseYn=" + searchUseYn + ", pageUnit=" + pageUnit
				+ ", pageSize=" + pageSize + "]";
	}
    
}
 