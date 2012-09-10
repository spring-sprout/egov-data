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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.rte.tex.com.service.SearchVO;

/**
 * 품목정보에 관한 인터페이스 클래스를 정의한다.
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
public interface EgovGoodsService {
	
	/**
	 * 품목을 조회한다.
	 * @param searchVO 품목정보
	 * @return List 품목 리스트
	 * @throws Exception
	 */
	public List selectGoodsList(SearchVO searchVO) throws Exception;

	/**
	 * 품목 정보를 등록한다.
	 * @param goodsVO 품목 정보
	 * @return String 목록 페이지
	 * @throws Exception
	 */
	public String insertGoods(GoodsVO goodsVO) throws Exception;

	/**
	 * 품목의 상세 정보를 조회한다.
	 * @param goodsVO 품목정보
	 * @return GoodsVO 품목정보
	 * @throws Exception
	 */
	public GoodsVO selectGoods(GoodsVO goodsVO) throws Exception;

	/**
	 * 조회된 품목을 수정한다.
	 * @param goodsVO 품목정보
	 * @param request
	 * @throws Exception
	 */
	public void updateGoods(GoodsVO goodsVO, final HttpServletRequest request) throws Exception;

	/**
	 * 조회된 품목을 삭제한다.
	 * @param goodsVO 품목정보
	 * @param request
	 * @throws Exception
	 */
	public void deleteGoods(GoodsVO goodsVO, final HttpServletRequest request) throws Exception;

    /**
     * 품목의 총 갯수를 반환한다.
     * @param searchVO 검색조건
     * @return int 품목의 갯수
     */
    int selectGoodsListTotCnt(SearchVO searchVO);
    
	/**
	 * 품목을 조회한다.(xml, Excel용)
	 * @return List 품목정보 목록
	 * @throws Exception
	 */
	public List<GoodsVO> selectGoodsXml() throws Exception;
} 
