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
 * 입찰대기목록 VO클래스를 정의한다.
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
public class CartVO extends SearchVO{
	private static final long serialVersionUID = 8665883632148060674L;
	
	/** 입찰목록 ID */
	private String cartId;
	
	/** 품목 VO */
	private GoodsVO goodsVO;
	
	/** 회원번호 */
	private String mberNo;
	
	/** 수량 */
	private int qy;
	
	/**
	 * 입찰대기목록 VO를 생성한다.
	 */
	public CartVO() {
		super();
	}

	/** 입찰대기목록 VO를 생성한다.
	 * @param cartId 입찰대기목록ID
	 * @param goodsVO 품목VO
	 * @param mberNo 회원번호
	 * @param qy 수량
	 */
	public CartVO(String cartId, GoodsVO goodsVO, String mberNo, int qy) {
		super();
		this.cartId = cartId;
		this.goodsVO = goodsVO;
		this.mberNo = mberNo;
		this.qy = qy;
	}

	/**
	 * 입찰대기목록 ID를 조회한다.
	 * @return String 입찰대기목록 ID
	 */
	public String getCartId() {
		return cartId;
	}

	/**
	 * 입찰대기목록 ID를 설정한다.
	 * @param cartId 입찰대기목록 ID
	 */
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	/**
	 * 품목VO를 조회한다.
	 * @return GoodsVO 품목VO
	 */
	public GoodsVO getGoodsVO() {
		return goodsVO;
	}

	/**
	 * 품목VO를 설정한다.
	 * @param goodsVO 등록될 품목 VO
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
	 * 수량 조회한다.
	 * @return int 수량
	 */
	public int getQy() {
		return qy;
	}

	/**
	 * 수량을 설정한다.
	 * @param qy 등록될 수량
	 */
	public void setQy(int qy) {
		this.qy = qy;
	}
	
	/**
	 * 입찰목록에 대한 정보를 문자열로 반환한다.
	 * @return String 입찰목록에 대한 정보
	 */
	@Override
	public String toString() {
		return "CartVO [cartId=" + cartId + ", goodsVO=" + goodsVO
				+ ", mberNo=" + mberNo + ", qy=" + qy + "]";
	}
	
}
