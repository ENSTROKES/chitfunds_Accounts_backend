package com.application.chitfunds.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.chitfunds.SequenceGenerator;
import com.application.chitfunds.entites.CustomerChitDetails;
import com.application.chitfunds.entites.CustomerDetailsForMapping;
import com.application.chitfunds.entites.CustomerGroupMapRequest;
import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.Customers;
import com.application.chitfunds.entites.GroupEntity;
import com.application.chitfunds.entites.Receipt;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.repository.CustomerGroupMappingRepo;
import com.application.chitfunds.repository.CustomerRepo;
import com.application.chitfunds.util.Constant;
import com.application.chitfunds.util.FileUploadProperties;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo repo;

	@Autowired
	MongoTemplate mongoTemplate;

//	@Autowired
//	CustomersMongoRepo mongoRepo;

	@Autowired
	ReceiptService receiptServices;

	@Autowired
	GroupService grpService;

	@Autowired
	SequenceGenerator sequenceGenerator;

	@Autowired
	CustomerGroupMappingRepo mapRepo;

	/*
	 * @Autowired CustomersDAO dao;
	 */
	@Override
	public Customers saveCustomer(Customers cust) {
		Customers cus = null;
		try {
			if (cust.get_id() == null) {
				Long seq = sequenceGenerator.generateSequence(Constant.CUSTOMERS_SEQUENCE);
				cust.setCustomerId(seq);
				Long branchSeq = sequenceGenerator.generateSequence(cust.getBranchName());
				// String subString = cust.getBranchName().substring(0, 3);
				String convert = String.format("%04d", branchSeq);

				cust.setCustomerGenId(cust.getBranchCode() + convert);
			}
			cus = repo.save(cust);

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		return cus;
	}

	@Override
	public List<Customers> getAllCustomers(Integer page, Integer size) {
		// PagingAndSortingRepository<Customers, Long> repository = new <Customers,
		// Long>();
		Query query = new Query();
		// query.addCriteria(new Criteria().where("collection_route").is(route.trim()));
		// List<CustomerGroupMapping> result = mongoOperations.find(query,
		// CustomerGroupMapping.class);

		Pageable paging = PageRequest.of(page, size);
		Page<Customers> custList = repo.findAll(paging);

		if (custList.hasContent()) {
			return custList.getContent();
		} else {
			return new ArrayList<Customers>();
		}

	}

	@Override
	public Customers getCustomersById(String custId) {
		Optional<Customers> cust = repo.findById(custId);
		if (cust.isPresent())
			return cust.get();
		else
			return null;
	}

	@Override
	public Boolean updateCustomers(Customers cust) {

		return null;
	}

	@Override
	public Boolean deleteCustomersById(String custId) {
		try {
			repo.deleteById(custId);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

//File upload
	private Path dirLocation;

	@Autowired
	public void FileSystemStorageService(FileUploadProperties fileUploadProperties) {
		this.dirLocation = Paths.get(fileUploadProperties.getLocation()).toAbsolutePath().normalize();
	}

	@Override
	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(this.dirLocation);
		} catch (Exception ex) {
			// throw new FilerException("Could not create upload dir!");
		}
	}

	@Override
	public String saveFile(MultipartFile file, Long id) {
		// TODO Auto-generated method stub
		try {
			String fileName = id + "_" + file.getOriginalFilename();
			Path dfile = this.dirLocation.resolve(fileName);
			Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
			return fileName;

		} catch (Exception e) {
			// throw FilerException("Could not upload file");
			return null;
		}
	}

	@Override
	public Resource loadFile(String fileName) {
		// TODO Auto-generated method stub
		try {
			Path file = this.dirLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			}

		} catch (MalformedURLException e) {
			// throw new FileNotFoundException("Could not download file");
		}
		return null;
	}

	@Override
	public Boolean saveMappedData(CustomerGroupMapRequest req) {
		Boolean flag = false;
		List<CustomerGroupMapping> mapList = new ArrayList<CustomerGroupMapping>();
		try {

			String groupId = req.getGroupId();
			GroupEntity groupdetails = grpService.getGroupById(groupId);
			if (groupdetails.getVacantCount() >= req.getCustomerDetails().size()) {
				for (CustomerDetailsForMapping customerData : req.getCustomerDetails()) {
					Long seq = sequenceGenerator.generateSequence(groupdetails.getGroupName().toLowerCase());
					String cusId = customerData.getCutomerId();
					String ticketNumber = groupdetails.getGroupName() + "-" + seq;
					CustomerGroupMapping map = new CustomerGroupMapping();
					map.setGroupId(groupdetails.get_id());

					map.setTicketNumber(ticketNumber);
					Customers result = getCustomersById(cusId);

					map.setChitId(groupdetails.getSchemeId());
					map.setCustomerId(result.get_id());
					map.setCustomerName(result.getPersonalDetails().getName());
					map.setContactNumber(result.getPersonalDetails().getPhoneNumber());
					map.setUpdatedDate(new Date().getTime());
					map.setCreated_date(new Date().getTime());
					map.setJoiningDate(customerData.getJoiningDate());
					map.setModeOfSubscription(customerData.getSubscription());
					for (CustomerChitDetails chitDetails : result.getCustomerChitDetails()) {
						map.setCollection_route(chitDetails.getCollection_route().trim());

					}
					Double totalBilledAmount = 0.0;
					// Double totalBilledAmount = receivedAmount(cusId, groupId);
					map.setReceivedAmount(totalBilledAmount);
					// map.setOutStandingAmount(outStandingAmount(groupdetails, totalBilledAmount,
					// result));
					map.setOutStandingAmount(0.0);
					result.setIsMappedToGroup(true);
					map.setLastPayedDate(lastPayedDate(cusId));
					saveCustomer(result);

					mapList.add(map);
				}
				mapRepo.saveAll(mapList);
				groupdetails.setVacantCount(groupdetails.getVacantCount() - req.getCustomerDetails().size());
				grpService.createGroup(groupdetails);
				flag = true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<CustomerGroupMapping> getMappedData(String groupId) {
		List<CustomerGroupMapping> mapList = mapRepo.findByGroupId(groupId);
		return mapList;
	}

	public Double receivedAmount(String custId, String groupId) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Receipt> receiptList = receiptServices.getReceiptByGroupandCustomer(custId, groupId);
		Double totalBilledAmount = 0.0;
		for (Receipt rec : receiptList) {
			totalBilledAmount = totalBilledAmount + rec.getBillAmount();
		}
		return totalBilledAmount;
	}

	public Long lastPayedDate(String custId) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Receipt> receiptList = receiptServices.getReceiptByCustomerId(custId);
		List<Receipt> resp = receiptList.stream().sorted((a, b) -> a.getCreatedDate().compareTo(b.getCreatedDate()))
				.collect(Collectors.toList());
		Long result = 0l;
		for (Receipt r : resp) {
			result = r.getCreatedDate();
		}
		return result;
	}

//	public Double outStandingAmount(GroupEntity grpdetails, Double alreadyBilledAmount, Customers customer) {
//		String modeOfSubscrition ="";  
//		
//			
//		Double slabValue = 0.0;
//		try {
//			Integer diff= 0;
//			for(CustomerChitDetails chitDetails:customer.getCustomerChitDetails()) {
//			switch (chitDetails.getSubscription().toLowerCase()) {
//			case "monthly":			
//				diff =	CalenderUtils.getMonthDifferenceBwtweenTwoDate(mapdata.getCreated_date(), new Date().getTime());
//				
//				
//				break;
//			case "daily":
//				diff =(Integer) CalenderUtils.getDaysDifferenceBwtweenTwoDate(mapdata.getCreated_date(), new Date().getTime());
//
//				
//				break;
//			case "weekly":
//				
//				
//				break;
//
//			default:
//				break;
//			}
//		}
//			Optional<SlabsEntity> slabList = slabRepo.findById(mapdata.getChitId());
//			SlabsEntity slab = new SlabsEntity();
//			if(slabList.isPresent())
//				slab = slabList.get();
//			else 
//				slab=null;
//							
//			Integer totalAmount= diff*slab.getInstallment();
//
//			SlabsEntity slabDetails = grpService.findSlabById(grpdetails.getSchemeId());
//			slabValue = slabDetails.getChitValue();
//			return (slabValue - alreadyBilledAmount);
//		} catch (Exception e) {
//			return slabValue;
//		}
//	}

	@Override
	public List<Customers> getCustomersListForMap(String slabId) {
		List<Customers> customerList = repo.findAll(Sort.by(Direction.DESC, "joiningDate"));
		System.out.println("customer data sorted size : " + customerList.size());
		List<Customers> resultList = customerList.parallelStream().filter(a -> a.getIsMappedToGroup() == false)
				.collect(Collectors.toList());
		List<Customers> customerDetails = new ArrayList();
		if (slabId != null) {
			for (Customers customer : resultList) {
				List<CustomerChitDetails> chitDetails = customer.getCustomerChitDetails();
				for (CustomerChitDetails chit : chitDetails) {
					if (chit.getSchemeId().equals(slabId)) {
						customerDetails.add(customer);
						break;
					}
				}
			}
		}else {
			customerDetails.addAll(resultList);
		}
		//List<Customers> res = customerDetails.stream().collect(Collectors.toList());
		
		return customerDetails;

	}

	@Override
	public List<Customers> getAllCustomers(String slabId) {
		List<Customers> custList = repo.findAll();
		List<Customers> customerDetails = new ArrayList();
		if (slabId != null) {
			for (Customers customer : custList) {
				List<CustomerChitDetails> chitDetails = customer.getCustomerChitDetails();
				for (CustomerChitDetails chit : chitDetails) {
					if (chit.getSchemeId().equals(slabId)) {
						customerDetails.add(customer);
						break;
					}
				}
			}
		}else {
			customerDetails.addAll(custList);
		}
		//List<Customers> res=  customerDetails.stream().collect(Collectors.toList());
		return customerDetails;
	}

	@Override
	public List<CustomerGroupMapping> getMappedCustomerByGroupAndCustomerId(String groupId, String customerId,
			String ticketNumber) {
		List<CustomerGroupMapping> maped_data = getMappedData(groupId);

		Query query = new Query();

		if (groupId != null) {
			query.addCriteria(new Criteria().where("groupId").is(groupId));
		}
		if (customerId != null) {

			query.addCriteria(new Criteria().where("customerId").is(customerId));
		}
		if (customerId != null) {

			query.addCriteria(new Criteria().where("ticketNumber").is(ticketNumber));
		}

		List<CustomerGroupMapping> custResult = mongoTemplate.find(query, CustomerGroupMapping.class);

		return custResult;
	}

	@Override
	public List<Customers> getCustomerBySlabDetails(String slabId) {
		List<Customers> allCustList = repo.findAll();
		List<Customers> resultList = new ArrayList<Customers>();
		for (Customers cus : allCustList) {
			for (CustomerChitDetails chit : cus.getCustomerChitDetails()) {
				if (slabId == chit.getChitId()) {
					resultList.add(cus);
				}
			}
		}

		return resultList;
	}

	@Override
	public List<Customers> getAllCustomers(Request req) {
		Query query = new Query();

		if (req.getBranch() != null) {
			query.addCriteria(new Criteria().where("branchName").is(req.getBranch()));
		}
		if (req.getCollectionRoute() != null) {
			query.addCriteria(
					new Criteria().where("customerChitDetails.collection_route").is(req.getCollectionRoute()));
		}
		if (req.getSchemeId() != null) {
			query.addCriteria(new Criteria().where("customerChitDetails.schemeId").is(req.getSchemeId()));
		}
		if (req.getJoiningDate() != null) {
			query.addCriteria(new Criteria().where("joiningDate").is(req.getJoiningDate()));
		}

		if (req.getPage() != null && req.getSize() != null) {
			Pageable paging = PageRequest.of(req.getPage(), req.getSize());
			query.with(paging);
		}

		List<Customers> result = mongoTemplate.find(query, Customers.class);

		return result;
	}

}
