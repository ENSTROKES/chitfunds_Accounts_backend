package com.application.chitfunds.entites;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("document_table")
public class DocumentsModel {
		
	//@Id
	//@Column(name = "id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@Column(name = "customerId")
	private Long customerId;
	
	//@Column(name = "name")
	private String name;
	
	//@Column(name = "type")
	private String type;
	
	//@Column(name = "category")
	private String category;
	
    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
//	@Column(name = "picByte", length = 52000)
//	private byte[] picByte;
	
	//@Column(name = "url")
	private String link;
	
	public DocumentsModel() {
		super();
	}
	
	public DocumentsModel(Long customerId, String categ, String name, String type, String link) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.type = type;
		this.link = link;
		this.category=categ;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	 * public byte[] getPicByte() { return picByte; }
	 * 
	 * public void setPicByte(byte[] picByte) { this.picByte = picByte; }
	 */

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "DocumentsModel [id=" + id + ", customerId=" + customerId + ", name=" + name + ", type=" + type
				+ ", category=" + category + "]";
	}

	
	
}
