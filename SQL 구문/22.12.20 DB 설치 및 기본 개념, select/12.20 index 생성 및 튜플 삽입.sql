-- 새로운 테이블을 하나 만들어서
-- 아래에서 가져온 데이터를 입력할꺼에요.
-- 테이블부터 만들어야 함.
-- 대소문자를 구분하지 않음, 
create table IndexTBL(
	first_name  Varchar(14), 
    last_name  VARCHAR(16),
    hire_date date
);
Insert into indexTBL
	select first_name, last_name, hire_date
	From employees.employees
	limit 500;
-- 들어갔는지 확인
-- * 모든 컬럼 호출
select *  
FROM IndexTBL;
-- 인덱스 생성 방법 
Create index Indexidx_indexTBL_firstname
on  indexTBL(first_name); 

ALTER TABLE indextbl DROP idx_indexTBL_firstname;


-- 데이터가 들어갔고, 아직 index는 설정하지 않음.
-- 특정 사람을 검색해 보아요.
-- 이름이 first_name이 marry란 사람을 찾아서 이름과 성, 입사일을 출력하세오.
select first_name, last_name, hire_date
from indextbl
Where first_name = 'Mary' 




