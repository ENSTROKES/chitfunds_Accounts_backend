package com.application.chitfunds.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.application.chitfunds.entites.CustomerGroupMapRequest;
import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.Customers;
import com.application.chitfunds.entites.DocumentsModel;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.Response;
import com.application.chitfunds.repository.CustomerRepo;
import com.application.chitfunds.repository.DocumentRepo;
import com.application.chitfunds.service.CustomerService;
import com.application.chitfunds.util.Constant;

@CrossOrigin("*")
@RestController
public class CustomerController {
	@Value("${DomainName}")
	private String domainName;

	@Autowired
	CustomerService custService;
	
	@Autowired
	CustomerRepo repo;
	
	@Autowired
	DocumentRepo docsRepo;

	@RequestMapping(value = "createCustomer", method = RequestMethod.POST)
	public Response createCustomers(@RequestBody Customers req) {
		Response res = new Response();
		try {
			Customers cust = req;
			Customers savedCustomer = null;
			savedCustomer = custService.saveCustomer(cust);
			if (savedCustomer != null) {
				res.setResponseCode(200);
				res.setObject(savedCustomer.getCustomerId());
				res.setResponseMessage("Customer Data created successfully...");
			} else {
				res.setResponseCode(201);
				res.setResponseMessage("Customer Data not created");
			}

		} catch (Exception e) {
			System.out.println();
			res.setResponseCode(300);
			res.setResponseMessage("error during save customer Data.... Exception : " + e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "getCustomersCount", method = RequestMethod.GET)
	public Long getAllEmployee() {
		Long total = 0L;
		total = repo.count();
		//System.out.println("total customer count : "+ total);
		return total;
	}
	@RequestMapping(value = "getAllCustomers", method = RequestMethod.GET)
	public Response getAllEmployee(@RequestParam("page") @Nullable Integer page,
			@RequestParam("size") @Nullable Integer size, @RequestParam("scheme") @Nullable String scheme,
			@RequestParam("branch") @Nullable String branch,
			@RequestParam("collectionRoute") @Nullable String collectionRoute,
			@RequestParam("joiningDate") @Nullable String joiningDate) {
		Response res = new Response();
		Request req= new Request();
		try {
			if (scheme != null) {
				req.setSchemeId(scheme);
			}
			if (branch != null) {
				req.setBranch(branch);
			}
			if (collectionRoute != null) {
				req.setCollectionRoute(collectionRoute);
			}
			if (joiningDate != null) {
				req.setJoiningDate(joiningDate);
			}

			if (page != null && size != null) {
				req.setSize(size);
				req.setPage(page-1);
			} 
			//else {
//				res.setObject(custService.getAllCustomers());
//			}
			res.setObject(custService.getAllCustomers(req));
			
			res.setResponseCode(200);
			res.setResponseMessage("Successfully Got the all customers details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch customer Data ");
		}
		return res;
	}

	@RequestMapping(value = "getCustomerById", method = RequestMethod.GET)
	public Response getCusotmerById(@RequestParam("id") String custId) {
		Response res = new Response();
		try {
			Customers custResponse = custService.getCustomersById(custId);
			if (!custResponse.equals(null)) {
				res.setObject(custResponse);
			} else {
				throw new IOException("Customers details not found...");
			}

			res.setResponseCode(200);
			res.setResponseMessage("Successfully Got the Customer details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during get customer Data ");
		}
		return res;
	}

	@RequestMapping(value = "deleteCustomerById", method = RequestMethod.DELETE)
	public Response deleteEmployeeById(@RequestParam("id") String cusId) {
		Response res = new Response();
		try {
			custService.deleteCustomersById(cusId);
			res.setResponseCode(200);
			res.setResponseMessage("Successfully deleted the customer details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during delete customer Data ");
		}
		return res;
	}

	@RequestMapping(value = "updateCustomer", method = RequestMethod.PUT)
	public Response updateCustomer(@RequestBody Customers cus) {
		Response res = new Response();
		try {
			if (custService.updateCustomers(cus)) {
				res.setResponseCode(200);
				res.setResponseMessage("Successfully updated the customer details");
			} else {
				res.setResponseCode(200);
				res.setResponseMessage("Issue while updated the customer details");
			}
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during update customer Data ");
		}
		return res;
	}

	/*
	 * @RequestMapping(value = "upload", method = RequestMethod.POST) public String
	 * handleUploadForm(@RequestParam("file") MultipartFile multipart) { String
	 * fileName = multipart.getOriginalFilename();
	 * 
	 * System.out.println("filename: " + fileName);
	 * 
	 * String message = "";
	 * 
	 * try { S3util.uploadFile(fileName, multipart.getInputStream()); message =
	 * "Your file has been uploaded successfully!"; } catch (Exception ex) { message
	 * = "Error uploading file: " + ex.getMessage(); }
	 * 
	 * return "message"; }
	 */

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public Response uplaodImage(@RequestParam("imageFile") MultipartFile image,
			@RequestParam("adharcard") MultipartFile adhar, @RequestParam("pancard") MultipartFile pancard,
			@RequestParam("applicationForm") MultipartFile applicationForm, @RequestParam("customerId") Long customerId)
			throws IOException {
		System.out.println("Original Image Byte Size - " + image.getBytes().length);
		Response res = new Response();
		try {
			List<DocumentsModel> alreadyUploadedDocs = getListOfDocById(customerId);
			List<DocumentsModel> lsitOfDocs = new ArrayList<DocumentsModel>();
			// System.out.println("No of records : " + alreadyUploadedDocs.size());
			/*
			 * if (alreadyUploadedDocs.size()> 0) {
			 * System.out.println("There is have some records"); for (DocumentsModel doc :
			 * alreadyUploadedDocs) { if
			 * ((doc.getCategory()).equalsIgnoreCase(Constant.CUSTOMER_IMAGE)) {
			 * DocumentsModel docs = setIntoModel(image, customerId,
			 * Constant.CUSTOMER_IMAGE); docs.setId(doc.getId()); lsitOfDocs.add(docs); }
			 * else { DocumentsModel docs = setIntoModel(image, customerId,
			 * Constant.CUSTOMER_IMAGE); lsitOfDocs.add(docs); }
			 * 
			 * if ((doc.getCategory()).equalsIgnoreCase(Constant.ADHAR_CARD)) {
			 * DocumentsModel docs = setIntoModel(adhar, customerId, Constant.ADHAR_CARD);
			 * docs.setId(doc.getId()); lsitOfDocs.add(docs); } else { DocumentsModel docs =
			 * setIntoModel(adhar, customerId, Constant.ADHAR_CARD); lsitOfDocs.add(docs); }
			 * 
			 * if ((doc.getCategory()).equalsIgnoreCase(Constant.PAN_CARD)) { DocumentsModel
			 * docs = setIntoModel(pancard, customerId, Constant.PAN_CARD);
			 * docs.setId(doc.getId()); lsitOfDocs.add(docs); } else { DocumentsModel docs =
			 * setIntoModel(pancard, customerId, Constant.PAN_CARD); lsitOfDocs.add(docs); }
			 * 
			 * if ((doc.getCategory()).equalsIgnoreCase(Constant.APPLICATON_FORM)) {
			 * DocumentsModel docs = setIntoModel(applicationForm, customerId,
			 * Constant.APPLICATON_FORM); docs.setId(doc.getId()); lsitOfDocs.add(docs); }
			 * else { DocumentsModel docs = setIntoModel(applicationForm, customerId,
			 * Constant.APPLICATON_FORM); lsitOfDocs.add(docs);
			 * 
			 * }
			 * 
			 * 
			 * if ((doc.getCategory()).equalsIgnoreCase(Constant.SIGNATURE_IMAGE)) {
			 * DocumentsModel docs = setIntoModel(signature, customerId,
			 * Constant.SIGNATURE_IMAGE); docs.setId(doc.getId()); lsitOfDocs.add(docs); }
			 * else { DocumentsModel docs = setIntoModel(signature, customerId,
			 * Constant.SIGNATURE_IMAGE); lsitOfDocs.add(docs); }
			 * 
			 * } } else { System.out.println("There is no records"); if (!image.isEmpty() &&
			 * image != null) { DocumentsModel doc = setIntoModel(image, customerId,
			 * Constant.CUSTOMER_IMAGE); lsitOfDocs.add(doc);
			 * System.out.println(doc.toString()); } if (!adhar.isEmpty() && adhar != null)
			 * { DocumentsModel doc = setIntoModel(adhar, customerId, Constant.ADHAR_CARD);
			 * lsitOfDocs.add(doc); System.out.println(doc.toString()); } if
			 * (!pancard.isEmpty() && pancard != null) { DocumentsModel doc =
			 * setIntoModel(pancard, customerId, Constant.PAN_CARD); lsitOfDocs.add(doc);
			 * System.out.println(doc.toString()); } if (!applicationForm.isEmpty() &&
			 * applicationForm != null) { DocumentsModel doc = setIntoModel(applicationForm,
			 * customerId, Constant.APPLICATON_FORM); lsitOfDocs.add(doc);
			 * System.out.println(doc.toString()); }
			 * 
			 * if (!signature.isEmpty() && signature != null) { DocumentsModel doc =
			 * setIntoModel(signature, customerId, Constant.SIGNATURE_IMAGE);
			 * lsitOfDocs.add(doc); System.out.println(doc.toString()); }
			 * 
			 * 
			 * }
			 */
			DocumentsModel imageDoc = null;
			DocumentsModel adharDoc = null;
			DocumentsModel panDoc = null;
			DocumentsModel applicationDoc = null;

			List<String> catgList = new ArrayList<String>();
			if (alreadyUploadedDocs.size() > 0) {
				for (DocumentsModel doc : alreadyUploadedDocs) {
					if (doc.getCategory().equalsIgnoreCase(Constant.CUSTOMER_IMAGE)
							&& (!image.isEmpty() && image != null)) {
						String link = uploadSingleFile(image, customerId);
						doc.setLink(link);
						imageDoc = doc;
						lsitOfDocs.add(imageDoc);
					}

					if (doc.getCategory().equalsIgnoreCase(Constant.ADHAR_CARD)
							&& (!adhar.isEmpty() && adhar != null)) {
						String link = uploadSingleFile(adhar, customerId);
						doc.setLink(link);
						adharDoc = doc;
						lsitOfDocs.add(adharDoc);
					}
					if (doc.getCategory().equalsIgnoreCase(Constant.PAN_CARD)
							&& (!pancard.isEmpty() && pancard != null)) {
						String link = uploadSingleFile(pancard, customerId);
						doc.setLink(link);
						panDoc = doc;
						lsitOfDocs.add(panDoc);
					}
					if (doc.getCategory().equalsIgnoreCase(Constant.APPLICATON_FORM)
							&& (!applicationForm.isEmpty() && applicationForm != null)) {
						String link = uploadSingleFile(applicationForm, customerId);
						doc.setLink(link);
						applicationDoc = doc;
						lsitOfDocs.add(applicationDoc);
					}
				}
			}

			if (!image.isEmpty() && image != null && imageDoc == null) {
				String link = uploadSingleFile(image, customerId);
				imageDoc = new DocumentsModel(customerId, Constant.CUSTOMER_IMAGE, image.getOriginalFilename(),
						image.getContentType(), link);
				lsitOfDocs.add(imageDoc);
			}
			if (!adhar.isEmpty() && adhar != null && adharDoc == null) {
				String link = uploadSingleFile(adhar, customerId);
				adharDoc = new DocumentsModel(customerId, Constant.ADHAR_CARD, adhar.getOriginalFilename(),
						adhar.getContentType(), link);
				lsitOfDocs.add(adharDoc);
			}
			if (!pancard.isEmpty() && pancard != null && panDoc == null) {
				String link = uploadSingleFile(pancard, customerId);
				panDoc = new DocumentsModel(customerId, Constant.PAN_CARD, pancard.getOriginalFilename(),
						pancard.getContentType(), link);
				lsitOfDocs.add(panDoc);
				System.out.println(panDoc.toString());
			}
			if (!applicationForm.isEmpty() && applicationForm != null && applicationDoc == null) {
				String link = uploadSingleFile(applicationForm, customerId);
				applicationDoc = new DocumentsModel(customerId, Constant.APPLICATON_FORM,
						applicationForm.getOriginalFilename(), applicationForm.getContentType(), link);
				lsitOfDocs.add(applicationDoc);
				System.out.println(applicationDoc.toString());
			}
			docsRepo.saveAll(lsitOfDocs);
			res.setResponseCode(200);
			res.setResponseMessage("Document Uploaded successfully...");
		} catch (Exception e) {
			e.printStackTrace();
			res.setResponseCode(500);
			res.setResponseMessage("faild to upload Image....");
		}
		return res;
	}

	/*
	 * public DocumentsModel setIntoModel(MultipartFile file, Long customerId,
	 * String categ) { DocumentsModel model = new DocumentsModel(); try { model =
	 * new DocumentsModel(customerId, categ, file.getOriginalFilename(),
	 * file.getContentType(), compressBytes(file.getBytes())); } catch (IOException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } return model;
	 * }
	 */

	@RequestMapping(path = { "/getFile/{imageName}" }, method = RequestMethod.GET)
	public DocumentsModel getImage(@PathVariable("imageName") String imageName) throws IOException {
		final Optional<DocumentsModel> retrievedImage = docsRepo.findByName(imageName);
		DocumentsModel img = new DocumentsModel(retrievedImage.get().getCustomerId(),
				retrievedImage.get().getCategory(), retrievedImage.get().getName(), retrievedImage.get().getType(),
				retrievedImage.get().getLink());
		return img;
	}

	@CrossOrigin("*")
	@RequestMapping(path = { "/getImageByCustomer" }, method = RequestMethod.GET)
	public Response getImageBycutomerId(@RequestParam("id") Long customerId) throws IOException {
		Response res = new Response();
		System.out.println("cutomerId : " + customerId);

		try {
			List<DocumentsModel> listOfDoc = getListOfDocById(customerId);
			if (listOfDoc.size() > 0) {
				System.out.println("size  : " + listOfDoc.size());
				res.setObject(listOfDoc);
				res.setResponseCode(200);
			} else {
				res.setResponseCode(204);
				res.setResponseMessage("There is no record found for the customer...");
			}
		} catch (Exception e) {
			res.setErrorCode(201);
			res.setErrorMessage(e.getMessage());
		}
		return res;
	}

	public List<DocumentsModel> getListOfDocById(Long customerId) {
		final Optional<List<DocumentsModel>> retrievedImage = docsRepo.findByCustomerId(customerId);

		List<DocumentsModel> listOfDoc = new ArrayList<DocumentsModel>();
		for (DocumentsModel doc : retrievedImage.get()) {
			System.out.println("docType : " + doc.getCategory());
			DocumentsModel img = new DocumentsModel(doc.getCustomerId(), doc.getCategory(), doc.getName(),
					doc.getType(), doc.getLink());
			img.setId(doc.getId());
			listOfDoc.add(img);
		}
		return listOfDoc;
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

	@PostMapping("/uploadfile")
	public String uploadSingleFile(@RequestParam("file") MultipartFile file, Long id) {

		String upfile = custService.saveFile(file, id);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(upfile)
				.toUriString();
		String url = fileDownloadUri.replace("localhost", domainName);

		return url;
	}

	@GetMapping("/download/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

		Resource resource = custService.loadFile(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/getCustomerForGroup")
	public Response getCustomerForGroup(@RequestParam String groupId) {
		Response res = new Response();
		List<CustomerGroupMapping> listOfMappedData = custService.getMappedData(groupId);
		if (listOfMappedData != null) {
			res.setResponseCode(200);
			res.setObject(listOfMappedData);
		} else {
			res.setResponseCode(201);
			res.setResponseMessage("Error while fetching data");
		}
		return res;
	}

	@PostMapping("/customerGroupMapping")
	public Response customerGroupMapping(@RequestBody CustomerGroupMapRequest req) {
		Response res = new Response();
		if (custService.saveMappedData(req)) {
			res.setResponseCode(200);
			res.setResponseMessage("data saved successfully");

		} else {
			res.setResponseCode(201);
			res.setResponseMessage("There is no vacant in the group ...");
		}
		return res;
	}

	@GetMapping("/getCustomersForCustomerMapping")
	public Response getCustomerForGroup(@RequestParam("type") @Nullable String type,
			@RequestParam("slabId") @Nullable String slabId) {
		Response res = new Response();
		if ("" == type.toLowerCase()) {

		}
		switch (type.toLowerCase()) {
		case "allcustomer":
			List<Customers> listOfCustomer = null; 
			listOfCustomer = custService.getAllCustomers(slabId);
			if (listOfCustomer != null) {
				res.setResponseCode(200);
				res.setObject(listOfCustomer);
			} else {
				res.setResponseCode(201);
				res.setResponseMessage("There is no un mapped customer to show");
			}
			break;
		case "nonmappedcustomer":
			List<Customers> listOfNonMappedData = custService.getCustomersListForMap(slabId);
			if (listOfNonMappedData.size() > 0) {
				res.setResponseCode(200);
				res.setObject(listOfNonMappedData);
			} else {
				res.setResponseCode(201);
				res.setResponseMessage("There is no un mapped customer to show");
			}
			break;
		case "slabbased":
			List<Customers> listOfMappedData = custService.getCustomerBySlabDetails(slabId);
			if (listOfMappedData.size() > 0) {
				res.setResponseCode(200);
				res.setObject(listOfMappedData);
			} else {
				res.setResponseCode(201);
				res.setResponseMessage("There is no un mapped customer to show");
			}
			break;
		default:
			break;
		}

		return res;
	}

	@GetMapping("/getCustomerBySlabId")
	public Response getCustomerbySlabId(@RequestParam String schemeId) {
		Response res = new Response();
		List<Customers> listOfMappedData = custService.getCustomerBySlabDetails(schemeId);
		if (listOfMappedData != null) {
			res.setResponseCode(200);
			res.setObject(listOfMappedData);
		} else {
			res.setResponseCode(201);
			res.setResponseMessage("Error while fetching data");
		}
		return res;
	}
	
	@GetMapping("/getChitListByCustomerId")
	public Response getChitListByCustomerId(@RequestParam String customerId) {
		Response res = new Response();
		List<CustomerGroupMapping> listOfMappedData = custService.getMappedGroupListByCutomerId(customerId);
		if (listOfMappedData != null) {
			res.setResponseCode(200);
			res.setObject(listOfMappedData);
		} else {
			res.setResponseCode(201);
			res.setResponseMessage("Customer not mapped with any group/chit");
		}
		return res;
	}
	
}
