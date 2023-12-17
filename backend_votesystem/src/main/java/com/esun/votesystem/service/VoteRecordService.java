package com.esun.votesystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esun.votesystem.dto.VoteRecordDto;
import com.esun.votesystem.repository.VoteRecordRepository;

import jakarta.transaction.Transactional;

@Service
public class VoteRecordService {

	private VoteRecordRepository voteRecordRepo;

	@Autowired
	public VoteRecordService(VoteRecordRepository voteRecordRepo) {
		this.voteRecordRepo = voteRecordRepo;
	}

	// 使用者進行投票
	@Transactional
	public Boolean addNewRecords(List<VoteRecordDto> recordDtos) {
		try {
			for (VoteRecordDto recordDto : recordDtos) {
				voteRecordRepo.addNewRecordProcedure(recordDto.getVoter(), recordDto.getItemNo());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
