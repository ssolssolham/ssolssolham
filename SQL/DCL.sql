/* #1. DBA 계정(sys or system)으로 연결 후 User 생성 */
CREATE USER bangry identified BY bangry;

/* #2. 생성된 User에 시스템 권한 / 객체(테이블, 뷰…) 권한 부여 */
-- 시스템 연결 권한 부여
GRANT CREATE SESSION TO bangry;
-- 객체 권한 부여(sys가 bangry에게 hr 스키마의 employee 객체에 대한 CRUD 권한을 부여)
GRANT SELECT, INSERT, UPDATE, DELETE ON hr.employees
TO bangry;

/* #3. 필요에 따라 User에게 부여된 권한 회수 */
REVOKE SELECT, INSERT, UPDATE, DELETE ON hr.employees
FROM bangry;


/* 롤(ROLE) : 여러 권한들의 묶음(내장 롤 / 사용자 정의 롤) */
/* #1. User에 내장 롤 부여 */
GRANT CONNECT, RESOURCE, dba TO bangry;

/* #2. 사용자 정의 롤 생성 */
CREATE ROLE my_role;
--DROP ROLE my_role;

/* #3. 시스템 권한과 객체 권한을 롤에 등록 */
GRANT CREATE SESSION TO my_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON hr.employees TO  my_role;

-- ※  모든 권한을 롤에 부여
GRANT ALL ON hr.employees TO my_role;

/* #4. User에 롤 부여 */
--GRANT CONNECT, RESOURCE TO killer;
GRANT my_role TO bangry;

/* #5. User 잠금 및 해제 */
ALTER USER bangry ACCOUNT LOCK;
ALTER USER bangry ACCOUNT UNLOCK;

/* #6. User 비밀번호 변경 */
ALTER USER killer IDENTIFIED BY killer;
--ALTER USER killer IDENTIFIED BY killer ACCOUNT UNLOCK;

/* #7. User 삭제 */
DROP USER bangry CASCADE;

/* #8. 데이터 딕셔너리로부터 유저 목록 조회 */
SELECT * FROM dba_users;

SELECT *
FROM hr.employees;

CREATE SYNONYM hremp
FOR hr.employees;

