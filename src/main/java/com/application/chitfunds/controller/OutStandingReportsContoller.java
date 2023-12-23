package com.application.chitfunds.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.chitfunds.entites.ReportRequest;
import com.application.chitfunds.entites.Response;
import com.application.chitfunds.service.OutStandingReportService;

@CrossOrigin("*") 
@RestController
public class OutStandingReportsContoller {
	
	@Autowired
	OutStandingReportService outStandingReport;
	
	@RequestMapping(value = "getOutStandingReport", method = RequestMethod.POST)
	public Response getBranchOutstanding(@RequestBody ReportRequest req) {
		Response response = new Response();
		try {
			
			switch (req.getType()) {
			case "branch":
				response= outStandingReport.getBranchWiseOutStanding(req);
				break;
			case "route":
				Map<String, Double>  result= outStandingReport.getRouteWiseOutStanding(req);
				response.setObject(result);
				break;
			case "subscription":
				response= outStandingReport.getSubscriptionhWiseOutStanding(req);
				break;

			default:
				response.setResponseCode(1001);
				response.setResponseMessage("Undefined report type....");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@RequestMapping(value = "customerOutstandingBySubscription", method = RequestMethod.POST)
	public Response exportLedgerByRoute(@RequestBody ReportRequest req) {
		Response res = new Response();

		try {
			res.setObject(outStandingReport.customerOutstandingBySubscription(req));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

}
