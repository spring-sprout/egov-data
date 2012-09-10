package egovframework.rte.tex.dlv.service;

import egovframework.rte.tex.pcs.service.PurchaseVO;

import java.util.List;

/**
 * @author Keesun Baik
 */
public interface DeliveryDAO {
	List<PurchaseVO> selectPurchaseList(PurchaseVO purchaseVO)
			throws Exception;

	List<PurchaseVO> selectAllPurchaseList(PurchaseVO purchaseVO)
			throws Exception;

	List<PurchaseVO> selectAllPurchaseXml()
			throws Exception;

	void updateDeliveryStatus(PurchaseVO purchaseVO);

	List selectDeliveryInfoList() throws Exception;

	int selectGoodsListTotCnt(PurchaseVO purchaseVO) throws Exception;

	int selectAllGoodsListTotCnt(PurchaseVO purchaseVO) throws Exception;
}
