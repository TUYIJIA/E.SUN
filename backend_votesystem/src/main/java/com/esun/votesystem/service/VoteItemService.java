package com.esun.votesystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esun.votesystem.dto.VoteItemDto;
import com.esun.votesystem.model.VoteItem;
import com.esun.votesystem.repository.VoteItemRepository;
import jakarta.transaction.Transactional;

@Service
public class VoteItemService {

	private VoteItemRepository voteItemRepo;

	@Autowired
	public VoteItemService(VoteItemRepository voteItemRepo) {
		this.voteItemRepo = voteItemRepo;
	}

	// 查詢所有投票項目
	@Transactional
	public List<VoteItemDto> findAllItems() {
		List<VoteItem> itemList = voteItemRepo.findAllItemsProcedure();

		List<VoteItemDto> itemDtos = new ArrayList<>();
		for (VoteItem item : itemList) {
			VoteItemDto voteItemDto = new VoteItemDto(item.getItemNo(), item.getItemName());
			itemDtos.add(voteItemDto);
		}
		return itemDtos;
	}

	// 查詢單一投票項目
	@Transactional
	public VoteItemDto findItemByNo(Integer itemNo) {
		if (itemNo != null) {
			VoteItem result = voteItemRepo.findItemByNoProcedure(itemNo);
			return new VoteItemDto(result.getItemNo(), result.getItemName());
		} else {
			return null;
		}

	}

	// 新增投票項目
	@Transactional
	public Boolean addNewItem(VoteItemDto itemDto) {
		try {
			voteItemRepo.addNewItemProcedure(itemDto.getItemName());
			return true;
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}

	// 更新投票項目
	@Transactional
	public Boolean updateItem(VoteItemDto itemDto) {
		try {
			voteItemRepo.updateItemProcedure(itemDto.getItemNo(), itemDto.getItemName());
			return true;
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}

	// 各項目票數
	@Transactional
	public List<VoteItemDto> calculateItemSubtotal() {
		List<Object[]> result = voteItemRepo.calculateItemSubtotal();

		List<VoteItemDto> itemDtoList = new ArrayList<>();

		for (Object[] objArray : result) {
			VoteItemDto itemDto = new VoteItemDto();
			itemDto.setItemNo((Integer) objArray[0]);
			itemDto.setItemName((String) objArray[1]);
			itemDto.setSubtotal(((Integer) ((Long) objArray[2]).intValue()));
			itemDtoList.add(itemDto);
		}
		return itemDtoList;
	}
	
	// 刪除某一投票項目
	@Transactional
	public Boolean deleteItem(Integer itemNo) {
		try {
			voteItemRepo.deleteItemProcedure(itemNo);
			return true;
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}

}