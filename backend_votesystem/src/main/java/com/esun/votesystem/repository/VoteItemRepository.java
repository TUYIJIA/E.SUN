package com.esun.votesystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.esun.votesystem.model.VoteItem;


@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Integer> {
	
	// 找尋所有項目
	@Procedure (value = "findAllItems")
	List<VoteItem> findAllItemsProcedure();
	
	// 新增投票項目
	@Procedure (value = "addNewItem")
	Void addNewItemProcedure(@Param("itemName") String itemName);
	
	// 更新投票項目
	@Procedure (value = "updateItem")
	Void updateItemProcedure(@Param("itemNo") Integer itemNo,@Param("itemName") String itemName);
	
	// 找單一個投票項目
	@Procedure (value = "findItem")
	VoteItem findItemByNoProcedure(@Param("itemNo") Integer itemNo);
	
	// 各投票項目小計
	@Procedure(value = "calculateSubtotal")
	List<Object[]> calculateItemSubtotal();
	
	// 刪除投票項目
	@Procedure (value = "deleteItem")
	Void deleteItemProcedure(@Param("itemNo") Integer itemNo);
	

}
