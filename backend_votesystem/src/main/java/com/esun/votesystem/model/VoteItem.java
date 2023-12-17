package com.esun.votesystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table (name = "vote_item")
public class VoteItem {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "item_no")
	private Integer itemNo;
	
	@Column (name = "item_name")
	private String itemName;
	
	@Transient
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
	
	

}
