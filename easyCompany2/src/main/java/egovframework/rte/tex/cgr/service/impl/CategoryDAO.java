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
package egovframework.rte.tex.cgr.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.annotations.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.tex.cgr.service.CategoryVO;

/**
 * 카테고리 정보에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("categoryDAO")
@Transactional
public class CategoryDAO extends HibernateDaoSupport {
	
	/**
	 * hibernate를 사용하기 위해 sessionFactory객체를 설정한다.
	 * @param sessionFactory
	 */
	@Autowired
	public void setHibernateDaoSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 선택된 카테고리 정보를 데이터베이스에서 삭제한다.
	 * @param categoryVO 카테고리 정보
	 * @throws Exception
	 */
	public void deleteCategory(CategoryVO categoryVO) throws Exception{
		super.getHibernateTemplate().delete(categoryVO);
	}
	
	/** 
	 * 카테고리의 정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장한다.
	 * @param categoryVO 카테고리 정보
	 * @throws Exception
	 */
	public void insertCategory(CategoryVO categoryVO) throws Exception{
		super.getHibernateTemplate().save(categoryVO);
	}
	
	/** 
	 * 카테고리의 전체 목록을 데이터베이스에서 읽어와 화면에 출력한다.
	 * @return List<CategoryVO> 카테고리정보 리스트
	 * @throws Exception
	 */
	@OrderBy(clause = "ctgryId")
	public List<CategoryVO> selectCategoryList() throws Exception{
		return super.getHibernateTemplate().loadAll(CategoryVO.class);
	}
	
	/** 
	 * 화면에 조회된 카테고리 정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영한다.
	 * @param categoryVO 카테고리 정보
	 * @throws Exception
	 */
	public void updateCategory(CategoryVO categoryVO) throws Exception{
		super.getHibernateTemplate().update(categoryVO);
	}
	
	/**
	 * 조회할 카테고리 정보의 갯수를 가져온다.
	 * @return int 카테고리 갯수
	 * @throws Exception
	 */
	public int selectCategoryListTotCnt() throws Exception{
		return selectCategoryList().size();
	}
	
	/**
	 * 데이터베이스에서 카테고리 정보를 읽어와 화면에 출력한다.
	 * @param vo 카테고리 정보
	 * @return CategoryVO 카테고리 정보
	 * @throws Exception
	 */
	public CategoryVO getCategory(CategoryVO vo) throws Exception{
		return (CategoryVO) super.getHibernateTemplate().get(CategoryVO.class, vo.getCtgryId());
	}
}
