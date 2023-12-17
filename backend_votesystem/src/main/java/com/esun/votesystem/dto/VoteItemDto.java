package com.esun.votesystem.dto;

public class VoteItemDto {

	private Integer itemNo;

	private String itemName;

	private Integer subtotal;

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

	public Integer getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}

	public VoteItemDto() {
	}

	public VoteItemDto(Integer itemNo, String itemName) {
		this.itemNo = itemNo;
		this.itemName = itemName;
	}

	public VoteItemDto(Integer itemNo, String itemName, Integer subtotal) {
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.subtotal = subtotal;
	}
}
