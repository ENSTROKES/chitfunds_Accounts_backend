package com.application.chitfunds.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.swing.JSpinner.ListEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.Customers;
import com.application.chitfunds.entites.Receipt;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.Response;
import com.application.chitfunds.entites.SlabsEntity;
import com.application.chitfunds.repository.CustomerGroupMappingRepo;
import com.application.chitfunds.repository.ReceiptRepo;
import com.application.chitfunds.repository.SlabsRepo;
import com.application.chitfunds.service.CustomerService;
import com.application.chitfunds.service.ReceiptService;
import com.application.chitfunds.util.CalenderUtils;

@CrossOrigin("*")
@RestController
public class ReceiptController {

	private static Logger LOGGER = Logger.getLogger(ReceiptController.class.getName());
	@Autowired
	ReceiptService service;

	@Autowired
	SlabsRepo slabRepo;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerGroupMappingRepo mapRepo;

	@Autowired
	ReceiptRepo receiptRepo;

	@RequestMapping(value = "getReceiptCount", method = RequestMethod.GET)
	public Long getReceiptCount() {
		Long total = 0L;
		total = receiptRepo.count();
		// System.out.println("total customer count : "+ total);
		return total;
	}

	@RequestMapping(value = "createReceipt", method = RequestMethod.POST)
	public Response createReceipt(@RequestBody Receipt recpt) {
		Response res = new Response();
		try {
			Receipt receipt = recpt;
			if (receipt.getGroupId() != null && receipt.getCustomerId() != null
					&& receipt.getReceiptType().toLowerCase().equals("normal")) {
				List<CustomerGroupMapping> listMappedData = customerService.getMappedCustomerByGroupAndCustomerId(
						receipt.getGroupId(), receipt.getCustomerId(), receipt.getTicketNumber());
				List<CustomerGroupMapping> modifiedList = new ArrayList<>();
				for (CustomerGroupMapping mappedData : listMappedData) {

					mappedData.setLastPayedDate(new Date().getTime());
					mappedData.setReceivedAmount(mappedData.getReceivedAmount() + receipt.getBillAmount());
					mappedData.setOutStandingAmount(outstandingCalcualtion(mappedData));
					System.out.println("mapp Data is there");
					modifiedList.add(mappedData);

				}
				if (modifiedList.size() > 0) {
					LOGGER.info("Updating modified mapped cutomers data during receipt ... customer list size"
							+ modifiedList.size());
					mapRepo.saveAll(modifiedList);
				}
			}

			Customers customerDetails = customerService.getCustomersById(receipt.getCustomerId());
			/*
			 * String route = ""; for(CustomerChitDetails chit :
			 * customerDetails.getCustomerChitDetails()) { route =
			 * chit.getCollection_route(); }
			 */

			if (service.createReceipt(receipt)) {
				res.setResponseCode(200);
				res.setResponseMessage("Receipt created successfully...");
			} else {
				res.setResponseCode(201);
				res.setResponseMessage("Receipt not created");
			}

		} catch (Exception e) {
			System.out.println();
			res.setResponseCode(300);
			e.printStackTrace();
			res.setResponseMessage("error during receipt creation... Exception : " + e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "getAllReceipt", method = RequestMethod.GET)
	public Response getAllReceipt(@RequestParam("page") @Nullable Integer page,
			@RequestParam("size") @Nullable Integer size, @RequestParam("branch") @Nullable String branch,
			@RequestParam("type") @Nullable String type, @RequestParam("billDate") @Nullable String billDate) {
		Response res = new Response();
		Request req = new Request();
		try {
			if (branch != null) {
				req.setBranch(branch);
			}
			if (type != null) {
				req.setReceiptType(type);
			}
			if (billDate != null) {
				req.setBillDate(billDate);
			}
			if (page != null && size != null) {
				req.setSize(size);
				req.setPage(page - 1);
			}
			res.setObject(service.getAllReceiptList(req));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "getReceiptById", method = RequestMethod.GET)
	public Response getReceiptById(@RequestParam("id") String id) {
		Response res = new Response();
		try {

			Receipt response = service.getReceiptById(id);

			if (!response.equals(null)) {
				res.setObject(response);
			} else {
				throw new IOException("Receipt not found...");
			}

			res.setResponseCode(200);
			res.setResponseMessage("Successfully Got the Receipt details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during get Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "deleteReceiptById", method = RequestMethod.DELETE)
	public Response deleteReceiptById(@RequestParam("id") String id) {
		Response res = new Response();
		try {
			service.deleteReceipt(id);
			res.setResponseCode(200);
			res.setResponseMessage("Successfully deleted the Receipt details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during delete Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "getReceiptByGroupAndCustomerId", method = RequestMethod.GET)
	public Response getReceiptByGroupAndCustomerId(@RequestParam("customerId") String custId,
			@RequestParam("groupId") String groupId) {
		Response res = new Response();

		try {
			res.setObject(service.getReceiptByGroupandCustomer(custId, groupId));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "getReceiptByCustomerId", method = RequestMethod.GET)
	public Response getReceiptByCustomerId(@RequestParam("id") String id) {
		Response res = new Response();

		try {
			res.setObject(service.getReceiptByCustomerId(id));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "getRouteForLedger", method = RequestMethod.GET)
	public Response getAllRouteList() {
		Response res = new Response();

		try {
			res.setObject(service.getAllRouteList());
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "getLedgerByRoute", method = RequestMethod.GET)
	public Response getLedgerByRoute(@RequestParam("route") String route) {
		Response res = new Response();

		try {
			res.setObject(service.getLedgerByRoute(route));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "exportLedgerByRoute", method = RequestMethod.GET)
	public Response exportLedgerByRoute(@RequestParam("route") String route) {
		Response res = new Response();

		try {
			res.setObject(service.getLedgerByRoute(route));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "exportLedgerByGroup", method = RequestMethod.GET)
	public Response exportLedgerByGroup(@RequestParam("group") String groupId) {
		Response res = new Response();

		try {
			res.setObject(service.exportLedgerByGroup(groupId));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

	public Double outstandingCalcualtion(CustomerGroupMapping mapData) {
		Integer diff = 0;
		Integer installment = 0;
		int totalAmount = 0;
		Optional<SlabsEntity> slabList = slabRepo.findById(mapData.getChitId());
		SlabsEntity slab = new SlabsEntity();
		if (slabList.isPresent()) {
			slab = slabList.get();
		} else {
			slab = null;
		}
		installment = slab.getDueMonthly();
		diff = CalenderUtils.getMonthDifferenceBwtweenTwoDate(Long.parseLong(mapData.getJoiningDate()),
				new Date().getTime());
		switch (mapData.getModeOfSubscription().toLowerCase()) {
		case "monthly":
			// diff =
			// CalenderUtils.getMonthDifferenceBwtweenTwoDate(Long.parseLong(mapData.getJoiningDate()),
			// new Date().getTime());
			// installment = slab.getDueMonthly();
			if (diff * installment < slab.getChitValue().intValue()) {
				totalAmount = diff * installment;
			} else
				totalAmount = slab.getChitValue().intValue();
			break;
		case "daily":
			// diff =
			// CalenderUtils.getMonthDifferenceBwtweenTwoDate(Long.parseLong(mapData.getJoiningDate()),
			// new Date().getTime());
			// diff =(Integer)
			// CalenderUtils.getDaysDifferenceBwtweenTwoDate(Long.parseLong(mapData.getJoiningDate()),
			// new Date().getTime());
			if ((CalenderUtils.getDay(new Date().getTime())) > 15) {
				diff++;
			}
			// installment = slab.getDueDaily();
			// Integer totalEmi= 0;
			// Integer joiningDay=
			// CalenderUtils.getDay(Long.parseLong(mapData.getJoiningDate()));
			// System.out.println("Joining day: "+ joiningDay);
			// Integer today = CalenderUtils.getDay(new Date().getTime());
			// System.out.println("Today: "+ today);
			/*
			 * if(joiningDay>=4) { System.out.println("inside====: 1"); diff--; Integer
			 * remainigDays =
			 * CalenderUtils.getDaysInMonth(Long.parseLong(mapData.getJoiningDate()));
			 * totalEmi = totalEmi+remainigDays;
			 * System.out.println("total emi ====: "+totalEmi ); }
			 * 
			 * if(today>=1 && today<=24) { System.out.println("inside====: 2"); diff--;
			 * totalEmi = totalEmi+today; System.out.println("total emi2 ====: "+totalEmi );
			 * }
			 */

			/*
			 * totalEmi = totalEmi+(diff*25); totalAmount = totalEmi*installment;
			 * if(totalAmount>slab.getChitValue().intValue()) {
			 * System.out.println("inside====: 3"); totalAmount=
			 * slab.getChitValue().intValue(); }
			 */

			if (diff * installment < slab.getChitValue().intValue()) {
				totalAmount = diff * installment;
			} else
				totalAmount = slab.getChitValue().intValue();

			break;
		case "weekly":
			// diff =
			// CalenderUtils.getMonthDifferenceBwtweenTwoDate(Long.parseLong(mapData.getJoiningDate()),
			// new Date().getTime());
			if (CalenderUtils.getDay(new Date().getTime()) > 15) {
				diff = diff + 1;
			}
			/*
			 * installment = slab.getDueWeekly();
			 * if(diff*installment<slab.getChitValue().intValue()) { totalAmount=
			 * diff*installment; }else totalAmount= slab.getChitValue().intValue(); break;
			 */

			if (diff * installment < slab.getChitValue().intValue()) {
				totalAmount = diff * installment;
			} else
				totalAmount = slab.getChitValue().intValue();

		default:
			break;
		}
		System.out.println("Diff :: " + diff);

		// Integer totalAmount= diff*installment;

		// totalAmount-mapData.getReceivedAmount()>0?totalAmount-mapData.getReceivedAmount():0.0;
		Double outstanding = totalAmount - mapData.getReceivedAmount();
		System.out.println("outstanding  :: " + outstanding);
		return outstanding;

	}

	@RequestMapping(value = "exportLedgerByRouteAndTime", method = RequestMethod.GET)
	public Response exportLedgerByRouteAndTime(@RequestParam("route") String route,
			@RequestParam("fromdate") Long fromDate, @RequestParam("todate") Long toDate) {
		Response res = new Response();

		try {
			res.setObject(service.getLedgerByRouteAndTime(route, fromDate, toDate));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Receipt list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Receipt Data ");
		}
		return res;
	}

	@RequestMapping(value = "updateReceipt", method = RequestMethod.PUT)
	public Response updateReceipt(@RequestBody Receipt recpt) {
		Response res = new Response();
		try {
			Receipt receipt = recpt;
			if (receipt.getGroupId() != null && receipt.getCustomerId() != null
					&& receipt.getReceiptType().toLowerCase().equals("normal")) {
				List<CustomerGroupMapping> listMappedData = customerService.getMappedCustomerByGroupAndCustomerId(
						receipt.getGroupId(), receipt.getCustomerId(), receipt.getTicketNumber());
				List<CustomerGroupMapping> modifiedList = new ArrayList<>();
				for (CustomerGroupMapping mappedData : listMappedData) {

					mappedData.setLastPayedDate(new Date().getTime());
					mappedData.setReceivedAmount(calculateUpdatedReceptAmount(mappedData, recpt));
					mappedData.setOutStandingAmount(outstandingCalcualtion(mappedData));
					System.out.println("mapp Data is there");
					modifiedList.add(mappedData);

				}
				if (modifiedList.size() > 0) {
					LOGGER.info("Updating modified mapped cutomers data during receipt ... customer list size"
							+ modifiedList.size());
					mapRepo.saveAll(modifiedList);
				}
			}

			Customers customerDetails = customerService.getCustomersById(receipt.getCustomerId());
			/*
			 * String route = ""; for(CustomerChitDetails chit :
			 * customerDetails.getCustomerChitDetails()) { route =
			 * chit.getCollection_route(); }
			 */

			if (service.updateReceipt(receipt)) {
				res.setResponseCode(200);
				res.setResponseMessage("Receipt updated successfully...");
			} else {
				res.setResponseCode(201);
				res.setResponseMessage("Receipt not updated");
			}

		} catch (Exception e) {
			System.out.println();
			res.setResponseCode(300);
			e.printStackTrace();
			res.setResponseMessage("error during receipt creation... Exception : " + e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "deleteReceipt", method = RequestMethod.DELETE)
	public Response deleteReceipt(@RequestParam	 String recptId) {
		Response res = new Response();
		try {
			Receipt receipt = service.getReceiptById(recptId);
			System.out.println("Recepit Id "+ receipt.get_id() + "Receipt amount : "+ receipt.getBillAmount() );
			if (receipt != null) {
				System.out.println("Step 1");
				if (receipt.getGroupId() != null && receipt.getCustomerId() != null
						&& receipt.getReceiptType().toLowerCase().equals("normal")) {
					List<CustomerGroupMapping> listMappedData = customerService.getMappedCustomerByGroupAndCustomerId(
							receipt.getGroupId(), receipt.getCustomerId(), receipt.getTicketNumber());
					List<CustomerGroupMapping> modifiedList = new ArrayList<>();
					System.out.println("Step 2 --- Mapped data size : "+ listMappedData.size());
					for (CustomerGroupMapping mappedData : listMappedData) {

						mappedData.setLastPayedDate(new Date().getTime());
						mappedData.setReceivedAmount(calculateDeletedReceptAmount(mappedData, receipt));
						mappedData.setOutStandingAmount(outstandingCalcualtion(mappedData));
						System.out.println("mapp Data is there");
						modifiedList.add(mappedData);

					}
					if (modifiedList.size() > 0) {
						System.out.println("Step 3");
						LOGGER.info("Updating modified mapped cutomers data during receipt ... customer list size"
								+ modifiedList.size());
						mapRepo.saveAll(modifiedList);
						System.out.println("Step 4");
					}
				}
				System.out.println("Step5");

				if (service.deleteReceipt(recptId)) {
					res.setResponseCode(200);
					res.setResponseMessage("Receipt Deleted successfully...");
				} else {
					res.setResponseCode(201);
					res.setResponseMessage("Receipt not updated");
				}

			}else {
				res.setResponseCode(203);
				res.setResponseMessage("Receipt not found for this id ");
			}

		} catch (Exception e) {
			System.out.println();
			res.setResponseCode(300);
			e.printStackTrace();
			res.setResponseMessage("error during receipt creation... Exception : " + e.getMessage());
		}
		return res;
	}

	public Double calculateUpdatedReceptAmount(CustomerGroupMapping mappedData, Receipt recept) {
		List<Receipt> recepitList = service.getReceiptByCustomerId(mappedData.getCustomerId());
		Double totalAmount = 0.0;
		for (Receipt recpt : recepitList) {
			if (recept.get_id().equals(recpt.get_id())) {
				totalAmount += recept.getBillAmount();
			} else {
				totalAmount += recpt.getBillAmount();
			}
		}
		return totalAmount;
	}

	public Double calculateDeletedReceptAmount(CustomerGroupMapping mappedData, Receipt recept) {
		List<Receipt> recepitList = service.getReceiptByCustomerId(mappedData.getCustomerId());
		Double totalAmount = 0.0;
		for (Receipt recpt : recepitList) {
			if (recept.get_id().equals(recpt.get_id())) {

			} else {
				totalAmount += recpt.getBillAmount();
			}
		}
		return totalAmount;
	}
}
