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
package egovframework.rte.tex.gds.service;

import org.springframework.format.annotation.NumberFormat;

import egovframework.rte.tex.cgr.service.CategoryVO;
import egovframework.rte.tex.com.service.SearchVO;

/**
 * 품목VO 클래스를 정의한다.
 * @author 실행환경 개발팀 이영진
 * @since 2011.06.07
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.07  이영진          최초 생성
 * 
 * </pre>
 */
public class GoodsVO extends SearchVO{
	
	private static final long serialVersionUID = -7640739397683515058L;
	
	/** 품목ID */
	private String goodsId;
	
	/** 품목명 */
	private String goodsNm;
	
	/** 품목이미지VO */
	private GoodsImageVO goodsImageVO;
	
	/** 카테고리 VO*/
	private CategoryVO categoryVO;
	
	/** 상세정보 이미지 VO */
	private GoodsImageVO detailImageVO;
	
	/** 제조사 */
	private String makr;
	
	/** 삭제여부 */
	private String useAt;
	
	/** 가격 */
	@NumberFormat(pattern="###,##0")
	private int price;
	
	/**
	 * 품목VO를 생성한다.
	 */
	public GoodsVO()
	{
		super();
	}

	/**
	 * 품목VO를 생성한다.
	 * @param goodsId 품목ID
	 * @param goodsNm 품목명
	 * @param price 가격
	 * @param goodsImageVO 품목이미지VO
	 * @param categoryVO 카테고리VO
	 * @param detailImageVO 품목의 상세이미지VO
	 * @param makr 제조사명
	 */
	public GoodsVO(String goodsId, String goodsNm, int price,
			GoodsImageVO goodsImageVO, CategoryVO categoryVO,
			GoodsImageVO detailImageVO, String makr) {
		super();
		this.goodsId = goodsId;
		this.goodsNm = goodsNm;
		this.price = price;
		this.goodsImageVO = goodsImageVO;
		this.categoryVO = categoryVO;
		this.detailImageVO = detailImageVO;
		this.makr = makr;
	}	

	/** 
	 * 품목ID를 조회한다.
	 * @return String 품목ID
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/** 
	 * 품목ID를 설정한다.
	 * @param goodsId 등록될 품목ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/** 
	 * 품목명을 조회한다.
	 * @return String 품목명
	 */
	public String getGoodsNm() {
		return goodsNm;
	}

	/** 
	 * 품목명을 설정한다.
	 * @param goodsNm 등록될 품목명
	 */
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	/** 
	 * 품목가격을 조회한다.
	 * @return String 품목가격
	 */
	public int getPrice() {
		return price;
	}

	/** 
	 * 품목 가격을 설정한다.
	 * @param price 등록될 품목가격
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/** 
	 * 카테고리정보를 조회한다.
	 * @return CategoryVO 카테고리정보
	 */
	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	/** 
	 * 카테고리정보를 설정한다.
	 * @param ctgryId 등록될 카테고리VO
	 */
	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	/**
	 * 품목이미지 정보를 조회한다.
	 * @return GoodsImageVO 이미지 정보
	 */
	public GoodsImageVO getGoodsImageVO() {
		return goodsImageVO;
	}

	/** 
	 * 품목 이미지의 정보를 설정한다.
	 * @param goodsImageVO 등록될 품목 이미지 정보
	 */
	public void setGoodsImageVO(GoodsImageVO goodsImageVO) {
		this.goodsImageVO = goodsImageVO;
	}
	
	
	/** 
	 * 상세이미지 정보를 조회한다.
	 * @return String 상세정보 이미지 경로
	 */
	public GoodsImageVO getDetailImageVO() {
		return detailImageVO;
	}


	/** 
	 * 상세이미지 정보를 등록한다.
	 * @param detailImageVO 등록될 상세정보 이미지
	 */
	public void setDetailImageVO(GoodsImageVO detailImageVO) {
		this.detailImageVO = detailImageVO;
	}


	/** 
	 * 제조사를 조회한다.
	 * @return String 제조사명
	 */
	public String getMakr() {
		return makr;
	}

	/** 
	 * 제조사를 등록한다.
	 * @param makr 등록될 제조사
	 */
	public void setMakr(String makr) {
		this.makr = makr;
	}
	
	/**
	 * 품목의 삭제여부를 조회한다.(값이 0일경우 삭제항목) 
	 * @return String 삭제여부
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * 품목의 삭제여부를 등록한다.
	 * @param useAt 삭제여부
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/** 
	 * 품목에 대한 정보를 문자열로 반환한다.
	 * @return String 품목에 대한 정보
	 */
	@Override
	public String toString() {
		return "GoodsVO [goodsId=" + goodsId + ", goodsNm=" + goodsNm
				+ ", price=" + price + ", goodsImageVO=" + goodsImageVO
				+ ", categoryVO=" + categoryVO + ", detailImageVO="
				+ detailImageVO + ", makr=" + makr + "]";
	
	}
}
