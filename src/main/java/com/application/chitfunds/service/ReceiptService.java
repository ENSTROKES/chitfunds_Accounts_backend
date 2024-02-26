package com.application.chitfunds.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.LedgerEntity;
import com.application.chitfunds.entites.Receipt;
import com.application.chitfunds.entites.ReceiptRouteList;
import com.application.chitfunds.entites.Request;

@Repository
public interface ReceiptService{
	
	public List<Receipt> getAllReceiptList();
	public List<Receipt> getAllReceiptList(Request req);
	public Receipt getReceiptById(String id);
	public Boolean createReceipt(Receipt receipt);
	public Boolean updateReceipt(Receipt receipt);
	public Boolean deleteReceipt(String id);
	public List<Receipt> getReceiptByGroupandCustomer(String custId, String groupId);
	public List<Receipt> getReceiptByCustomerId(String id);
	public List<ReceiptRouteList> getAllRouteList();
	public List<LedgerEntity> getLedgerByRoute(String route);
	public List<LedgerEntity> exportLedgerByGroup(String groupId);
	public List<Map<String, Object>> getLedgerByRouteAndTime(String route,Long fromDate, Long toDate);
}
