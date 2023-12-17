Drop table employee
CREATE table employee(
	 emp_id int not null primary key auto_increment,
	name varchar(20) NOT NULL,
    email varchar(50) NULL,
    floor_seat_seq int NULL,
    FOREIGN KEY (floor_seat_seq) REFERENCES seating_chart(floor_seat_seq)
)auto_increment=10000;
	 
CREATE table seating_chart(
	 floor_seat_seq int  not null primary key auto_increment ,
	 floor_no int not null,
	 seat_no int not null
);
insert into seating_chart(floor_no, seat_no) values
(1,1),
(1,2),
(1,3),
(1,4),
(2,1),
(2,2),
(2,3),
(2,4),
(3,1),
(3,2),
(3,3),
(3,4),
(4,1),
(4,2),
(4,3),
(4,4)


insert into employee(name, email, floor_seat_seq)values
('王曉明','aa@example.com',2),
('王美美','bb@example.com',6),
('陳小春','cc@example.com',null),
('吳大華','dd@example.com',null),
('蔡二林','ee@example.com',10),
('謝好好','ff@example.com',14),
('蔡影','gg@example.com',9)

--建立找尋所有座位的 storedProcedure --

DELIMITER //
CREATE PROCEDURE findAllSeats()
BEGIN
	SELECT  floor_seat_seq , floor_no , seat_no
	FROM seating_chart ;
END //
DELIMITER;

CALL findAllSeats();

--尋找所有員工--

DROP PROCEDURE findAllEmp
DELIMITER //
CREATE PROCEDURE findAllEmp()
BEGIN
	SELECT  emp_id , email , floor_seat_seq
	FROM employee ;
END //
DELIMITER;

CALL findAllEmp();


DROP PROCEDURE updateEmpSeat
DELIMITER //

CREATE PROCEDURE updateEmpSeat(
    IN empId INT,
    IN newFloorSeatSeq INT,
    OUT is_updated BOOLEAN
)
BEGIN
    DECLARE row_affected INT;

    UPDATE employee
    SET floor_seat_seq = newFloorSeatSeq
    WHERE emp_id = empId;

    SET row_affected = ROW_COUNT();

    IF row_affected > 0 THEN
        SET is_updated = TRUE;
    ELSE
        SET is_updated = FALSE;
    END IF;
END //

DELIMITER ;

DELIMITER //

SET @emp_id = 10000; -- 設置要更新的員工 ID
SET @new_floor_seat_seq = 1; -- 設置新的座位序號

CALL updateEmpSeat(@emp_id, @floor_seat_seq, @is_updated);

SELECT @is_updated; -- 查看是否更新成功的標誌
	