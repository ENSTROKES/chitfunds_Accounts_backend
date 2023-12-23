package com.application.chitfunds.entites;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="slabs")
public class SlabsEntity {

	private Long slabId;
	private String _id;
	private Double chitValue;
	private Integer installment;
	private Integer dueDaily;
	private Integer dueWeekly;
	private Integer dueMonthly;
	private Double registrationFees;
//	@OneToMany(cascade = CascadeType.ALL) 
	private List<PaymentSlabs> paymentSlab;
	private long createdDate;
	private long UpdatedDate;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Long getSlabId() {
		return slabId;
	}
	public void setSlabId(Long slabId) {
		this.slabId = slabId;
	}

	public Double getChitValue() {
		return chitValue;
	}
	public void setChitValue(Double chitValue) {
		this.chitValue = chitValue;
	}
	public Integer getInstallment() {
		return installment;
	}
	public void setInstallment(Integer installment) {
		this.installment = installment;
	}
	public Integer getDueDaily() {
		return dueDaily;
	}
	public void setDueDaily(Integer dueDaily) {
		this.dueDaily = dueDaily;
	}
	public Integer getDueWeekly() {
		return dueWeekly;
	}
	public void setDueWeekly(Integer dueWeekly) {
		this.dueWeekly = dueWeekly;
	}
	public Integer getDueMonthly() {
		return dueMonthly;
	}
	public void setDueMonthly(Integer dueMonthly) {
		this.dueMonthly = dueMonthly;
	}
	public List<PaymentSlabs> getPaymentSlab() {
		return paymentSlab;
	}
	public void setPaymentSlab(List<PaymentSlabs> paymentSlab) {
		this.paymentSlab = paymentSlab;
	}
	public long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
	public long getUpdatedDate() {
		return UpdatedDate;
	}
	public void setUpdatedDate(long updatedDate) {
		UpdatedDate = updatedDate;
	}
	public Double getRegistrationFees() {
		return registrationFees;
	}
	public void setRegistrationFees(Double registrationFees) {
		this.registrationFees = registrationFees;
	}
	
	

}