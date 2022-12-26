-- 1.
select D.CATEGORY, D.DEPARTMENT_NAME, count(S.STUDENT_NO) as '학생수', rank() over(order by count(S.STUDENT_NO) desc)
from tb_student S inner join tb_department D on S.DEPARTMENT_NO = D.DEPARTMENT_NO -- 학생 테이블과 학과 테이블을 조인하여 학과별 학생수를 계산하기 위해 수행.
group by D.DEPARTMENT_NAME , D.CATEGORY  -- 학과 별 학생수를 나타내기 위해 그룹화, 학과 계열을 그룹화 하여 정렬.
having D.CATEGORY = '공학' OR D.CATEGORY = '자연과학'  -- 학과 계열이 공학  OR 자연과학일 때.
order by '학생수' Desc; -- 학생수 기준으로 내림차순 (점차 줄어드는 순) 정렬

select D.CATEGORY, D.DEPARTMENT_NAME, count(S.STUDENT_NO) as '학생수', rank() over(partition by D.CATEGORY order by count(S.STUDENT_NO) desc)
from tb_student S inner join tb_department D on S.DEPARTMENT_NO = D.DEPARTMENT_NO
group by D.DEPARTMENT_NAME , D.CATEGORY
having D.CATEGORY = '공학' OR D.CATEGORY = '자연과학'
order by '학생수' Desc;

-- 1. 공학 계열, 자연과학 계열 부서별 학생 많은 순으로 정렬.
	Select * from (select D.CATEGORY, D.DEPARTMENT_NAME, count(S.STUDENT_NO) as '학생수', rank() over(partition by D.CATEGORY order by count(S.STUDENT_NO) desc) AS 순위
    -- 학과 계열, 헉과 명칭, 학생 번호를 토대로 한 학생 수,
	from tb_student S inner join tb_department D on S.DEPARTMENT_NO = D.DEPARTMENT_NO -- 학생 테이블과 학과 테이블을 조인하여 학과별 학생수를 계산하기 위해 수행.
	group by D.DEPARTMENT_NAME, D.CATEGORY -- 학과 별 학생수를 나타내기 위해 그룹화, 학과 계열을 그룹화 하여 정렬.
	having D.CATEGORY = '공학' OR D.CATEGORY = '자연과학' -- 학과 계열이 공학  OR 자연과학일 때.
	order by '학생수' Desc) as top -- 학생수 기준으로 내림차순 (점차 줄어드는 순) 정렬
	where top.순위 <=2;  -- 최대 2순위까지 보여 줌.


-- 2. 예체능 과목, 논문 키워드 없으며, 담당교수가 배정되지 않음.
select C.CLASS_NAME, D.DEPARTMENT_NAME -- 과목명과 부서명을 출력.
from tb_class C Left join tb_class_professor P on C.CLASS_NO = P.CLASS_NO inner join tb_department D on D.DEPARTMENT_NO = C.DEPARTMENT_NO
-- 과목 테이블과 과목별 교수 테이블을 left 조인하여, 과목별 담당 교수가 지정되지 않는 항목을 추출하기 위해 left 조인, 과목 테이블과 부서 테이블을 조인하여. 과목 별 학과명을 출력하도록 inner join 수행.
where C.CLASS_NAME Like '%논문%' and P.PROFESSOR_NO is null and D.CATEGORY = '예체능'
-- 과목 이름에 '논문'이라는 키워드가 들어가며, 조인한 테이블에서 교수번호가 널 값이고, 카테고리가 '예체능'일 때.
order by DEPARTMENT_NAME ASC; -- 부서 이름을 내림차순으로 정렬.

-- 3. 환경조경학과 동일한 계열의 학과 교수수가 0 항목 출력
select D.CATEGORY AS 계열, D.DEPARTMENT_NAME AS 학과, count(P.PROFESSOR_NAME) as '교수 수'
-- 학과 카테고리 및 학과 이름, 교수 수를 계산하는 것을 카운트 
from tb_department D left join tb_professor P on D.DEPARTMENT_NO = P.DEPARTMENT_NO 
-- 학과 테이블과 교수 테이블을 left join 하여 교수테이블에서 학과 테이블 있는 항목을 제외한 나머지 null 값 추출 O, 학과 테이블과 교수 테이블 조인하여 해당 학과 교수를 파악 가능.
group by D.DEPARTMENT_NAME, D.CATEGORY   -- 학과별 교수 수 지정, 동일한 계열 그룹화하여 나타내기 위해
having D.CATEGORY = (select D.CATEGORY from tb_department D where D.DEPARTMENT_NAME = '환경조경학과') and  
count(P.PROFESSOR_NAME) = 0 ;
-- 환경 조경학과에 속한 해당 학과 계열을 출력, 교수 수가 0인 항목만 출력.

-- 4. 서가람 학생을 담당하는 지도교수의 관한 담당 학생들 (01년도 이후 입학생) 의 연도별 평점.
select left(G.TERM_NO,4) as 년도, format(avg(G.POINT),1) as '평점'  -- 문자열 왼쪽에서 4자리만 표현, 학점을 소수점 1자리만 표시.
from tb_professor P inner join tb_student S on P.PROFESSOR_NO = S.COACH_PROFESSOR_NO  -- 필요한 테이블을 교수, 학생, 성적 테이블을 가져옴.
inner join tb_grade G on G.STUDENT_NO = S.STUDENT_NO
where S.ENTRANCE_DATE between '2001-01-01'and current_date()  -- 입학연도가 01년도 이후로 조건이 있으므로 조건 표시.
group by P.PROFESSOR_NO, left(G.TERM_NO,4) 
-- '서가람학생을 담당하는 지도교수의 학생들의 연도별 구하기에 교수 번호를 통해 묶음. 연도 별 평점을 구해야 함므로 묶음
having P.PROFESSOR_NO = (select S.COACH_PROFESSOR_NO from tb_student S where S.STUDENT_NAME = '서가람');

-- 5. 과목을 하나도 배정받지 못한 교수가 지도 교수인 학생 수를 표시.
select P.PROFESSOR_NAME as '지도교수', count(S.STUDENT_NAME) as '학생 수' -- 교수 성함과, 해당 지도교수에게 상담받는 학생의 수 표시.
from tb_class_professor CP right join tb_professor P on CP.PROFESSOR_NO = P.PROFESSOR_NO  
inner join tb_student S on S.COACH_PROFESSOR_NO = P.PROFESSOR_NO
-- 과복별 교수 테이블과 교수테이블 right 조인하여 과목을 담당하지 않는 교수 표시, 교수 테이블과 학생 태이블을 조인하여 지도교수인 학생을 알기 위해 inner 조인 
where CP.CLASS_NO is null  -- 과목별 교수 테이블에 없는 항목 (과목을 담당하지 않는 교수)만 출력하기 위한 조건
Group by P.PROFESSOR_NAME  -- 교수별 담당 학생의 수를 표시가히 위해 묶음.
order by P.PROFESSOR_NAME ASC;  -- 정렬시 교수 성함 순으로 정렬.

-- 6. 예체능과 공학 게열의 학과이름고, 학생 수 표시.
select D.CATEGORY as '계열', D.DEPARTMENT_NAME '학과이름', count(S.STUDENT_NO) as '학생 수' -- 학교 계열, 학과 이름. 학과별 학생 수 표시.
from tb_department D left join tb_student S on D.DEPARTMENT_NO = S.DEPARTMENT_NO  
-- 학과 테이블과 학생 테이블을 left 조인하여, 학생 테이블에는 현재 해당 학과 소속 인원이 현재 없는 항목도 추려야 하기에 left 조인을 하여 학괴별 학생을 매칭 시킴.
where D.CATEGORY = '예체능' or D.CATEGORY = '공학' -- 학과 계열이 예체능. 공학 계열 학과 별 학생 수 도출.
group by D.DEPARTMENT_NAME, D.CATEGORY -- 학과 계열 및 학과 별 학생수를 나타내기 때문에 그룹으로 묶음.
order by D.CATEGORY asc, count(S.STUDENT_NO) DESC; -- 학과 계열 오름차순, 이후 학생 수가 많은 학과 순으로 정렬.

-- 7. 2001년 부터 2005년 까지의 기간에 누적 학생수 상위 3과목.
select C.CLASS_NO,C.CLASS_NAME -- 과목 번호, 과목 이름을 출력.
from tb_class C inner join tb_grade G on C.CLASS_NO = G.CLASS_NO inner join tb_student S on S.STUDENT_NO = G.STUDENT_NO
-- 과목 과목 케이블에서 성적 테이블과 과목별 성적 테이블과 조인하여 학기정보를 얻음. 학생 테이블과 성적테이블을 조인하여 학생 수를 계산 하도록 조인시킴.
where cast(Left(TERM_NO,4) As unsigned Integer) between 2001 and 2005 -- 년도를 4자리로 잘라서, 숫자로 변환한 뒤, 2001년과 2005년 사이 값 조건.
group by C.CLASS_NO -- 과목번호를 그룹화 시킴.
order by count(S.STUDENT_NO) DESC -- 학생 수를 내림차순으로 정렬.
limit 3; -- 최대 3개까지 표현.

-- 7번 다른 풀이
select C.CLASS_NO,C.CLASS_NAME,rank() over(order by count(S.STUDENT_NO) desc) as '누적 순위'	
from tb_class C inner join tb_grade G on C.CLASS_NO = G.CLASS_NO inner join tb_student S on S.STUDENT_NO = G.STUDENT_NO
where cast(Left(TERM_NO,4) As unsigned Integer) between 2001 and 2005
group by C.CLASS_NO
order by count(S.STUDENT_NO) desc;
