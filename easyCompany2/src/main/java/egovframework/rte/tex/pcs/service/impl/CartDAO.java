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

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.tex.pcs.service.CartVO;

/**
 * 입찰대기 품목에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("cartDAO")
public class CartDAO extends EgovAbstractDAO{
	
	/**
	 * 데이터 베이스에서 입찰대기 품목을 조회한다.
	 * @param cartVO 입찰대기 품목
	 * @return List<CartVO> 입찰대기 품목 리스트
	 */
	public List<CartVO> selectCartList(CartVO cartVO)
	{
		return list("cartDAO.selectCartList", cartVO);
	}
	
	/**
	 * 데이터 베이스에서 입찰대기 정보를 조회한다.
	 * @param cartId 조회할 CartId
	 * @return CartVO 조회된 입찰대기 정보
	 */
	public CartVO selectCart(String cartId)
	{
		return (CartVO)selectByPk("cartDAO.selectCart", cartId);
	}

	/**
	 * 데이터 베이스의 입찰대기 목록에 품목을 등록한다.
	 * @param cartVO 장바구니정보
	 */
	public void insertCart(CartVO cartVO)
	{
		insert("cartDAO.insertCart", cartVO);
	}
	
	/**
	 * 선택된 입찰대기 정보를 데이터 베이스에서 삭제한다.
	 * @param ckd 체크 리스트
	 */
	public void deleteCart(String ckd)
	{
		delete("cartDAO.deleteCart", ckd);
	}
	
}
