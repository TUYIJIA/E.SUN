package com.esun.votesystem.dto;


public class VoteRecordDto {
	
	private String voter;
	
	private Integer itemNo;
	
	private String itemName;
	
		
	public String getVoter() {
		return voter;
	}

	public void setVoter(String voter) {
		this.voter = voter;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public VoteRecordDto() {	
	}

}
