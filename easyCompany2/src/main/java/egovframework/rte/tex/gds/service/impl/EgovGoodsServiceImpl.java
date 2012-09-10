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
package egovframework.rte.tex.gds.service.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.gds.service.EgovGoodsService;
import egovframework.rte.tex.gds.service.GoodsImageVO;
import egovframework.rte.tex.gds.service.GoodsVO;

/**
 * 품목정보에 관한 비지니스 클래스를 정의한다.
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
@Service("goodsService")
public class EgovGoodsServiceImpl extends AbstractServiceImpl implements EgovGoodsService{
	
	/** GoodsDAO */
	@Resource(name="goodsDAO")
	private GoodsDAO goodsDAO;
	
	/** goodsID Generation */
	@Resource(name="egovIdGnrServiceGds")
	private EgovIdGnrService egovIdGnrServiceGds;
	
	/** fileUploadProperties */
	@Resource(name = "fileUploadProperties")
	Properties fileUploadProperties;
	
	/**
	 * 품목정보를 조회한다.
	 * @param searchVO
	 * @return List<GoodsVO> 품목리스트
	 * @throws Exception
	 */
	public List<GoodsVO> selectGoodsList(SearchVO searchVO) throws Exception
	{
		return goodsDAO.selectGoodsList(searchVO);
	}

	/**
	 * 품목정보를 등록한다.
	 * @param goodsVO 등록되는 품목정보
	 * @return String 품목번호
	 * @throws Exception
	 */
	public String insertGoods(GoodsVO goodsVO) throws Exception
	{
		log.debug(goodsVO.toString());
		
		goodsDAO.insertGoodsImage(goodsVO.getGoodsImageVO());
		goodsDAO.insertGoodsImage(goodsVO.getDetailImageVO());
		
		
		/** ID Generation Service */
    	String id = egovIdGnrServiceGds.getNextStringId();
    	goodsVO.setGoodsId(id);
		log.debug(goodsVO.toString());
		
    	goodsDAO.insertGoods(goodsVO);

		return id;
	}
	
	/**
	 * 품목 상세정보를 조회한다.
	 * @param goodsVO 품목 정보
	 * @return GoodsVO 검색된 품목 정보
	 * @throws Exception
	 */
	public GoodsVO selectGoods(GoodsVO goodsVO) throws Exception
	{
		GoodsVO resultVO = goodsDAO.selectGoods(goodsVO);
		log.debug(resultVO);
		
		if (resultVO == null)
            throw processException("info.nodata.msg");
		
        return resultVO;
	}
	
	/**
	 * 조회된 품목을 수정한다.
	 * @param request
	 * @param goodsVO 품목정보
	 * @throws Exception
	 */
	public void updateGoods(GoodsVO goodsVO, final HttpServletRequest request) throws Exception
	{

		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		GoodsImageVO[] imageList = new GoodsImageVO[2];
		// extract files
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		// process files
		String uploadLastPath = fileUploadProperties.getProperty("file.upload.path");
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + uploadLastPath;
		File saveFolder = new File(uploadPath);

		// 디렉토리 생성
		boolean isDir = false;

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		if (!isDir) {

			Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
					.iterator();
			MultipartFile file;
			String filePath;
			int i = 0; // goodsImage,detailImage 구분용 index 
			while (itr.hasNext()) {

				// 상품이미지를 가지고 온다
				Entry<String, MultipartFile> entry = itr.next();
				file = entry.getValue();

				if (!"".equals(file.getOriginalFilename())) {
					
					String saveFileName;
					
					if (i == 0) {
						saveFileName = goodsVO.getGoodsImageVO().getGoodsImageId();
					} else {
						saveFileName = goodsVO.getDetailImageVO().getGoodsImageId();
					}
					
					imageList[i] =  new GoodsImageVO(
							saveFileName, file
							.getOriginalFilename());
					// 파일 전송
					filePath = uploadPath + "\\" + saveFileName;
					file.transferTo(new File(filePath));
				}
				i++;
			}
		}
		if (imageList[0] != null)
			goodsVO.setGoodsImageVO(imageList[0]);
		if (imageList[1] != null)
			goodsVO.setDetailImageVO(imageList[1]);
		
		goodsDAO.updateGoods(goodsVO);
		
	}
	
	/**
	 * 품목을 삭제한다.
	 * @param goodsVO 품목정보
	 * @param request
	 * @throws Exception
	 */
	public void deleteGoods(GoodsVO goodsVO, final HttpServletRequest request) throws Exception
	{
    	goodsDAO.deleteGoods(goodsVO); //상품삭제(플래그 변경)
    	goodsDAO.deleteGoodsCart(goodsVO); //상품이 있는 카트 모두 삭제
	}

	/**
	 * 품목리스트의 갯수를 조회한다.
	 * @param searchVO 검색조건
	 * @return int 상품 갯수
	 */
	public int selectGoodsListTotCnt(SearchVO searchVO) {
		return goodsDAO.selectGoodsListTotCnt(searchVO);
	}

	/**
	 * 품목 정보를 조회한다.(xml, excel)
	 * @return List<GoodsVO> 상품정보
	 * @throws Exception
	 */
	public List<GoodsVO> selectGoodsXml() throws Exception{
		return goodsDAO.selectGoodsXml();
	}
}
