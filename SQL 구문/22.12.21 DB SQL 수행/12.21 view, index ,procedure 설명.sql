-- 해당 데이터 베이스를 사용 명령. 데이터 베이스를 선택하는 구문., 
-- 일번적인 dbms의 sql 대소문자 구별하지 않음.(키워드, 테이블이름 등은) , 키워드는 대문자, 컬럼이름 데이터는 소문자로 지정.
USE shopdb;

SELECT * 
FROM membertbl;

-- View를 만듬. 이름을 가지고 확인할 수 있게 앞에 v 붙임
-- member tbl 을 가지고 아이디 , 이름을 갖고 가상의 view를 만듬 ;의미는 각 수행하는 절을 구분하는 용도.
create VIEW v_memberTBL
AS 
SELECT memberID, memberName
FROM membertbl;
-- view 않에 있는 항목에 접근. as 다음에 있는 구문을 수행 (실제 데이터를 가지고 수행하는 것이 아니라, Select 형태를 수행하는 절을 수행)
Select *
From v_membertbl;
-- 구분자 변경 
DELIMITER  //
-- begin end 실제 수행하는 기능 작성
CREATE procedure mycall()
begin
	select memberName
    From membertbl
    where memberID = 'hong';
    
    select memberName
    From membertbl
    where memberID = 'shin';
end //
DELIMITER ;

-- 포르시저 호출 view는 커리를 직접 작성하는 겂보다, 보안성과 효율성을 높이기 위해 미리 작성 형태., View 보다 내가 직접 건드리지 않아서 범위가 한계임.
call myCall();

-- -------------------------------------------
-- 임시 테이블을 하나만 만들기
-- memberTBL에서 회원이 삭제되면 삭제 회원정보를 다른 table 저장.

CREATE TABLE deleteMemberTBL (
	-- 컬럼명  데이터 타입  제약사항(not null, unique)
    memberID  char(8)  primary key,
    memberName char(4) NOT NULL,
    memberAddr VARCHAR(20)
);
-- 테이블이 각각 행에 대해서 삭제되면 동작하는 트리거
-- Old는 memberTBL 을 의미.
DELIMITER //
Create trigger trg_memberTBL
	AFTER DELETE 
    ON memberTBL
    FOR EACH ROW
BEGIN
	INSERT INTO deletemembertbl VALUES(
		OLD.memberID, OLD.memberName, OLD.memberAddr
    );
END //
DELIMITER ;

DELETE FROM membertbl
where memberID = 'hong';

select * from membertbl;
select * from deletemembertbl;
