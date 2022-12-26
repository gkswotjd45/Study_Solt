-- insert 구문
-- 테이블안에 데이터를 삽입하기 위한 sql구분(DML)

USE sqldb;

-- DDL을 이용해서 테이블 하나 생성해 봄.

-- char 연산 빈도가 많을 때 
-- varchar 데이터 공간 확보 위주.
-- 기본적으로 트랜잭션을 적욜 할 수 없음. 
-- 트랜잭션은 DML에만 적용 , DDL에는 적용할 수 없음.

-- int가 not null이 아니여서 값이 없어도 됨.
create table testtbl1(
	id  		INT,
    userName    char(3),
    age			INT
);
-- 가장 일반적인 데이터를 삽입하는 insert 구문

-- insert into values(값);
Insert INTO testtbl1 values (1,'홍길동',20);
-- 이렇게도 할수 있어요.
Insert into testtbl1(id,age) values (2,20);
insert into testtbl1(username,age,id) values('최길동',40,3);

-- 이번에는 테이블을 색다르게 만들어 봄.
-- auto_incremnet는 숫자 타입에만 설정 (자동 증가하는 속성) ex) 홈페이지 게시글 증가. // primary key , unqiue 로 지정해야 함.
-- char(3) 최대 3글자 까지 입력. (영문, 한글 3글자)
create table testtbl2 (
id 			int	 	auto_increment primary key,
userName 	char(3),
age			int
);

-- id는 primary key로 되어 잇어 null 안됨 but, auto로 설정
Insert INTO testtbl2 values(null,'홍길동',20);
Insert INTO testtbl2 values(null,'최길동',30);

-- AUTO_Increment는 기본적으로 1부터 시작해서 1씩 증가.
-- 시작값과 증가되는 값을 변경할 수 있음. (그런데 협업에서 굳이 변경하지 않음)

select * from testtbl2;
-- 여기까지가 insert 예요.

-- 수정하려면  update 구문을 이용하려고 함. (데이터 수정)
-- update set 이용.
update testtbl2 set username = '홍길동' where age = 30;

-- 삭제하려면 delete 구문을 이용 (drop 은 아님 _ 데이터 구조를 지울때, table)
start transaction;
delete from testtbl2
where age = 30;
-- 트랙잰션 작업 무효화 수행.
rollback;

-- 삭제 관련되서는 크게 3가지가 있음
-- delete (테이블에서 row를 삭제할 때 쓰임)_transaction log를 기록 -> 트랜잭션 처리를 감안하고 수행 (시간이 오래걸림)
-- drop (table 자체를 삭제)
-- truncate (테이블에서 row를 삭제)_ transaction log를 기록하지 않음. -> 냅다 지움, 속도가 빠름.
-- 현업에서는 store procudure 을 사용하여 수행.

-- 여기까지 insert, update, delete 까지 기본 구문의 내용-----------------

-- sql 형변환

-- buytbl에서 
select *
from buytbl;

-- 전체를 집계함수를 이용해서 계산. group 함수는 아님.
select avg(amount) as '평균 구매개수'
from buytbl

-- 실수를 정수로 변환해 보아요.
-- cast 연산을 통해 형병환 (실수 -> 정수) 반올림으로 수행. 
select cast(avg(amount) as unsigned INTEGER )as '평균 구매개수'
from buytbl;


-- sekect는 데이터를 땡겨와서 결과집합으로 만듬 (꼭 테이블 형태로 찾아서 수행하는 것이 아님) // '홍길동' 값을 가져와. (위 컬럼 '홍길동' 결과값 '홍길동') -결과 집합으로 생성
select '홍길동';

-- 다양한 구분자로 되어 있는 날짜를 날짜 형식으로 바꾸어 보아요. 
-- 실제 날짜 형태로 표현 (날짜 date --로 표현) => 굳이 날짜로 바꾸는 이유는 날짜는 숫자로 잡히기 때문에 연산을 하기위해 수행서 변환함.
select cast('2022/12/22' as date);

-- select 
-- concat 문자열 연결하는 함수. 자바에서는 + 연산 이용. 해당문자열을 그대로 갖고와서 붙임
-- price가 숫자 형태이므로 문자 형태로 바꿔서 표현. amount도 숫자에서 문자로 표현하여 결합
select num, 
concat(cast(price as char(5)),'*',cast(amount as char(4))) as '단가 * 수량',
price * amount as '구매액'
from buytbl;

-- my sql (sql) 묵시적 형변환이 특이함.
-- 이걸 다 외우기는 어렵. 그래도 한번 살펴보고 이상하구나 라는 기억하는게 좋음.

-- 300 이남 , 자바는 문자열과 문자열 + 결합, mysql은 concat 을 통해 수행하므로, '100', '200' 숫자로 인식 
select '100' + '200';
select 'hello ' + 'world'; -- +는 mysql에서 숫자로 변환하는 작업 수행, ('hell0'을 인식하지 못하여  0으로 인식.
select 'hello ' + '35world'; -- 35world 을 정수형으로 변환하려고 할때 숫자로 시작하면 35는 정확히 인식하지만, 나머지 문자열은 인식하지 못함.

select concat('hello', 'world');
select concat(100,'200');

select 1 > '2hoho';  -- > 숫자 비교 연산자 이므로 , 2hoho문자열은 2로 인식함, 1> 2 false => 0으로 도출
select 0 = 'hello'; -- 숫자 비교 = < > , hello 문자열은 0 이므로 연산자는 true -> 1로 도출, 

-- 기본연산에 대해서 알아보았어요--------------------------

-- 그 다음에는 제공되는 함수들에 대해서 알아보야 해요.
-- 굉장히 많은 함수들이 mysql에 의해서 제공되요.
-- 사실은 함수를 찾아서 사용해야 (너무 많아서 외우는 것은 사실상 불가능) 
-- 하지만 기본함수는 알아두어야 함.

-- 가장 많이 사용되는 함수는 문자열 관련 함수,
-- length 문자열의 길이가 아니라, 해당 문자열을 저장하는 바이트 수.
select length('abcde'); -- 영문자 1개당 1byte
select length('홍길동'); -- 기본적으로 아스키 코드 한글은 3byte 글자당  

-- char_LENGHTH가 문자열의 길이를 알아내는 함수.
select char_length('abced');
select char_length('홍길동');

select concat('hello','World','hahah');
select concat_ws('??','hello','world','hahaha'); -- 맨 앞에 연결자를 줄 수 있음.

-- 기준 문자열에서 찾고자 하는 문자열을 찾아서 그 시작위치를 알려줌. dbms 시작위치는 1부터 시작, java는 0부터 시작.
select instr('이것은소리없는아우성','소리'); -- string 안에 해당 문자열 있니? -> 발견한 위치를 알려줌. 
select instr('이것은소리없는아우성','소학'); -- 찾고자 하는 문자열이 없으면 0

-- 숫자를 소수점 아래 자리수 까지 표현하는 방법. 3자리마다 콤마도 찍어줘요.
-- format 소수점 셋재짜리까지 표현.
select format( 12345678.141592653597,3);

-- 나머지는 무지막지한 함수들은 문제를 풀면서 찾아서 해결해 보도록 함.

-- 반올림 함수 
select round(3.141592);

-- current date (현재 시스템에서 설저되어 있는 날짜)
select curdate();
-- 현재 날짜 시간 나타냄.
select now();
-- 현재 시스템의 날짜의 연도 
select Year(curdate());

-- 날짜는 아니지만 문자열 표현. => adddate 날짜로 전환  interval 2month 날짜 간격 표현.
select adddate('2022-12-22', interval 2 month); 

-- ----- join---------
select * from usertbl;
select * from buytbl;

-- 일반적인 joim은 inner join 임.
-- 먼저 from 에서 나온 테이블을 기준. 후에 나오는 테이블 은 buytbl 과 결합, 각 테이블 id과 동일한 항목만 추림. .
select * from (usertbl inner join buytbl on usertbl.userID = buytbl.userID);
-- name이라는 컬럼은 테이블 1개만 존재해서 따로 지정 x , userid는 2개 테이블 다 존재하기 때문에 따로 명시해 줘야함.
-- 가능하면 join을 사용하지 않는 것이 아님. "표준 문법"
select usertbl.userId, name from usertbl inner join buytbl on usertbl.userID = buytbl.userID;

-- AS 이용하여 데이터를 축약해서 사용.
select U.userId, name from usertbl U inner join buytbl B on U.userID = B.userID;

-- 간단히 나타낼 수 있지만, 좋지 않은 표현.
select usertbl.userID, name 
from usertbl, buytbl
where usertbl.userid = buytbl.userID;


-- 물품이 한번이라도 구매한 기록이 있는 회원들에게 감사의 안내문을 보내야 함. // 한번이라도 구매한 사람의 이름과 주소를 입력.
select distinct usertbl.name, usertbl.addr
from usertbl inner join buytbl on usertbl.userID = buytbl.userID;
-- 잘했지만 , 그런데 이방식은 join을 사용한거임. => join은 부하가 옴.
-- 서브 쿼리를 이용해서 해결할 수도 있음.

-- exisit 해당 쿼리에 조건에 앵이 있다면, 원 select문 buttbl 과 동일한게 있으면 결과에 나타냄.
select U.name, U.addr
from usertbl U
where exists (select * from buytbl B where U.userID = B.userID);

-- script을 만들어서 드릴 꺼alter-- 
-- 이거 실행시켜서 테이블과 데이터베이스 실행.
-- 1. 학생을 기준으로 학생이름, 지역, 가입한 동아리, 동아리방을 출력하세요.
-- 순서대로 join을 하여 진행. 
select  s.stdName, s.addr, Sc.clubName, c.roomNo
from stdtbl s
	inner join stdclubtbl SC on s.stdName = SC.stdName inner join clubtbl C  on SC.clubName = C.clubName;

-- inner join에 대해서 알아봄.

-- 전체 회원의 구매기록을 조회, 단, 구매 기록이 없는 회원도 출력.
-- 이문제를 해결하기 위해서 outer join을 이용하면 됨
-- "기준 A테이블 가지고" iD를 통해 inner  join 수행 후,  왼쪽 테이블에 포함되지 않은 행을 결과행에 붙임.  => left outter join
-- "기준 A테이블 가지고" iD를 통해 inner  join 수행 후,  오른쪽 테이블에 포함되지 않은 행을 결과행에 붙임.  => rigiht outter join
-- "기준 A테이블 가지고" iD를 통해 inner  join 수행 후,  양쪽 테이블에 포함되지 않은 행을 결과행에 붙임.  => full outter join

select *
FROM usertbl U LEFT outer join buytbl B On U.userID = B.userID;

-- 한번도 구매한 적이 없는 회원의 이름과 주소를 출력하세요.
-- 모든 사용자의 구매목록을 일단 구한 다음 .. 그중 구매한 제품이 없는 사람들을 뽑아내면 됨.
-- null 아직 값이 없기 때문에 정해지지 않았기 때문에 비교할 수 없음,,, => is null
-- = 연산자를 사용할 수 없음.

select *
FROM usertbl U LEFT outer join buytbl B On U.userID = B.userID
where B.prodName is null;

-- union에 대해서 알아보기.
-- 2개의 칼럼명도 사로 다르면, 전체 테이블그조는 상위 테이블을 따라감.
-- 전체 결과 구조는 위쬭에 있는 결과집합의 구조를 따라감.
-- 상위 하위 컬럼의 개수가 동일해야 함.
-- 위쪽 결과집합의 데이터 타입이 아래쪽 결과 집합의 데이터 타입과 같아야하 함.

--  UNION (중복배제) , UnION ALL(무조건 중복에 관계없이 붙임)
(select name, height
from usertbl
where( height >180))
Union
(select userid, price
from buytbl
where price >5000);

-- 마지막 하나 남음
-- 모든 사용자를 조회하는데 전화가 없는 사람을 제외하고, 출력하세요.
select name, concat_WS('-',mobile1,mobile2)
from usertbl
where mobile1 is not null;