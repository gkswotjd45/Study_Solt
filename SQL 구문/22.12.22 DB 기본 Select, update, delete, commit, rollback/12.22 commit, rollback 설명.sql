
use sqldb;

-- 자바 프로그램은 트랜잭션 설정은 다르게 해야함, MYSQL에서 수행하는 방법.
start transaction;
select *
from usertbl;
Delete from usertbl;
 ROLLBACK;

-- COMMIT (위에서 작업한  SQL구문을 정말로 적용), 
-- ROLLBACK (트랜잭션으로 설정된 작업을 무시) 
-- 이게 나오는 순간 트랜잭션 종료 (하나의 ㅌ랜잭션 설정)
-- 무조건 모든 트랜잭션을 설정하면 과부하게 발생, 적절하게 설정하여 수행.

select * 
from usertbl;


