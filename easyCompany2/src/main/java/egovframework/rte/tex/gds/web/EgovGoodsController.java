package egovframework.rte.tex.gds.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.tex.com.service.EgovUserUtil;
import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.gds.service.EgovGoodsService;
import egovframework.rte.tex.gds.service.GoodsImageVO;
import egovframework.rte.tex.gds.service.GoodsVO;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 
 * @Class Name : EgovGoodsController.java
 * @Description : EgovGoodsController class
 * @author 이영진
 * @since 2011. 5. 27.
 * @version 1.0
 */
@Controller
@SessionAttributes(types = GoodsVO.class)
public class EgovGoodsController {

	@Resource(name = "goodsService")
	EgovGoodsService egovGoodsService; // EgovGoodsService

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService; // EgovPropertyService

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator; // Validator

	@Resource(name = "fileUploadProperties")
	Properties fileUploadProperties;

	@Resource(name = "egovIdGnrServiceImage")
	private EgovIdGnrService egovIdGnrServiceImage; // goodsImageID Generation
	
//	@Resource(name = "castorMarshaller")
//	private Marshaller marshaller;


	/**
	 * 상품정보를 조회한다
	 * 
	 * @param searchVO
	 *            검색정보
	 * @return String 상품조회 화면
	 */
	@RequestMapping(value = "/gds/selectListGoods.do")
	public String selectGoodsList(
			@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model)
			throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List goodsList = egovGoodsService.selectGoodsList(searchVO);
		model.addAttribute("resultList", goodsList);

		int totCnt = egovGoodsService.selectGoodsListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		MemberVO loginVO = EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);
		return "/gds/EgovGoodsList";
	}

	/**
	 * 상품정보를 등록한다
	 * 
	 * @param goodsVO
	 *            등록될 상품정보
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param Model
	 * @param status
	 * @return String 상품목록 조회 화면
	 */
	@RequestMapping("/gds/insertGoods.do")
	public String insertGoods(final HttpServletRequest request,
			@ModelAttribute("searchVO") SearchVO searchVO, GoodsVO goodsVO,
			BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {
		// Server-Side Validation
		beanValidator.validate(goodsVO, bindingResult);

		// validate request type
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		if (bindingResult.hasErrors()) {
			model.addAttribute("goodsVO", goodsVO);
			return "/gds/EgovGoodsRegist";
		}

		List<GoodsImageVO> imageList = new ArrayList<GoodsImageVO>();

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

			while (itr.hasNext()) {

				// 상품이미지를 가지고 온다
				Entry<String, MultipartFile> entry = itr.next();
				file = entry.getValue();

				if (!"".equals(file.getOriginalFilename())) {

					String saveFileName = egovIdGnrServiceImage
							.getNextStringId();

					// 확장자 가져오기
					int index = file.getOriginalFilename().lastIndexOf(".");
					String fileExtension = "."
							+ file.getOriginalFilename().substring(index + 1);

					imageList.add(new GoodsImageVO(
							saveFileName + fileExtension, file
									.getOriginalFilename()));

					// 파일 전송
					filePath = uploadPath + "\\" + saveFileName + fileExtension;
					file.transferTo(new File(filePath));
				}
			}
		}

		goodsVO.setGoodsImageVO(imageList.get(0));
		goodsVO.setDetailImageVO(imageList.get(1));

		egovGoodsService.insertGoods(goodsVO);
		
		status.setComplete();
		return "redirect:/gds/selectListGoods.do";
	}

	/**
	 * 상품 등록 화면을 조회한다.
	 * 
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/gds/insertGoodsView.do")
	public String insertGoodsView(
			@ModelAttribute("searchVO") SearchVO searchVO, Model model)
			throws Exception {
		model.addAttribute("goodsVO", new GoodsVO());
		MemberVO loginVO=EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);
		return "/gds/EgovGoodsRegist";
	}

	/**
	 * 상품 상세정보를 조회한다
	 * 
	 * @param goodsVO
	 *            상품정보
	 * @return String 상품상세정보 화면
	 */
	@RequestMapping("/gds/getGoods.do")
	public @ModelAttribute("goodsVO")
	GoodsVO selectGoods(GoodsVO goodsVO,
			@ModelAttribute("searchVO") SearchVO searchVO) throws Exception {
		return egovGoodsService.selectGoods(goodsVO);
	}

	/**
	 * 상품 상세 화면을 조회한다.
	 * 
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/gds/getGoodsView.do")
	public String getGoodsView(@RequestParam("selectedId") String goodsId,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model)
			throws Exception {
		GoodsVO goodsVO = new GoodsVO();
		goodsVO.setGoodsId(goodsId);
		
		MemberVO loginVO=EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);

		// 변수명은 CoC에 따라 goodsVO
		model.addAttribute(selectGoods(goodsVO, searchVO));
		return "/gds/EgovGoodsDetail";
	}

	/**
	 * 상품 수정 화면을 조회한다.
	 * 
	 * @param searchVO
	 *            - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/gds/updateGoodsView.do")
	public String updateGoodsView(@RequestParam("selectedId") String goodsId,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model)
			throws Exception {
		GoodsVO goodsVO = new GoodsVO();

		goodsVO.setGoodsId(goodsId);
		
		// 변수명은 CoC에 따라 goodsVO
		model.addAttribute(selectGoods(goodsVO, searchVO));
		GoodsVO goods = selectGoods(goodsVO, searchVO);
		
		return "/gds/EgovGoodsRegist";
	}

	/**
	 * 조회된 상품정보를 수정한다
	 * 
	 * @param goodsVO
	 *            상품정보
	 * @return String 상품수정 화면
	 */
	@RequestMapping("/gds/updateGoods.do")
	public String updateGoods(final HttpServletRequest request,
			@ModelAttribute("searchVO") SearchVO searchVO, GoodsVO goodsVO,
			BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {
		// Server-Side Validation
		beanValidator.validate(goodsVO, bindingResult);

		// validate request type
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");

		if (bindingResult.hasErrors()) {
			model.addAttribute("goodsVO", goodsVO);
			return "/gds/EgovGoodsRegist";
		}

		egovGoodsService.updateGoods(goodsVO, request);
		status.setComplete();
		return "redirect:/gds/selectListGoods.do";
	}

	/**
	 * 조회된 상품정보를 삭제한다
	 * 
	 * @param goodsVO
	 *            상품정보
	 * @return String 상품조회 화면
	 */
	@RequestMapping("/gds/deleteGoods.do")
	public String deleteGoods(GoodsVO goodsVO, HttpServletRequest request,
			@ModelAttribute("searchVO") SearchVO searchVO, SessionStatus status)
			throws Exception {
		egovGoodsService.deleteGoods(goodsVO,request);
		status.setComplete();
		return "forward:/gds/selectListGoods.do";
	}
	
	/**
	 * xml로 변환될 페이지를 제공한다. 
	 */
	@Resource MarshallingView goodsMarshallingView;
	@RequestMapping("/gds/viewXML.do")
	public ModelAndView viewXML(@ModelAttribute("searchVO") SearchVO searchVO) throws Exception{

		List<GoodsVO> goodsList = egovGoodsService.selectGoodsXml();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsList", goodsList);
		
		return new ModelAndView(goodsMarshallingView, map);
	}
	
	/**
	 * excel로 변환될 페이지를 제공한다. 
	 */
	@RequestMapping("/gds/excelDownload.do")
	public ModelAndView excelDownload(@ModelAttribute("searchVO") SearchVO searchVO) throws Exception{
		
		List<GoodsVO> goodsList = egovGoodsService.selectGoodsXml();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsList", goodsList);
		
		return new ModelAndView("goodsExcelView", map);
	}
}
