package com.application.chitfunds.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.el.lang.FunctionMapperImpl.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.application.chitfunds.entites.Branch;
import com.application.chitfunds.entites.CollectionRouteModel;
import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.GroupEntity;
import com.application.chitfunds.entites.LedgerEntity;
import com.application.chitfunds.entites.ReportRequest;
import com.application.chitfunds.entites.Response;
import com.application.chitfunds.util.CalenderUtils;

@Service
public class OutStandingReportServiceImpl implements OutStandingReportService {
	@Autowired
	MongoTemplate mongoOperations;

	@Override
	public Response getBranchWiseOutStanding(ReportRequest req) {
		Map<String, Double> outstandingBranchWise = new HashMap<>();
		Response res = new Response();
		try {

			List<Branch> branchList = mongoOperations.findAll(Branch.class);

			for (Branch branch : branchList) {
				Double totalOutStanding = 0.0;
				ReportRequest reportReq = new ReportRequest();
				reportReq.setValue(branch.getOfficeName());
				Map<String, Double> result = getRouteWiseOutStanding(reportReq);
				for (Map.Entry<String, Double> entrySet : result.entrySet()) {
					Double value = entrySet.getValue();
					totalOutStanding = totalOutStanding + value;
				}
				outstandingBranchWise.put(branch.getOfficeName(), totalOutStanding);
			}
			res.setResponseCode(200);
			res.setObject(outstandingBranchWise);
		} catch (Exception e) {
			e.printStackTrace();
			res.setErrorCode(1001);
		}

		return res;
	}

	@Override
	public Map<String, Double> getRouteWiseOutStanding(ReportRequest req) {
		Map<String, Double> outstandingRouteWise = new HashMap<>();
		try {

			Query query = new Query();
			query.addCriteria(new Criteria().where("branch").is(req.getValue().trim()));
			List<CollectionRouteModel> routeList = mongoOperations.find(query, CollectionRouteModel.class);

			for (CollectionRouteModel route : routeList) {
				Query query1 = new Query();
				query1.addCriteria(new Criteria().where("collection_route").is(route.getCollectionRouteName().trim()));
				List<CustomerGroupMapping> result = mongoOperations.find(query1, CustomerGroupMapping.class);
				Double outStanding = 0.0;
				for (CustomerGroupMapping customerData : result) {
					outStanding = outStanding + customerData.getOutStandingAmount();
				}

				outstandingRouteWise.put(route.getCollectionRouteName(), outStanding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outstandingRouteWise;
	}

	@Override
	public Response getSubscriptionhWiseOutStanding(ReportRequest req) {
		Response response = new Response();
		try {
			Map<String, Double> resultMap = new HashMap<>();

			req.setSubscription("Daily");
			resultMap.put("Daily", subOutStanding(req));
			req.setSubscription("Weekly");
			resultMap.put("Weekly", subOutStanding(req));
			req.setSubscription("Monthly");
			resultMap.put("Monthly", subOutStanding(req));
			response.setObject(resultMap);
			response.setResponseCode(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Double subOutStanding(ReportRequest req) {
		Query queries = new Query();
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("collection_route").is(req.getValue()),
				Criteria.where("modeOfSubscription").is(req.getSubscription()));
		queries.addCriteria(criteria);

		List<CustomerGroupMapping> customerMappedList = mongoOperations.find(queries, CustomerGroupMapping.class);
		Double totalOutStanding = 0.0;

		for (CustomerGroupMapping listDate : customerMappedList) {
			totalOutStanding = totalOutStanding + listDate.getOutStandingAmount();
		}

		return totalOutStanding;
	}

	@Override
	public List<LedgerEntity> customerOutstandingBySubscription(ReportRequest req) {

		List<LedgerEntity> ledgerList = new ArrayList<LedgerEntity>();
		try {
			Query queries = new Query();
			Criteria criteria = new Criteria();
			criteria.andOperator(Criteria.where("collection_route").is(req.getValue()),
					Criteria.where("modeOfSubscription").is(req.getSubscription()));
			queries.addCriteria(criteria);
			List<CustomerGroupMapping> customerMappedList = mongoOperations.find(queries, CustomerGroupMapping.class);
			List<GroupEntity> groupDetails = mongoOperations.findAll(GroupEntity.class);
			Map<String, Integer> groupInstallmentMap = groupDetails.stream()
					.collect(Collectors.toMap(GroupEntity::get_id, GroupEntity::getMonthlyInstallment));
System.out.println("InstallmentList"+groupInstallmentMap.toString());
			for (CustomerGroupMapping mapdata : customerMappedList) {

				LedgerEntity ledger = new LedgerEntity();
				ledger.setCustomerName(mapdata.getCustomerName());
				ledger.setCollectionRoute(req.getValue());
				ledger.setGroupName(mapdata.getGroupName());
				if (mapdata.getLastPayedDate() != null && mapdata.getLastPayedDate() != 0) {
					ledger.setLastPaiedDate(CalenderUtils
							.ConvertMilliSecondsToFormattedDateInSimpleFormate(mapdata.getLastPayedDate()));
				} else {
					ledger.setLastPaiedDate(" - ");
				}
				ledger.setPaidAmount(mapdata.getReceivedAmount());
				ledger.setPendingAmount(mapdata.getOutStandingAmount());
				Integer installment = 0;
				installment= groupInstallmentMap.get(mapdata.getGroupId());
				System.out.println("GroupId "+ mapdata.getGroupId() +"   Installment : "+groupInstallmentMap.get(mapdata.getGroupId()));
				System.out.println("Step1.......");
				if (installment!=null && installment != 0 && mapdata.getReceivedAmount() != null && mapdata.getReceivedAmount() != 0) {
					System.out.println("Step1.......");
					Double paidInstallMent = mapdata.getReceivedAmount() / installment;
					System.out.println("GroupId paidInstallmet :"+ paidInstallMent);
					ledger.setInstallment(paidInstallMent.intValue());
				} else {
					ledger.setInstallment(0);
				}

				ledger.setContactNumber(mapdata.getContactNumber());

				ledgerList.add(ledger);
			}
			return ledgerList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
