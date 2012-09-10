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
package egovframework.rte.tex.brd.service;

import java.util.List; 

import egovframework.rte.tex.com.service.SearchVO;

/**
 * 게시판에 관한 인터페이스클래스를 정의한다.
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
public interface EgovBoardService {
    
	/**
	 * 데이터 베이스에서 게시판 목록을 읽어와 화면에 출력한다.
	 * @param SearchVO 검색조건
	 * @return List 게시글 목록 리스트
	 * @throws Exception
	 */
	List selectBoardList(SearchVO searchVO) throws Exception;
	
	/**
	 * 글번호를 이용해 데이터베이스에서 글을 읽어와 화면에 출력한다.
	 * @param bbsctt_no 글번호
	 * @return 게시글 상세화면
	 * @throws Exception
	 */
	BoardVO getBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시글을 데이터베이스에서 삭제한다.
	 * @param bbsctt_no 게시글번호
	 * @throws Exception
	 */
	void deleteBoard(BoardVO boardVO) throws Exception;

	/**
	 * 게시글의 정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장한다.
	 * @param boardVO 게시글 정보
	 * @throws Exception
	 */
	void insertBoard(BoardVO boardVO) throws Exception;

	/**
	 * 화면에 조회된 게시글 정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영한다.
	 * @param boardVO 게시글 정보
	 * @throws Exception
	 */
	void updateBoard(BoardVO boardVO) throws Exception;
	
    /**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @throws Exception
	 */
    int selectBoardListTotCnt(SearchVO searchVO) throws Exception;
    
}
