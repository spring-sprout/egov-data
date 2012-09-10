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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.tex.pcs.service.CartVO;
import egovframework.rte.tex.pcs.service.EgovCartService;

/**
 * 입찰대기 품목에 관한 비지니스 클래스를 정의한다.
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
@Service("cartService")
public class EgovCartServiceImpl extends AbstractServiceImpl implements EgovCartService{
	
	/** CartDAO */
	@Resource(name="cartDAO")
	private CartDAO cartDAO;
	
	/** CartID Generation */
	@Resource(name="egovIdGnrServiceCart")
	private EgovIdGnrService egovIdGnrServiceCart;
	
	/**
	 * 입찰대기 목록을 조회한다.
	 * @param cartVO 입찰대기품목 정보
	 * @return List<CartVO> 입찰대기 정보
	 * @throws Exception
	 */
	public List<CartVO> selectCartList(CartVO cartVO) throws Exception
	{
		return cartDAO.selectCartList(cartVO);
	}
	
	/**
	 * 입찰대기 정보를 조회한다.
	 * @param cartId 입찰대기 번호
	 * @return CartVO 입찰대기품목 정보
	 * @throws Exception
	 */
	public CartVO selectCart(String cartId) throws Exception
	{
		return cartDAO.selectCart(cartId);
	}
	
	/**
	 * 입찰대기 목록에 품목을 등록한다.
	 * @param cartVO 입찰대기 목록
	 * @return String 입찰대기 목록 번호
	 * @throws Exception
	 */
	public String insertCart(CartVO cartVO) throws Exception
	{
		log.debug(cartVO.toString());
		
		String id = egovIdGnrServiceCart.getNextStringId();
		cartVO.setCartId(id);
		log.debug(cartVO.toString());
		
		cartDAO.insertCart(cartVO);
		
		return id;
	}
	
	/**
	 * 선택된 품목을 입찰대기 목록에서 삭제한다.
	 * @param ckd 체크 리스트
	 * @throws Exception
	 */
	public void deleteCart(String[] ckd) throws Exception
	{
		for(int i=0;i<ckd.length;i++){
		    cartDAO.deleteCart(ckd[i]);		  
		}
	}
    	
}
