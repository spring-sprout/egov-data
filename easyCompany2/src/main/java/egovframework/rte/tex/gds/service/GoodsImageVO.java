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

import java.io.Serializable;

/**
 * 품목 이미지의 VO클래스를 정의한다.
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
public class GoodsImageVO implements Serializable{

	private static final long serialVersionUID = -7514212543965482353L;
	
	/** 품목 이미지 ID */
	private String goodsImageId;
	
	/** 이미지 파일명 */
	private String fileNm;
	
		
	/**
	 * 품목 이미지 VO를 생성한다.
	 */
	public GoodsImageVO() {
		super();
	}

	/**
	 * 품목 이미지 VO를 생성한다.
	 * @param goodsImageId 품목이미지ID
	 * @param fileNm 이미지 파일명
	 */
	public GoodsImageVO(String goodsImageId, String fileNm) {
		super();
		this.goodsImageId = goodsImageId;
		this.fileNm = fileNm;
	}

	/**
	 * 품목 이미지 ID를 반환한다.
	 * @return String 품목이미지ID
	 */
	public String getGoodsImageId() {
		return goodsImageId;
	}

	/**
	 * 품목 이미지 ID를 설정한다.
	 * @param goodsImageId 품목이미지ID
	 */
	public void setGoodsImageId(String goodsImageId) {
		this.goodsImageId = goodsImageId;
	}

	/**
	 * 이미지 파일명을 반환한다.
	 * @return fileNm 파일명
	 */
	public String getFileNm() {
		return fileNm;
	}

	/**
	 * 이미지 파일명을 설정한다.
	 * @param fileNm 파일명
	 */
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	/**
	 * 품목의 이미지 정보를 문자열로 반환한다.
	 * @return 품목 이미지 정보
	 */
	@Override
	public String toString() {
		return "GoodsImageVO [goodsImageId=" + goodsImageId + ", fileNm="
				+ fileNm + "]";
	}

}
