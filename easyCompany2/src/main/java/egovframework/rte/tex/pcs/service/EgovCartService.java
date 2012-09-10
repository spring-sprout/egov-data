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

import java.util.List;

/**
 * 입찰대기목록에 관한 인터페이스 클래스를 정의한다.
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
public interface EgovCartService {

	/**
	 * 입찰대기목록을 조회한다.
	 * @param cartVO 입찰대기 품목정보
	 * @return List<CartVO> 입찰대기 목록
	 * @throws Exception
	 */
	public List<CartVO> selectCartList(CartVO cartVO) throws Exception;

	/**
	 * 입찰대기 목록을 조회한다.
	 * @param cartId 입찰대기 ID
	 * @return CartVO 입찰대기 정보
	 * @throws Exception
	 */
	public CartVO selectCart(String cartId) throws Exception;

	/**
	 * 입찰대기 목록에 품목을 등록한다.
	 * @param cartVO 입찰대기VO
	 * @return String 입찰대기 목록 페이지
	 * @throws Exception
	 */
	public String insertCart(CartVO cartVO) throws Exception;

	/**
	 * 화면에서 선택한 품목을 입찰대기 목록에서 삭제한다.
	 * @param ckd 체크리스트
	 * @throws Exception
	 */
	public void deleteCart(String[] ckd) throws Exception;
	
}
