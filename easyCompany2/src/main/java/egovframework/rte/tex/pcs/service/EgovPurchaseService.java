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

/**
 * 입찰정보에 관한 인터페이스 클래스를 정의한다.
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
public interface EgovPurchaseService {

	/**
	 * 입찰대기목록에서 선택된 상품을 구매한다.
	 * @param ckd 체크리스트
	 * @throws Exception
	 */
	public void insertPurchaseFromCart(String[] ckd) throws Exception;
	
	/**
	 * 품목을 입찰한다.(바로입찰 버튼 클릭시)
	 * @param purchaseVO 입찰VO
	 * @throws Exception
	 */
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception;
	
}
