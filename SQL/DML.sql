INSERT INTO departments
            (department_id,department_name,manager_id,location_id)
VALUES (900,'KOSTA',NULL,NULL);

SELECT *
FROM departments;

rollback;

INSERT INTO  departments(department_id,department_name,manager_id,location_id)
VALUES (departments_seq.NEXTVAL,'KOSTA',NULL,NULL);
-- Data-Dictionary : OS-Registry
SELECT *
FROM user_sequences;

SELECT *
FROM user_constraints;

SELECT *
FROM SYS.user_constraints;

SELECT *
FROM user_tables;

CREATE TABLE dept2
as select *
  from departments
  where 0 = 1;
  
insert into dept2 
SELECT *
from departments;

select *
from dept2;

UPDATE EMP SET SAL = SAL * 1.1 WHERE DEPTNO = 20;
SELECT SAL, SAL * 1.1
FROM EMP
WHERE DEPTNO = 20;
ROLLBACK;

-- DELETE
DELETE FROM departments 
WHERE  department_name LIKE '%KOSTA%'; 

DELETE FROM departments 
WHERE  department_name LIKE '%KOSTA%'; 

DELETE FROM employees 
WHERE  salary > (SELECT AVG(salary) 
                       FROM   employees); 
                       
SELECT *
FROM user_sequences;

INSERT INTO EMPLOYEES(EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,
       PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT
       ,MANAGER_ID,DEPARTMENT_ID) 
       VALUES(employees_seq.NEXTVAL,'ศฃมุ','นฺ','paskal1234@naver.com'
       ,'010-8379-8700',sysdate,'IT_PROG',20000,0.35,103,60);

COMMIT;
SELECT *
FROM employees;

