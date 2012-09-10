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
package egovframework.rte.tex.brd.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.tex.brd.service.BoardVO;
import egovframework.rte.tex.com.service.SearchVO;

/**
 * 게시판 정보에 관한 데이터 접근 클래스를 정의한다.
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

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO{

	/**
	 * 데이터 베이스에서 게시판 목록을 읽어와 화면에 출력한다.
	 * @param SearchVO vo
	 * @return List 게시글 목록 리스트
	 * @throws Exception
	 */
	public List selectBoardList(SearchVO vo) throws Exception{
		List list = list("boardDAO.selectBoardList", vo);
		return list;
	}
	
	/**
	 * 글번호를 이용해 데이터베이스에서 글을 읽어와 화면에 출력한다.
	 * @param bbsctt_no 글번호
	 * @return 게시글 상세화면
	 * @throws Exception
	 */
	public BoardVO getBoard(BoardVO vo) throws Exception{
		return (BoardVO)selectByPk("boardDAO.selectBoard", vo);

	}
	
	/**
	 * 게시글을 데이터베이스에서 삭제한다.
	 * @param bbsctt_no 게시글번호
	 * @throws Exception
	 */
	public void deleteBoard(BoardVO vo) throws Exception{
		delete("boardDAO.deleteBoard", vo);
	}
	
	/**
	 * 게시글의 정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장한다.
	 * @param boardVO 게시글 정보
	 * @throws Exception
	 */
	public void insertBoard(BoardVO boardVO) throws Exception{
		insert("boardDAO.insertBoard", boardVO);
	}
	
	/**
	 * 화면에 조회된 게시글 정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영한다.
	 * @param boardVO 게시글 정보
	 * @throws Exception
	 */
	public void updateBoard(BoardVO boardVO) throws Exception{
		update("boardDAO.updateBoard", boardVO);
	}
	
	 /**
	 * 데이터 베이스에서 글 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 갯수
	 * @exception Exception
	 */
    public int selectBoardListTotCnt(SearchVO searchVO) throws Exception{
        return (Integer)getSqlMapClientTemplate().queryForObject("boardDAO.selectBoardListTotCnt", searchVO);
    }
}
