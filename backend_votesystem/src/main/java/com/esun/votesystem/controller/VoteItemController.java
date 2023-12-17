package com.esun.votesystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.esun.votesystem.dto.VoteItemDto;
import com.esun.votesystem.service.VoteItemService;

@RestController
public class VoteItemController {

	private VoteItemService voteItemService;

	@Autowired
	public VoteItemController(VoteItemService voteItemService) {
		this.voteItemService = voteItemService;
	}

	// 查詢所有投票項目
	@GetMapping("/voteItems")
	public ResponseEntity<?> findAllItems() {
		List<VoteItemDto> result = voteItemService.findAllItems();
		if (result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("尚無投票項目");
		}
	}

	// 查詢單一投票項目
	@GetMapping("/voteItem/{itemNo}")
	public ResponseEntity<?> findItemByNo(@PathVariable Integer itemNo) {
		VoteItemDto result = voteItemService.findItemByNo(itemNo);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("查無投票項目");
		}
	}

	// 新增投票項目
	@PostMapping("/voteItem")
	public ResponseEntity<?> addNewItem(@RequestBody VoteItemDto itemDto) {
		Boolean addResponse = voteItemService.addNewItem(itemDto);
		if (addResponse) {
			return ResponseEntity.status(HttpStatus.OK).body("新增成功");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("新增失敗");
		}
	}

	// 更新投票項目
	@PutMapping("/voteItem/{itemNo}")
	public ResponseEntity<?> updateItem(@RequestBody VoteItemDto itemDto) {
		Boolean updateResponse = voteItemService.updateItem(itemDto);
		if (updateResponse) {
			return ResponseEntity.status(HttpStatus.OK).body("更新成功");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("更新失敗");
		}
	}

	// 顯示各項目累計票數狀況
	@GetMapping("/voteItems/state")
	public ResponseEntity<?> getItemState() {
		List<VoteItemDto> result = voteItemService.calculateItemSubtotal();
		if (result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("尚無投票紀錄");
		}
	}
	// 刪除單一投票項目
	@DeleteMapping("/voteItem/{itemNo}")
	public ResponseEntity<?> deleteItem(@PathVariable Integer itemNo) {
		Boolean deleteResponse = voteItemService.deleteItem(itemNo);
		if (deleteResponse) {
			return ResponseEntity.status(HttpStatus.OK).body("刪除成功");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("刪除失敗");
		}
	}
}
