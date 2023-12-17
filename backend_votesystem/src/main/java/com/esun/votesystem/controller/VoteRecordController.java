package com.esun.votesystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.esun.votesystem.dto.VoteRecordDto;
import com.esun.votesystem.service.VoteRecordService;

@RestController
public class VoteRecordController {

	private VoteRecordService voteRecordService;

	@Autowired
	public VoteRecordController(VoteRecordService voteRecordService) {
		this.voteRecordService = voteRecordService;
	}

	@PostMapping("/voteRecord")
	public ResponseEntity<?> addNewRecord(@RequestBody List<VoteRecordDto> voteRecordDtos) {
		Boolean addResult = voteRecordService.addNewRecords(voteRecordDtos);
		if (addResult) {
			return ResponseEntity.status(HttpStatus.OK).body("投票成功");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("投票失敗");
		}
	}	
}
