
-- 제일 처음은 사용할 데이터베이스를 지정하는 거.
-- USE 데이터 베이스 명
USE employees;

-- sql문은 대소문자를 구분하지 않음, 하지만 가독성을 위해 키워드는 대문자, 일반적으로 사용자 정이어는 소문자 [관용적]사용.
-- 색이 파란색은 키워드임.
-- 데이터베이스 조회 지금 접속한 권한을 형태에서 보여줄수 있는 데이터목록 출력.
show databases;


-- 테이블 상태를 조회 
-- rows 행들이 현재 몇 개가 있는지 확인.
show table status;

-- 특정 테이블의 명세 조회 // descript
DESC departments;
-- 상위 작성한 것들은 기본적인 명령어-----------------
-- Select 구문 데이터를 추출 하기위해 사용하는 구문
-- select 컬럼명들 

Select first_name, last_name, birth_date
from employees;
-- 이거 실행하면 결과집합이 리턴됨. (결과들이 모여있는 형태)
-- ResultSet, 결과 레코드 집합, result Grid -> 테이블 형태로 간주만된 형태. / 실제 테이블은 아님.
-- 만약 결과집합에 컬럼명을 바꾸고자 한다면,
-- Alias를 이용하면 돼요. (별명, 별칭) 
-- ' ' 싱글쿼터 (공백이 있거나, 숫자로만 되어있을 경우)
Select first_name AS '나의 이름', 
		last_name AS 성, 
        birth_date AS '생년월일'
FROM employees;

-- 그러면 항상 모든 데이터를 가져오는 건가? 만약 조건절이 없으면, 당연히 모든데이터를 가져와요.
-- 만약 원하는 조건이 있다면, 조건절을 이용해야 해요.
-- where 키워드를 이용해서 수행 select from where

-- 이거 하기전에, 데이터 셋을 하나 만들어야 함.
-- 테이블 생성하고, 데이터도 insert 해 높음. (한글 처리에 조심) _ 같은 한글이지만, 코드 형식은 다 다름.

-- 이름이 김경호인 사람의 정보를 출력
Select  * From usertbl
where  name = '김경호';

 -- 1970년 이후 출생하고 신장이 182 이상인 사람의 아이디와 이름을 출력
 -- 괄호는 꼭 안써도 됨 why and 연산자 우선순위가 낮음.
Select userID, name 
from usertbl
where (birthYear >= 1970) and (height >= 182);

-- 컬럼 타입 확인 후 select 문 수행 
desc usertbl;

-- 키가 180 ~ 183인 사람의 이름과 아이디를 조회
select userID, name
From usertbl
Where (height >= 180) and (height <= 183) ;

-- 숫자의 범위를 지정할 때 Between and 사용
select userID, name
From usertbl
Where height between 180 and 183 ;

-- 지역이 경남 전남 경북 인 사람의 이름과 지역을 출력하세요
Select name, addr from usertbl
where (addr = '경남') or (addr = '전남') or (addr = '경북');

-- OR 연산자 between in 연산자 수행.
Select name, addr from usertbl
where addr in  ('경남','전남','경북');

-- 패턴 매칭 
-- 김씨성을 가진 사람들의 이름과 키를 조회하세요.
-- 와일드카드 2개 (% , _ ) :  % : 0개 이상의 문자열 의미 Ex) 자바% : 자바로 시작하는 모든 문자열 의미 (자바, 자바1, 자바가어렵) if) %자바% : 문자열 안에 자바라는 키워드가 들어간 형태. (포털사이트)
-- _ : 한개의 문자열 의미(한글자) 어떤 문자에 나오는 것에 상관없이 올수 있는 키워드. ex)자바_ :(자바킹, 자바투, 자바소) - 자바로 시작하는 3글자. => 문자열의 개수를 지정할때 사용. 자바__ : 자바로 시작하는 4글자.
-- '김%' : 이름이 김퍼센트인 사람을 출력. 따라서 = 연산자를 사용하는 것이 아니라 Like "처럼 생긴것" 을 사용.

Select name, height
from usertbl
where name like '김%';

-- 김경호보다 키가 크거나 같은 사람의 이름과 키를 출력.
select name
from usertbl
where name = '김경호';

select name, height
from usertbl
where height >= 177


select name, height
from usertbl
where height >= (select height
from usertbl
where name = '김경호');

-- 사는 지역이 경남인 사람의 키보다 크거나 같은 사람들을 조회a
-- 서브 커리 중에 여러 개의 나온 값들을 비교 any
-- 서브 커리 중에 모든 결과값보다 커야 함 all
select name, height
from usertbl
where height >= any (select  height 
from usertbl
where addr = '경남');

select name, height
from usertbl
where height >= all (select  height 
from usertbl
where addr = '경남');

-- 먼저 가입한 순서대로 회원의 이름과 가입일을 출력하세요.
-- 정렬 order by 항상 단일 sql에 가장 마지막에 위치.
-- 정령의 기본은 오름차순 날짜 과거 -> 현재  
-- 기본 값 AEc - 오름차순 DESC - 내림차순.
select name, mDate
From usertbl
order by mDate ASC;

desc usertbl;

-- 회원의 이름과 가입일 ,키 을 출력, 정령 키 순서로 큰순서를 정렬.
-- 만약 키가 같은 가입일이 빠른 순으로 정렬.
select name, mDate ,height
From usertbl
order by height DESC, mDate ASC;

-- 회원들의 거주지역이 어디인지를 출력. (중복 제거)
-- distinct (유일한, 구별되는) 중복을 제거 수행.
select distinct addr
from usertbl;

-- 회원 가입이 오래된 사람들 3명을 출력
-- LIMIT 나온 결과의 개수 제한. 3개만 출력
Select *
From usertbl
order by mDate ASC
LIMIT 3;

-- 테이블을 복사하는 가장 간단한 방법 
create table usertbl2(
select * 
from usertbl
);
-- 쉽고 간편하게 복사할 수 있지만, 제약사항은 포함이 안됨 (Primary key,Foreign key,....)

-- 여기까지가 기본 sql select 구문, 인간적으로 쉬워요.
-- grouping이 나옴. ex) 남자들의 평균 연봉, 여자들의 연봉, 서울 사람들 ~
-- 구매 테이블에서 사용자가 구매한 물품의 개수를 출력하세요.
select * 
from buytbl;

-- 각각의 사용자별로 제품은 상관 없이 모두 몇개를 지금까지 구입했는지 알고 싶음. => ~ 별 (group) 
-- 그룹한 항목들은 전체 보여주지는 않음. -> 구체적인 지시사항을 명시해야함.
select userID
from buytbl
group by userID;

-- userId는 하나로 묶일 수 있지만, prodname 경우에는 제품이 각각 존재하므로 묶을 수 없음.
select userID, prodName
from buytbl
group by userID;

-- mssql이 가지고 있는 함수 (sum)
select userID, sum(amount) as '총 구매 개수'
from buytbl
group by userID;

-- 각 사용자별 구매액의 총합을 구하고, 구매액이 큰 순서로 출력.
-- 정렬을 꼭필요한 경우가 아니면 수행하면 안됨 -> 결과를 도출하는 데 시간이 오래걸리며  DB 부하 발생.
-- 최소한의 시간을 주며 빠르게 수행
Select userID, sum(price*amount) As total
from buytbl
group by userID
order by total DESC;

-- 우리 사이트에서 한사림이 평균적으로 몇개를 구매했는지 ?
Select AVG(amount) AS '전체 평균개수'
from  buytbl

-- 가장 사용자테이블에서 가장 큰 키와 가장 작은 키의 회원 이름과 키를 출력 (max, min)
-- 서브 커리를 이용.

SELECt name, height
from usertbl
where height = (Select height 
from usertbl 
order by height DESC LIMIT 1) OR height = (Select height 
from usertbl 
order by height ASC LIMIT 1) 
;

Select name, height 
from usertbl 
where height = (select min(height) from usertbl) OR height = (select max(height) from usertbl) ;

-- userTable에서 휴대폰이 있는 사용자의 수를 출력.
-- count는 개수를 세는 함수
select count(UserID) 
FROM usertbl;

-- 휴대폰 가지고 있는 개수를 출력.
select count(mobile1) 
FROM usertbl;

-- 샤용자별 총 구매금익이 1,000 이상인  사용자의 id와 금액을 출력.
-- group by - 조건 having (where에 비슷한 역할)
select userID, sum(price*amount) As total
from buytbl
group by userid 
having total>=1000;

-- 기본적인 sql 구문 select from whrere group by having order by 순으로 진행. [해당 순서로 진행]



