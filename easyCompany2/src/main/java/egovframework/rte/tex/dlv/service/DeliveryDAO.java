package egovframework.rte.tex.dlv.service;

import egov.data.ibatis.repository.SqlMapRepository;
import egov.data.ibatis.repository.annotation.Statement;
import egovframework.rte.tex.pcs.service.PurchaseVO;

import java.util.List;

/**
 * @author Keesun Baik
 */
//public interface DeliveryDAO {
public interface DeliveryDAO extends SqlMapRepository<PurchaseVO, String> {

	@Statement("deliveryDAO.selectPurchaseList")
	List<PurchaseVO> selectPurchaseList(PurchaseVO purchaseVO) throws Exception;

	@Statement("deliveryDAO.selectAllPurchaseList")
	List<PurchaseVO> selectAllPurchaseList(PurchaseVO purchaseVO) throws Exception;

	@Statement("deliveryDAO.selectAllPurchaseXml")
	List<PurchaseVO> selectAllPurchaseXml() throws Exception;

	@Statement("deliveryDAO.updateDeleveryInfo")
	void updateDeliveryStatus(PurchaseVO purchaseVO);

	@Statement("deliveryDAO.selectDeliveryInfoList")
	List selectDeliveryInfoList() throws Exception;

	@Statement("deliveryDAO.selectDeleveryInfoListTotCnt")
	int selectGoodsListTotCnt(PurchaseVO purchaseVO) throws Exception;

	@Statement("deliveryDAO.selectAllDeleveryInfoListTotCnt")
	int selectAllGoodsListTotCnt(PurchaseVO purchaseVO) throws Exception;
}
