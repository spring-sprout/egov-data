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
package egovframework.rte.tex.dlv.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.tex.pcs.service.PurchaseVO;

/**
 * 입찰내역 관리 및 납품상태에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("deliveryDAO")
public class DeliveryDAO extends EgovAbstractDAO {

	/**
	 * 데이터 베이스에서 사용자의 입찰내역 조회한다.
	 * @param purchaseVO 입찰내역 정보
	 * @return List<PurchaseVO> 입찰내역 리스트
	 * @throws Exception
	 */
	public List<PurchaseVO> selectPurchaseList(PurchaseVO purchaseVO)
			throws Exception {
		
		return list("deliveryDAO.selectPurchaseList", purchaseVO);
	}

	/**
	 * 데이터 베이스에서 전체 입찰내역 조회한다.(관리자용)
	 * @param purchaseVO 입찰내역 정보
	 * @return List<PurchaseVO> 입찰내역 리스트
	 * @throws Exception
	 */
	public List<PurchaseVO> selectAllPurchaseList(PurchaseVO purchaseVO)
	throws Exception {
		return list("deliveryDAO.selectAllPurchaseList", purchaseVO);
	}
	
	/**
	 * 데이터 베이스에서 전체 입찰내역을 조회한다.(xml, Excel용)
	 * @return List<PurchaseVO> 입찰내역 리스트
	 * @throws Exception
	 */
	public List<PurchaseVO> selectAllPurchaseXml()
	throws Exception {
		return list("deliveryDAO.selectAllPurchaseXml", new PurchaseVO());
	}

	/**
	 * 데이터 베이스에서 납품상태를 변경한다.
	 * 
	 * @param purchaseVO 입찰내역 정보
	 */
	public void updateDeliveryStatus(PurchaseVO purchaseVO) {
		update("deliveryDAO.updateDeleveryInfo", purchaseVO);
	}

	/**
	 * 데이터 베이스에서 납품상태 정보를 조회한다.
	 * 
	 * @return List 납품상태 정보 리스트
	 * @throws Exception
	 */
	public List selectDeliveryInfoList() throws Exception {
		return list("deliveryDAO.selectDeliveryInfoList", null);
	}

	/**
	 * 데이터 베이스에서 회원의 입찰목록의 건수를 조회한다.
	 * @param purchaseVO 입찰내역정보
	 * @return int 입찰 건수
	 * @throws Exception
	 */
	public int selectGoodsListTotCnt(PurchaseVO purchaseVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"deliveryDAO.selectDeleveryInfoListTotCnt", purchaseVO);
	}
	
	/**
	 * 데이터 베이스에서 전체 입찰목록의 건수를 조회한다.(관리자용)
	 * @param purchaseVO 입찰내역 정보
	 * @return int 입찰건수
	 * @throws Exception
	 */
	public int selectAllGoodsListTotCnt(PurchaseVO purchaseVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"deliveryDAO.selectAllDeleveryInfoListTotCnt", purchaseVO);
	}
}
