package com.application.chitfunds.entites;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "temporaryGroupMapping")
public class TemporaryGroupMapping {
    
    private String _id;
    private Long mappingId;
    private String groupId;
    private String groupName;
    private String customerName;
    private String customerId;
    private String contactNumber;
    private String modeOfSubscription;
    private String prizedStatus;
    private String collectionRoute;
    private String collectionPincode;
    private Double receivedAmount;
    private Double outStandingAmount;
    private Long createdDate;
    private Long updatedDate;
    private String joiningDate;
    private Long lastPayedDate;
    private String chitId;
    private String ticketNumber;
    private Integer installment;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getModeOfSubscription() {
        return modeOfSubscription;
    }

    public void setModeOfSubscription(String modeOfSubscription) {
        this.modeOfSubscription = modeOfSubscription;
    }

    public String getPrizedStatus() {
        return prizedStatus;
    }

    public void setPrizedStatus(String prizedStatus) {
        this.prizedStatus = prizedStatus;
    }

    public String getCollectionRoute() {
        return collectionRoute;
    }

    public void setCollectionRoute(String collectionRoute) {
        this.collectionRoute = collectionRoute;
    }

    public String getCollectionPincode() {
        return collectionPincode;
    }

    public void setCollectionPincode(String collectionPincode) {
        this.collectionPincode = collectionPincode;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Double getOutStandingAmount() {
        return outStandingAmount;
    }

    public void setOutStandingAmount(Double outStandingAmount) {
        this.outStandingAmount = outStandingAmount;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Long getLastPayedDate() {
        return lastPayedDate;
    }

    public void setLastPayedDate(Long lastPayedDate) {
        this.lastPayedDate = lastPayedDate;
    }

    public String getChitId() {
        return chitId;
    }

    public void setChitId(String chitId) {
        this.chitId = chitId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Integer getInstallment() {
        return installment;
    }

    public void setInstallment(Integer installment) {
        this.installment = installment;
    }
}
