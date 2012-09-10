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
package egovframework.rte.tex.pcs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.tex.pcs.service.CartVO;
import egovframework.rte.tex.pcs.service.EgovCartService;
import egovframework.rte.tex.pcs.service.EgovPurchaseService;
import egovframework.rte.tex.pcs.service.PurchaseVO;

/**
 * 입찰정보에 관한 비지니스 클래스를 정의한다.
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
@Service("purchaseService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class EgovPurchaseServiceImpl extends AbstractServiceImpl implements EgovPurchaseService{
	
	/** EgovCartService */
	@Resource(name="cartService")
	EgovCartService egovCartService;
	
	/** PurchaseDAO */
	@Resource(name="purchaseDAO")
	PurchaseDAO purchaseDAO;
	
	/** purchaseId Generation */
	@Resource(name="egovIdGnrServicePurchs")
	EgovIdGnrService egovIdGnrServicePurchs;
	
	/**
	 * 입찰대기 목록에서 선택된 품목을 구매한다.
	 * @param ckd 체크리스트
	 * @throws Exception
	 */
	public void insertPurchaseFromCart(String[] ckd) throws Exception
	{
		PurchaseVO purchaseVO = new PurchaseVO();
		CartVO cartVO;
		for(int i = 0;i<ckd.length;i++)
		{
			cartVO = egovCartService.selectCart(ckd[i]);
			log.debug(cartVO.toString());
				
			purchaseVO.setPurchsId(egovIdGnrServicePurchs.getNextStringId());
			purchaseVO.setGoodsVO(cartVO.getGoodsVO());
			purchaseVO.setMberNo(cartVO.getMberNo());
			purchaseVO.setQy(cartVO.getQy());
			
			purchaseDAO.insertPurchase(purchaseVO);
		}
		egovCartService.deleteCart(ckd);
	}
	
	/**
	 * 품목을 입찰한다.
	 * @param purchaseVO 구매VO
	 * @throws Exception
	 */
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception
	{
		purchaseVO.setPurchsId(egovIdGnrServicePurchs.getNextStringId());

		purchaseDAO.insertPurchase(purchaseVO);
	}
	
}
