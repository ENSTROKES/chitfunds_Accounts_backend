package com.application.chitfunds.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.And;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.application.chitfunds.SequenceGenerator;
import com.application.chitfunds.controller.CustomerController;
import com.application.chitfunds.entites.CustomerChitDetails;
import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.Customers;
import com.application.chitfunds.entites.GroupEntity;
import com.application.chitfunds.entites.LedgerEntity;
import com.application.chitfunds.entites.Receipt;
import com.application.chitfunds.entites.ReceiptRouteList;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.SlabsEntity;
import com.application.chitfunds.repository.CustomerRepo;
import com.application.chitfunds.repository.ReceiptRepo;
import com.application.chitfunds.repository.SlabsRepo;
import com.application.chitfunds.util.CalenderUtils;
import com.application.chitfunds.util.Constant;

@Service
public class ReceiptServiceImpl implements ReceiptService{

	@Autowired
	ReceiptRepo repo;
	
	@Autowired
	SlabsRepo slabRepo;
	
	@Autowired
	CustomerRepo customerRrepo;
	
	
	@Autowired
	SequenceGenerator sequenceGenerator;
	
	@Autowired
	MongoTemplate mongoOperations;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Receipt> getAllReceiptList() {
		List<Receipt> receiptList = repo.findAll();
		return receiptList;
	}
	
	@Override
	public List<Receipt> getAllReceiptList(Request req) {
		
Query query = new Query();
		
		if (req.getBranch() != null) {
			query.addCriteria(new Criteria().where("branchName").is(req.getBranch()));
		}
		if(req.getBillDate()!=null) {
			System.out.println("receiptDate"+req.getBillDate());
			query.addCriteria(new Criteria().where("receiptDate").is(req.getBillDate()));			
		}
		if (req.getReceiptType() != null) {
			System.out.println("receiptType"+req.getReceiptType());
			query.addCriteria(new Criteria().where("receiptType").is(req.getReceiptType()));			
		}
		
		if (req.getPage() != null && req.getSize() != null) {
			Pageable paging = PageRequest.of(req.getPage(), req.getSize());
			query.with(paging);
		}

		//<GroupEntity> groupList = mongoTemplate.find(query, Receipt.class);
		List<Receipt> receiptList = mongoTemplate.find(query, Receipt.class);
		return receiptList;
	}

	@Override
	public Receipt getReceiptById(String id) {
		Optional<Receipt> receipt = null;
		try{
			receipt = repo.findById(id);
		}catch(Exception e) {
		System.out.println("error in : "+e.getMessage());
		e.printStackTrace();
		}
		finally {
			if (receipt.isPresent())
				return receipt.get();
			else
				return null;
		}
		
	}

	@Override
	public Boolean createReceipt(Receipt recpt) {
		Receipt receipt = recpt;
		try {
			if(receipt.getrId()==null) {
				receipt.setrId(sequenceGenerator.generateSequence(Constant.RECEIPT_SEQUENCE));
			}

			receipt.setCreatedDate(new Date().getTime());
			receipt.setLastUpdatedDate(new Date().getTime());
			Receipt result = repo.save(receipt);
			if (result != null)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error During save receipt: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Boolean deleteReceipt(String id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public List<Receipt> getReceiptByGroupandCustomer(String custId, String groupId) {
		try {
			List<Receipt> receiptList =  repo.findByCustomerId(custId);
			receiptList.stream().filter(a -> a.getGroupId()==groupId).collect(Collectors.toList());
			 return receiptList;
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public List<Receipt> getReceiptByCustomerId(String id) {
		
		return repo.findByCustomerId(id);
	}

	@Override
	public List<ReceiptRouteList> getAllRouteList() {
		List<ReceiptRouteList> route =new ArrayList<ReceiptRouteList>();
		try {
			 TypedAggregation<Receipt> receiptAggregation =
					 Aggregation.newAggregation(Receipt.class, Aggregation.group("collectionRoute"));
			  route = mongoOperations.aggregate(receiptAggregation, "receipt", ReceiptRouteList.class).getMappedResults() ;
					//mongoOperations.findOne(query(Criteria.where("branchId").is(branchId)),Branch.class);
			// List<String> result = route.stream().map(item -> item.get("_id").
					 //.get("_id").toString()).collect(Collectors.toList());
			
		}catch(Exception e) {
			
			e.printStackTrace();	
		}
	return route;
		
	}

	
	@Override
	public List<LedgerEntity> getLedgerByRoute(String route) {
		System.out.println("route : "+route);
		List<LedgerEntity> ledgerList = new ArrayList<LedgerEntity>();
		try {
		Query query = new Query();
		query.addCriteria(new Criteria().where("collection_route").is(route.trim()));
		List<CustomerGroupMapping> result = mongoOperations.find(query, CustomerGroupMapping.class);
		
		for(CustomerGroupMapping mapdata :result) {
//			Optional<Customers> cust = customerRrepo.findById(mapdata.getCustomerId());
//			Customers customerData = new Customers();
//			if (cust.isPresent())
//				customerData=  cust.get();
//			else
//				customerData=  null;
//			
//			String subscription ="";
//			for(CustomerChitDetails chit:customerData.getCustomerChitDetails()) {
//				subscription =chit.getSubscription();
//			}
			
			LedgerEntity ledger = new LedgerEntity();
			ledger.setCustomerName(mapdata.getCustomerName());
			ledger.setCollectionRoute(route.trim());
			ledger.setGroupName(mapdata.getGroupName());
			if(mapdata.getLastPayedDate()!=0) {
				ledger.setLastPaiedDate(
						CalenderUtils.ConvertMilliSecondsToFormattedDateInSimpleFormate(mapdata.getLastPayedDate()));
			}else {
				ledger.setLastPaiedDate(" - ");
			}
			
			ledger.setPaidAmount(mapdata.getReceivedAmount());
			
			ledger.setPendingAmount(mapdata.getOutStandingAmount());
			ledgerList.add(ledger);
			/*Integer diff= 0;
			
			Optional<SlabsEntity> slabList = slabRepo.findById(mapdata.getChitId());
			SlabsEntity slab = new SlabsEntity();
			if(slabList.isPresent())
				slab = slabList.get();
			else 
				slab=null;
			
			Integer installment = 0; 
			switch (mapdata.getModeOfSubscription().toLowerCase()) {
			case "monthly":			
				diff =	CalenderUtils.getMonthDifferenceBwtweenTwoDate(mapdata.getCreated_date(), new Date().getTime());
				installment = slab.getDueMonthly();
				
				break;
			case "daily":
				diff =(Integer) CalenderUtils.getDaysDifferenceBwtweenTwoDate(mapdata.getCreated_date(), new Date().getTime());
				installment = slab.getDueDaily();
				
				break;
			case "weekly":				
				diff = Math.toIntExact(CalenderUtils.getWeekDifferenceBwtweenTwoDate(mapdata.getCreated_date(), new Date().getTime()));
				installment = slab.getDueWeekly();
				break;

			default:
				break;
			}
			
		
							
			Integer totalAmount= diff*installment;
			ledger.setPendingAmount(
					totalAmount-mapdata.getReceivedAmount()>0?totalAmount-mapdata.getReceivedAmount():0.0);
			*/
			
		}
		return ledgerList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<LedgerEntity> exportLedgerByGroup(String groupId) {
		
		List<LedgerEntity> ledgerList = new ArrayList<LedgerEntity>();
		try {
		Query query = new Query();
		query.addCriteria(new Criteria().where("groupId").is(groupId.trim()));
		List<CustomerGroupMapping> result = mongoOperations.find(query, CustomerGroupMapping.class);
		
		for(CustomerGroupMapping mapdata :result) {
			
			LedgerEntity ledger = new LedgerEntity();
			ledger.setCustomerName(mapdata.getCustomerName());
			ledger.setCollectionRoute(mapdata.getCollection_route());
			ledger.setGroupName(mapdata.getGroupName());
			if(mapdata.getLastPayedDate()!=0) {
				ledger.setLastPaiedDate(
						CalenderUtils.ConvertMilliSecondsToFormattedDateInSimpleFormate(mapdata.getLastPayedDate()));
			}else {
				ledger.setLastPaiedDate(" - ");
			}
			
			ledger.setPaidAmount(mapdata.getReceivedAmount());
			
			ledger.setPendingAmount(mapdata.getOutStandingAmount());
			ledgerList.add(ledger);
			
		}
		return ledgerList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getLedgerByRouteAndTime(String route, Long fromDate, Long toDate) {
		List<Map<String, Object>> ledgerList = new ArrayList<Map<String, Object>>();
		try {
		Query query = new Query();
		query.addCriteria(new Criteria().where("collection_route").is(route.trim()));
		List<CustomerGroupMapping> result = mongoOperations.find(query, CustomerGroupMapping.class);
		for(CustomerGroupMapping mapdata :result) {
			Double monthlyTotal = 0.0;
			Map<String, Object> ledgerMap = new HashMap<String, Object>();
			ledgerMap.put("Ticket number",mapdata.getTicketNumber());
			ledgerMap.put("Customer Name", mapdata.getCustomerName());
			ledgerMap.put("Group Name",mapdata.getGroupName());
			ledgerMap.put("Installment",mapdata.getInstallment());
			ledgerMap.put("Received Amount", mapdata.getInstallment());
			ledgerMap.put("OutStanding",mapdata.getOutStandingAmount());
			
			
			Query queries = new Query();
			Criteria criteria = new Criteria();
//			criteria.and("ticketNumber").is(mapdata.getTicketNumber());
//			criteria.and("createdDate").gte(fromDate);
//			criteria.and("createdDate").lte(toDate);
			
			criteria.andOperator(Criteria.where("ticketNumber").is(mapdata.getTicketNumber()),
					Criteria.where("createdDate").gte(fromDate),
					Criteria.where("createdDate").lte(toDate));
			queries.addCriteria(criteria);
//			query.addCriteria( new Criteria().where("ticketNumber").is(mapdata.getTicketNumber())
//					.andOperator(new Criteria().where("createdDate").gte(fromDate)
//							.andOperator(new Criteria().where("createdDate").lte(toDate))));
			
			List<Receipt> receiptList = mongoOperations.find(queries, Receipt.class);
			for(Receipt res :receiptList) {
				ledgerMap.put(CalenderUtils.ConvertMilliSecondsToFormattedDateInSimpleFormate(res.getCreatedDate()),res.getBillAmount());
				monthlyTotal=monthlyTotal+res.getBillAmount();
			}
			ledgerMap.put("Monthly total", monthlyTotal);
			ledgerList.add(ledgerMap);
		}
	
	}catch(Exception e ) {
		e.printStackTrace();
	}
		return ledgerList;
	}
	
	
	
}
