package com.application.chitfunds.entites;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "temporaryGroup")
public class TemporaryGroup {

    private Long groupId;
    private String _id;
    private String branchName;
    private String groupType;
    private String groupName;
    private String schemeId;
    private Long lauctionDate;
    private String auctionFromDate;
    private String auctioToDate;
    private String startingDate;
    private Double companyCommissionPercentage;
    private String FDNumber;
    private Double totalFD;
    private Double rateOfInterest;
    private String fDDate;
    private Double maturityAmount;
    private String FDBank;
    private String FDBranch;
    private String PSONumber;
    private String PSODate;
    private Long createdDate;
    private Long lastUpdatedDate;
    private String schemeName;
    private Integer vacantCount;
    private Integer monthlyInstallment;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public Long getLauctionDate() {
        return lauctionDate;
    }

    public void setLauctionDate(Long lauctionDate) {
        this.lauctionDate = lauctionDate;
    }

    public String getAuctionFromDate() {
        return auctionFromDate;
    }

    public void setAuctionFromDate(String auctionFromDate) {
        this.auctionFromDate = auctionFromDate;
    }

    public String getAuctioToDate() {
        return auctioToDate;
    }

    public void setAuctioToDate(String auctioToDate) {
        this.auctioToDate = auctioToDate;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public Double getCompanyCommissionPercentage() {
        return companyCommissionPercentage;
    }

    public void setCompanyCommissionPercentage(Double companyCommissionPercentage) {
        this.companyCommissionPercentage = companyCommissionPercentage;
    }

    public String getFDNumber() {
        return FDNumber;
    }

    public void setFDNumber(String fDNumber) {
        FDNumber = fDNumber;
    }

    public Double getTotalFD() {
        return totalFD;
    }

    public void setTotalFD(Double totalFD) {
        this.totalFD = totalFD;
    }

    public Double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(Double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public String getfDDate() {
        return fDDate;
    }

    public void setfDDate(String fDDate) {
        this.fDDate = fDDate;
    }

    public Double getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public String getFDBank() {
        return FDBank;
    }

    public void setFDBank(String fDBank) {
        FDBank = fDBank;
    }

    public String getFDBranch() {
        return FDBranch;
    }

    public void setFDBranch(String fDBranch) {
        FDBranch = fDBranch;
    }

    public String getPSONumber() {
        return PSONumber;
    }

    public void setPSONumber(String pSONumber) {
        PSONumber = pSONumber;
    }

    public String getPSODate() {
        return PSODate;
    }

    public void setPSODate(String pSODate) {
        PSODate = pSODate;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public Integer getVacantCount() {
        return vacantCount;
    }

    public void setVacantCount(Integer vacantCount) {
        this.vacantCount = vacantCount;
    }

    public Integer getMonthlyInstallment() {
        return monthlyInstallment;
    }

    public void setMonthlyInstallment(Integer monthlyInstallment) {
        this.monthlyInstallment = monthlyInstallment;
    }
}
