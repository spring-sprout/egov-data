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
package egovframework.rte.tex.pcs.service;

import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.gds.service.GoodsVO;

/**
 * 입찰목록VO 클래스를 정의한다.
 * 
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
public class PurchaseVO extends SearchVO{
	private static final long serialVersionUID = -2781867666906905705L;
	
	/** 입찰목록ID */
	private String purchsId; 
	
	/** 품목VO */
	private GoodsVO goodsVO;
	
	/** 입찰한 회원의 회원번호*/
	private String mberNo;
	
	/** 입찰 수량 */
	private int qy;
	
	/** 입찰일 */
	private String purchsDe;
	
	/** 납품상태 */
	private String dlvySe;
	
	
	/**
	 * 입찰목록 VO를 생성한다.
	 */
	public PurchaseVO() {
		super();
	}

	/**
	 * 입찰목록 VO를 생성한다.
	 * @param purchsId 입찰목록ID
	 * @param goodsVO 품목VO
	 * @param mberNo 회원번호
	 * @param qy 수량
	 * @param purchsDe 입찰일
	 * @param dlvySe 납품상태
	 */
	public PurchaseVO(String purchsId, GoodsVO goodsVO, String mberNo,
			int qy, String purchsDe, String dlvySe) {
		super();
		this.purchsId = purchsId;
		this.goodsVO = goodsVO;
		this.mberNo = mberNo;
		this.qy = qy;
		this.purchsDe = purchsDe;
		this.dlvySe = dlvySe;
	}

	/**
	 * 입찰목록ID를 조회한다.
	 * @return String 구매목록번호
	 */
	public String getPurchsId() {
		return purchsId;
	}


	/**
	 * 입찰목록ID를 설정한다.
	 * @param purchsId 등록할 입찰목록ID
	 */
	public void setPurchsId(String purchsId) {
		this.purchsId = purchsId;
	}

	/**
	 * 품목VO를 조회한다.
	 * @return GoodsVO 품목정보
	 */
	public GoodsVO getGoodsVO() {
		return goodsVO;
	}

	/**
	 * 품목VO를 입력한다.
	 * @param goodsVO 등록할 품목정보
	 */
	public void setGoodsVO(GoodsVO goodsVO) {
		this.goodsVO = goodsVO;
	}

	/**
	 * 회원번호를 조회한다.
	 * @return String 회원번호
	 */
	public String getMberNo() {
		return mberNo;
	}

	/**
	 * 회원번호를 설정한다.
	 * @param mberNo 등록될 회원번호
	 */
	public void setMberNo(String mberNo) {
		this.mberNo = mberNo;
	}

	/**
	 * 수량을 조회한다.
	 * @return String 수량
	 */
	public int getQy() {
		return qy;
	}

	/**
	 * 수량을 설정한다.
	 * @param qy 수량
	 */
	public void setQy(int qy) {
		this.qy = qy;
	}

	/**
	 * 입찰일을 조회한다.
	 * @return String 입찰일
	 */
	public String getPurchsDe() {
		return purchsDe;
	}

	/**
	 * 입찰일을 설정한다.
	 * @param purchsDe 입찰일
	 */
	public void setPurchsDe(String purchsDe) {
		this.purchsDe = purchsDe;
	}

	/**
	 * 납품상태를 조회한다.
	 * @return String 납품상태
	 */
	public String getDlvySe() {
		return dlvySe;
	}

	/**
	 * 납품상태를 설정한다.
	 * @param dlvySe 납품상태
	 */
	public void setDlvySe(String dlvySe) {
		this.dlvySe = dlvySe;
	}
	
	/** 
	 * 입찰목록에 관한 정보를 문자열로 반환한다.
	 * @return String 입찰목록
	 */
	@Override
	public String toString() {
		return "PurchaseVO [purchsId=" + purchsId + ", goodsVO="
				+ goodsVO + ", mberNo=" + mberNo + ", qy=" + qy + ", purchsDe="
				+ purchsDe + ", dlvySe=" + dlvySe + "]";
	}
} 