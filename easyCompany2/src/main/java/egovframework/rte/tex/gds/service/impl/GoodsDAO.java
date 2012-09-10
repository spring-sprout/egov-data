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
package egovframework.rte.tex.gds.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.gds.service.GoodsImageVO;
import egovframework.rte.tex.gds.service.GoodsVO;

/**
 * 품목 정보에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("goodsDAO")
public class GoodsDAO extends EgovAbstractDAO{

	/**
	 * 데이터 베이스에서 품목 목록을 가져온다.
	 * @param searchVO
	 * @return List<GoodsVO> 품목정보 리스트
	 * @throws Exception
	 */
	public List<GoodsVO> selectGoodsList(SearchVO searchVO) throws Exception
	{
		return list("goodsDAO.selectGoodsList", searchVO);
	}

	/**
	 * 품목의 정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장한다.
	 * @param goodsVO 등록될 품목 정보
	 * @return String 등록 결과
	 * @throws Exception
	 */
	public String insertGoods(GoodsVO goodsVO) throws Exception
	{

		return (String)insert("goodsDAO.insertGoods", goodsVO);
	}
	
	/**
	 * 데이터 베이스에서 품목의 상세 정보를 조회한다.
	 * @param goodsVO 품목 정보
	 * @return GoodsVO 품목 상세정보
	 * @throws Exception
	 */
	public GoodsVO selectGoods(GoodsVO goodsVO) throws Exception
	{
		return (GoodsVO)selectByPk("goodsDAO.selectGoods", goodsVO);
	}
	
	/**
	 * 화면에 조회된 품목 정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영한다.
	 * @param goodsVO 품목 정보
	 * @throws Exception
	 */
	public void updateGoods(GoodsVO goodsVO) throws Exception
	{
		update("goodsDAO.updateGoods", goodsVO);
		update("goodsDAO.updateGoodsImage", goodsVO.getGoodsImageVO());
		update("goodsDAO.updateGoodsImage", goodsVO.getDetailImageVO());
	}
	
	/**
	 * 조회된 품목 정보를 데이터 베이스에서 플래그 값을 변경하여 삭제 표시를 한다.
	 * @param goodsVO 품목 정보
	 * @throws Exception
	 */
	public void deleteGoods(GoodsVO goodsVO) throws Exception
	{
		update("goodsDAO.deleteGoods", goodsVO);
		
	}
	
	/**
	 * 입찰대기 목록의 해당 품목을 데이터 베이스에서 삭제한다.
	 * @param goodsVO 품목정보
	 * @throws Exception
	 */
	public void deleteGoodsCart(GoodsVO goodsVO) throws Exception{
		delete("goodsDAO.deleteCart", goodsVO);
	}
	
	/**
	 * 데이터 베이스에서 품목의 총 갯수를 조회한다.
	 * @param searchVO 검색조건
	 * @return int 상품 갯수
	 */
	public int selectGoodsListTotCnt(SearchVO searchVO) {
	        return (Integer)getSqlMapClientTemplate().queryForObject("goodsDAO.selectGoodsListTotCnt", searchVO);
	    }
	
	/**
	 * 데이터 베이스에 품목 이미지 정보를 등록한다.
	 * @param goodsImgaeVO 등록될 품목이미지정보
	 * @return String 등록결과
	 * @throws Exception
	 */
	public String insertGoodsImage(GoodsImageVO goodsImgaeVO) throws Exception
	{

		return (String)insert("goodsDAO.insertGoodsImage", goodsImgaeVO);
	}
	
	/**
	 * 데이터 베이스에서 품목 이미지 정보를 삭제한다.
	 * @param goodsImageId 삭제될 품목 이미지 ID
	 * @throws Exception
	 */
	public void deleteGoodsImage(String goodsImageId) throws Exception
	{
		delete("goodsDAO.deleteGoodsImage", goodsImageId);
	}
	
	/** 
	 * 데이터 베이스에서 품목정보를 조회한다.(xml, Excel용)
	 * @return List<GoodsVO> 상품정보
	 */
	public List<GoodsVO> selectGoodsXml() throws Exception
	{
		return list("goodsDAO.selectGoodsXml", new GoodsVO());
	}
}
