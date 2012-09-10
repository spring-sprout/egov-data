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
package egovframework.rte.tex.brd.web;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.tex.brd.service.BoardVO;
import egovframework.rte.tex.brd.service.EgovBoardService;
import egovframework.rte.tex.com.service.EgovUserUtil;
import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 게시글 정보를 관리하는 컨트롤러 클래스를 정의한다.
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
@Controller
@SessionAttributes(types = BoardVO.class)
public class EgovBoardController {

	/** EgovBoardService */
	@Resource(name = "boardService")
	private EgovBoardService boardService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**Ehcache */
	@Resource(name="ehcache")
	Ehcache ehcache ;
    
	/** 
	 *글목록을 조회한다.(paging)
	 * @param searchVO 검색조건
	 * @param model
	 * @return "brd/egovBoardList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/brd/egovBoardList.do")
	public String selectBoardList(
			@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model)
			throws Exception {
		
		/**Cache sample */
		Ehcache cache = ehcache.getCacheManager().getCache("properties");
		
		Element pageUnit=cache.get("pageUnit");
		Element pageSize=cache.get("pageSize");
		
		if(pageUnit != null && pageSize != null){
			
			searchVO.setPageUnit(Integer.parseInt(pageUnit.getValue().toString()));
			searchVO.setPageSize(Integer.parseInt(pageSize.getValue().toString()));
	
		}else{
			/** EgovPropertyService.sample */
			searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
			
			/** cache에 입력 */
			cache.put(new Element("pageUnit",propertiesService.getInt("pageUnit")));
			cache.put(new Element("pageSize",propertiesService.getInt("pageSize")));
		}

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List boardList = boardService.selectBoardList(searchVO);
		model.addAttribute("resultList", boardList);

		int totCnt = boardService.selectBoardListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
    	MemberVO loginVO = EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);

		return "brd/egovBoardList";
	}

	/**
	 * 글 상세화면으로 이동한다.
	 * @param boardVO 게시판 정보
	 * @param searchVO 검색조건
	 * @return BoardVO 게시글 정보
	 * @throws Exception
	 */
	@RequestMapping("/brd/getBoard.do")
	public @ModelAttribute("BoardVO")BoardVO getBoard(BoardVO boardVO, @ModelAttribute("searchVO") SearchVO searchVO) throws Exception {
		return boardService.getBoard(boardVO);
	}

	/**
	 * 글등록 화면으로 이동한다.
	 * @param searchVO 검색조건
	 * @param model
	 * @return "brd/egovBoardRegister"
	 * @throws Exception
	 */
	@RequestMapping("/brd/insertBoardView.do")
	public String insertPageBoard(
			@ModelAttribute("searchVO") SearchVO searchVO, Model model)
			throws Exception {
		model.addAttribute("boardVO", new BoardVO());
		return "brd/egovBoardRegister";
	}

	/**
	 * 글 수정 화면으로 이동한다.
	 * @param id 수정할 게시글 ID
	 * @param searchVO 검색조건
	 * @param model
	 * @return "brd/egovBoardRegister"
	 * @throws Exception
	 */
	@RequestMapping("/brd/updateBoardView.do")
	public String updtPageBoard(@RequestParam("selectedId") String id,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model)
			throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBbscttNo(id);
		model.addAttribute(getBoard(boardVO, searchVO));
		
		MemberVO loginVO=EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);
		
		return "brd/egovBoardRegister";
	}

	/**
	 * 글 삭제 후 목록화면으로 이동한다.
	 * @param boardVO 게시글 정보
	 * @param searchVO 검색 조건
	 * @param status 세션 상태
	 * @return "forward:/brd/egovBoardList.do"
	 * @throws Exception
	 */
	@RequestMapping("/brd/deleteBoard.do")
	public String deleteBoard(BoardVO boardVO,
			@ModelAttribute("searchVO") SearchVO searchVO, SessionStatus status)
			throws Exception {
		boardService.deleteBoard(boardVO);
		status.setComplete();
		return "forward:/brd/egovBoardList.do";
	}

	/**
	 * 글 저장 후 목록화면으로 이동한다.
	 * @param searchVO 검색조건
	 * @param boardVO 게시글 정보
	 * @param bindingResult validation결과
	 * @param model 
	 * @param status 세션 상태
	 * @return "forward:/brd/egovBoardList.do"
	 * @throws Exception
	 */
	@RequestMapping("/brd/insertBoard.do")
	public String insertBoard(@ModelAttribute("searchVO") SearchVO searchVO,
			BoardVO boardVO, BindingResult bindingResult, Model model,
			SessionStatus status) throws Exception {

		// Server-Side Validation
		 beanValidator.validate(boardVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("boardVO", boardVO);
			return "brd/egovBoardRegister";
		}
		
		MemberVO vo = EgovUserUtil.getMemberInfo();
		boardVO.setMemberNo(vo.getMemberNo());
		boardVO.setId(vo.getId());
		boardService.insertBoard(boardVO);
		status.setComplete();
		return "redirect:/brd/egovBoardList.do";
	}

	/** 
	 * 글 수정 후 목록화면으로 이동한다.
	 * @param searchVO 검색조건
	 * @param boardVO 게시글 정보
	 * @param bindingResult validation결과
	 * @param model
	 * @param status 세션 상태
	 * @return "forward:/brd/egovBoardList.do"
	 * @throws Exception
	 */
	@RequestMapping("/brd/updateBoard.do")
	public String updateBoard(@ModelAttribute("searchVO") SearchVO searchVO,
			BoardVO boardVO, BindingResult bindingResult, Model model,
			SessionStatus status) throws Exception {

		beanValidator.validate(boardVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("boardVO", boardVO);
			return "brd/egovBoardRegister";
		}

		boardService.updateBoard(boardVO);
		status.setComplete();
		return "forward:/brd/egovBoardList.do";
	}
}
