package com.rbc._3m00.dto;

public class MobileNotice {
	
	private String mobileNoticeHeader = "";
	
	private String mobileNoticeDescription = "";
	
	private String mobileNoticePlatform = "";
	
	private String mobileNoticeStartDate = "";
	
	private String mobileNoticeEndDate = "";

	public String getMobileNoticeHeader() {
		return mobileNoticeHeader;
	}

	public void setMobileNoticeHeader(String mobileNoticeHeader) {
		this.mobileNoticeHeader = mobileNoticeHeader;
	}

	public String getMobileNoticeDescription() {
		return mobileNoticeDescription;
	}

	public void setMobileNoticeDescription(String mobileNoticeDescription) {
		this.mobileNoticeDescription = mobileNoticeDescription;
	}

	public String getMobileNoticePlatform() {
		return mobileNoticePlatform;
	}

	public void setMobileNoticePlatform(String mobileNoticePlatform) {
		this.mobileNoticePlatform = mobileNoticePlatform;
	}

	public String getMobileNoticeStartDate() {
		return mobileNoticeStartDate;
	}

	public void setMobileNoticeStartDate(String mobileNoticeStartDate) {
		this.mobileNoticeStartDate = mobileNoticeStartDate;
	}

	public String getMobileNoticeEndDate() {
		return mobileNoticeEndDate;
	}

	public void setMobileNoticeEndDate(String mobileNoticeEndDate) {
		this.mobileNoticeEndDate = mobileNoticeEndDate;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Header = " + this.getMobileNoticeHeader() + "\n")
		.append("Description = " + this.getMobileNoticeDescription() + "\n")
		.append("Platform = " + this.getMobileNoticePlatform() + "\n")
		.append("Start Date = " + this.getMobileNoticeStartDate() + "\n")
		.append("End Date = " + this.getMobileNoticeEndDate() + "\n");
		
		return sb.toString();
	}
	
	

}
