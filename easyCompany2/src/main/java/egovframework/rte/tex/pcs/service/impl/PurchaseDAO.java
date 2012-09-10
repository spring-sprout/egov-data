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

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.tex.pcs.service.PurchaseVO;

/**
 * 입찰목록에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("purchaseDAO")
public class PurchaseDAO extends EgovAbstractDAO{
	
	/** 
	 * 데이터베이스에서 입찰목록에 입찰한 품목을 등록한다.
	 * @param purchaseVO 구매VO
	 */
	public void insertPurchase(PurchaseVO purchaseVO)
	{
		insert("purchaseDAO.insertPurchase", purchaseVO);
	}
}
