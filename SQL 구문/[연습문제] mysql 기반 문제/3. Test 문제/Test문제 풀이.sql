
-- 1. 사학과의 학생수 목록 출력
Select count(s.STUDENT_NO) as '학생수'  -- 학생 번호를 통한 학생 수 출력.
from tb_student s  -- 학생 테이블 이용.
where s.DEPARTMENT_NO = 
(Select D.DEPARTMENT_NO from tb_department D where D.DEPARTMENT_NAME = '사학과')
-- 학과 테이블에서 사학과에 다니는 학과 명을 출력.
and year(s.ENTRANCE_DATE) = 2001;
-- 입학년도가 2001년에 입학한 학생 위주.

-- 2. 공학인 학과에 입학 정원 20 ~ 30 이상 학과 목록 표시. 
Select D.CATEGORY as '계열', D.DEPARTMENT_NAME as '학과이름', D.CAPACITY as '정원' 
-- 학과 계열, 학과이름, 정원 목록 출력.
from tb_department D  -- 학과테이블 이용.
where D.CATEGORY = '공학' and D.CAPACITY between 20 and 30 
order by D.DEPARTMENT_NAME ASC;

-- 3. '학'자가 들어간 계열의 소속 학과의 개수
select D.CATEGORY, count(D.DEPARTMENT_NAME) as '학과수'
-- 학과 계열, 학과수 출력 
from tb_department D
where D.CATEGORY like '%학%' -- 학과 계열에 '학'이라는 키워드가 들어가 있으면,
group by D.CATEGORY; -- 학과계열을 그룹으로 묶음.

-- 4. 영어영문학과 교수 이름, 주소, 나이가 많은 순으로 정렬.
select P.PROFESSOR_NAME as '교수이름', cast(left(P.PROFESSOR_SSN,2) as unsigned integer) as 출생년도, P.PROFESSOR_ADDRESS as '주소'
-- 교수 이름, 출생년도 앞자리 2자리, 주소 출력.
from tb_professor P
where P.DEPARTMENT_NO = (select D.DEPARTMENT_NO from tb_department D where D.DEPARTMENT_NAME = '영어영문학과')
-- 조건이 학과테이블에서 영어영문학과에 속한 학과 번호에 해당하는 자를 출력.
order by 출생년도 ASC; -- 정렬은 출생년도 오름차순으로 정렬.

-- 5. 국어 국문학과에 다니는 서울에 거주하고 이쓴 학생 정보 출력.
select S.DEPARTMENT_NO as '학과이름', S.STUDENT_NAME as '학생이름', Case S.ABSENCE_YN when S.ABSENCE_YN  = 'Y'  then '휴학' when S.ABSENCE_YN = 'N' 
then '정상' end as '휴학여부'
from tb_student S
where S.DEPARTMENT_NO = (Select D.DEPARTMENT_NO from tb_department D where D.DEPARTMENT_NAME = '국어국문학과')  and 
Left(S.STUDENT_ADDRESS,2) = '서울';

 -- 6. 80년생인 여학생 중 성이 김 씨인 학생 정보를 출력.
select replace (s.STUDENT_SSN,right(s.STUDENT_SSN,6),'******') as '주민등록번호' ,s.STUDENT_NAME as '학생이름'
-- 주민등록번호 우측에서 6자리를 *으로 대체함, 학생이름 출력.
from tb_student s
where cast(Left(s.STUDENT_SSN,2) as unsigned integer) = 80 and 
cast(mid(s.STUDENT_SSN,8,1)as unsigned integer) = 2 -- 주민등록번호를 좌측 2번째가 80인 경우, 주민등록번호 2로 수행하는 경우.
and s.STUDENT_NAME like '김%'
-- 학생 이름이 김으로 시작하는 경우.

-- 7. 계열이 예체능인 학과의 정원별 강의실 크기 조정,
select D.DEPARTMENT_NAME as '학과이름', D.CAPACITY as '정원', case when D.CAPACITY > 40 then '대강의실' 
when D.CAPACITY >= 30 then '중강의실' else '소강의실' end as '강의실 크기'
-- 수용인원별 강의실 크기 구문 지정.
from tb_department D
where D.CATEGORY = '예체능' -- 학과 계열이 예체능인 경우.
order by D.CAPACITY DESC;  -- 수용인원 별로 내림차순으로 정렬.

-- 8. 주소지가 경기 또는 인천인 학생들의 입학기간 출력.
select s.STUDENT_NAME as 학생이름 , date_format(s.ENTRANCE_DATE,"%y년 %m월 %d일") as '입학일자',
concat(timestampdiff(Year,s.ENTRANCE_DATE,current_date()),'년') as '입학후기간(년)'
from tb_student s
where left(s.STUDENT_ADDRESS,2) in('경기','인천') and year(s.ENTRANCE_DATE) between 1900 and 1999
order by '입학후기간(년)' Desc, 학생이름 ASC;
