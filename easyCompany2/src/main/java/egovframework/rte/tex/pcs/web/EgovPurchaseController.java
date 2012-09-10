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
package egovframework.rte.tex.pcs.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.tex.com.service.EgovUserUtil;
import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.gds.service.GoodsVO;
import egovframework.rte.tex.mbr.service.MemberVO;
import egovframework.rte.tex.pcs.service.CartVO;
import egovframework.rte.tex.pcs.service.EgovCartService;
import egovframework.rte.tex.pcs.service.EgovPurchaseService;
import egovframework.rte.tex.pcs.service.PurchaseVO;

/**
 * 입찰목록을 관리하는 컨트롤러 클래스를 정의한다.
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
@Controller
public class EgovPurchaseController {
	
	/** EgovPurchaseService */
	@Resource(name="purchaseService")
	EgovPurchaseService egovPurchaseService;
	
	/** EgovCartService */
	@Resource(name="cartService")
	EgovCartService egovCartService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService") 
    protected EgovPropertyService propertiesService;
    
	/**
	 * 입찰대기목록을 조회한다.
	 * @param searchVO 검색조건
	 * @param model
	 * @return "pcs/EgovCartList"
	 * @throws Exception
	 */
	@RequestMapping("/pcs/selectListCart.do")
	public String selectCartList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model) throws Exception
	{
		CartVO cartVO = new CartVO();
		cartVO.setSearchVO(searchVO);
		cartVO.setMberNo(EgovUserUtil.getMemberInfo().getMemberNo());
		
		List cartList = egovCartService.selectCartList(cartVO);
        model.addAttribute("resultList", cartList);
    	MemberVO loginVO = EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);
	
		return "pcs/EgovCartList";
	}

	/**
	 * 입찰대기목록을 등록한다.
	 * @param goodsId 품목ID
	 * @param qy 수량
	 * @param searchVO 검색조건
	 * @param model
	 * @return "redirect:/pcs/selectListCart.do"
	 * @throws Exception
	 */
	@RequestMapping("/pcs/insertCart.do")
	public String insertCart(
    		@RequestParam("selectedId") String goodsId,
    		@RequestParam("qy") int qy,
			@ModelAttribute("searchVO") SearchVO searchVO,Model model) throws Exception
	{
		CartVO cartVO = new CartVO();
		GoodsVO goodsVO = new GoodsVO();
		
		goodsVO.setGoodsId(goodsId);
		cartVO.setGoodsVO(goodsVO);
		
		MemberVO memberVO=EgovUserUtil.getMemberInfo();
		cartVO.setMberNo(memberVO.getMemberNo());
		cartVO.setQy(qy); 
		
		egovCartService.insertCart(cartVO);
		return "redirect:/pcs/selectListCart.do";
	}
	
	/**
	 * 선택한 입찰 대기 목록 정보를 삭제한다.
	 * @param ckd 체크리스트
	 * @return "redirect:/pcs/selectListCart.do"
	 * @throws Exception
	 */
	@RequestMapping("/pcs/deleteCart.do")
	public String deleteCart(@RequestParam("cart_col_check[]") String[] ckd) throws Exception
	{
		egovCartService.deleteCart(ckd);
		return "redirect:/pcs/selectListCart.do";
	}
	
	/**
	 * 입찰대기목록에서 선택된 품목을 입찰한다.
	 * @param ckd 체크리스트
	 * @return "redirect:/dlv/selectListPurchase.do"
	 * @throws Exception
	 */
	@RequestMapping("/pcs/purchaseFromCart.do")
	public String insertPurchaseFromCart(
			@RequestParam("cart_col_check[]") String[] ckd) throws Exception
	{
		egovPurchaseService.insertPurchaseFromCart(ckd);
		return "redirect:/dlv/selectListPurchase.do";
	}
	
	/**
	 * 품목을 입찰한다.
	 * @param goodsId 품목ID
	 * @param qy 수량
	 * @param searchVO 검색조건
	 * @param model
	 * @return "redirect:/dlv/selectListPurchase.do"
	 * @throws Exception
	 */
	@RequestMapping("/pcs/Purchase.do")
	public String insertPurchase(
    		@RequestParam("selectedId") String goodsId,
    		@RequestParam("qy") int qy,
			@ModelAttribute("searchVO") SearchVO searchVO,Model model) throws Exception
	{
		PurchaseVO purchaseVO = new PurchaseVO();
		GoodsVO goodsVO = new GoodsVO();
		goodsVO.setGoodsId(goodsId);
		
		purchaseVO.setGoodsVO(goodsVO);
		
		MemberVO memberVO=EgovUserUtil.getMemberInfo();
		purchaseVO.setMberNo(memberVO.getMemberNo());
		purchaseVO.setQy(qy);
		egovPurchaseService.insertPurchase(purchaseVO);

		return "redirect:/dlv/selectListPurchase.do";
	}
	
}
