package com.esun.votesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esun.votesystem.dto.VoteRecordDto;
import com.esun.votesystem.model.VoteRecord;

@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {
	
	// 查詢所有投票紀錄 
	@Procedure(value = "findAllRecords")
	List<VoteRecord> findAllRecordsProcedure();
	
	// 新增投票
	@Procedure(value = "addNewRecord")
	void addNewRecordProcedure(@Param("voterName") String voterName,@Param("itemNo") Integer itemNo);
	


}
