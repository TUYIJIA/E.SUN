CREATE DATABASE E_SunTest;

USE E_SunTest;

-- 建立 vote_item 表

CREATE TABLE vote_item (
	item_no INT NOT NULL PRIMARY KEY auto_increment,
	item_name NVARCHAR(50) NOT NULL
);

-- 建立 vote_record 表

CREATE TABLE vote_record (
	record_no INT NOT NULL PRIMARY KEY auto_increment,
	voter NVARCHAR(50) NOT NULL,
	item_no INT NOT NULL,
	FOREIGN KEY (item_no) REFERENCES vote_item (item_no)
);

-- 插入 vote_item 相關數值 --

INSERT INTO vote_item (item_name) VALUES
('電腦'),
('滑鼠');

-- 插入 vote_record 相關數值 --

INSERT INTO vote_record (voter , item_no) VALUES
('Leo',1),
('Sandy',1),
('Sandy',2),
('Randy',2),
('RSY',2);

-- 建立查詢投票紀錄 storedprocedure --

DELIMITER //
CREATE PROCEDURE findAllRecords()
BEGIN
    SELECT record_no,item_no, voter
    FROM vote_record;
END //
DELIMITER;


-- 建立查詢投票項目 storedprocedure --

DELIMITER //
CREATE PROCEDURE findAllItems()
BEGIN
	SELECT item_no , item_name
	FROM vote_item ;
END //
DELIMITER;

-- 建立新增投票項目 storedprocedure

DELIMITER //
CREATE PROCEDURE addNewItem(
    IN itemName NVARCHAR(50)
)
BEGIN
    INSERT INTO vote_item(item_name) VALUES (itemName);
END //
DELIMITER;


-- 建立新增投票紀錄 storedprocedure 

DELIMITER //
CREATE PROCEDURE addNewRecord(
    IN voterName NVARCHAR(50),
		IN itemNo INT
)
BEGIN
    INSERT INTO vote_record(voter,item_no) VALUES (voterName,itemNo);
END //
DELIMITER;


-- 找單一投票項目 storedprocedure 

DELIMITER //
CREATE PROCEDURE findItem(
	IN itemNo INT
)
BEGIN
    SELECT item_no,item_name 
		FROM vote_item 
		WHERE item_no = itemNo;
END //
DELIMITER;


-- 更新投票項目 storedprocedure 

DELIMITER //
CREATE PROCEDURE updateItem(
	IN itemNo INT,
  IN itemName NVARCHAR(50)
)
BEGIN
    UPDATE vote_item 
		SET item_name = itemName
		WHERE item_no = itemNo;
END //
DELIMITER;


--  建立所有投票項目與之票數小計 storedprocedure

DELIMITER //
CREATE PROCEDURE calculateSubtotal()
BEGIN
	SELECT vi.item_no, vi.item_name, COUNT(vr.record_no) AS subtotal
	FROM vote_item vi
	LEFT JOIN vote_record vr ON vi.item_no = vr.item_no
	GROUP BY vi.item_no, vi.item_name;
END //
DELIMITER;



-- 建立刪除單一投票項目 storedprocedure

DELIMITER //
CREATE PROCEDURE deleteItem(
	IN itemNo INT
)
BEGIN

	DECLARE relatedData INT;
	
	SELECT COUNT(*) INTO relatedData 
	FROM vote_record 
	WHERE item_no = itemNo;
	
	IF relatedData > 0 THEN

		DELETE FROM vote_record
		WHERE item_no = itemNo;
		
	END IF;
	
	DELETE 
	FROM vote_item
	WHERE item_no = itemNo;
END //
DELIMITER;
