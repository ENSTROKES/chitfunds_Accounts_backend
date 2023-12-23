package com.application.chitfunds.service;

import java.util.List;
import java.util.Map;

import com.application.chitfunds.entites.LedgerEntity;
import com.application.chitfunds.entites.ReportRequest;
import com.application.chitfunds.entites.Response;

public interface OutStandingReportService {
	public Response getBranchWiseOutStanding(ReportRequest req);
	public Map<String, Double>  getRouteWiseOutStanding(ReportRequest req);
	public Response getSubscriptionhWiseOutStanding(ReportRequest req);
	public List<LedgerEntity> customerOutstandingBySubscription(ReportRequest req);
}
