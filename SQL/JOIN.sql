-- CROSS JOIN, CARDINALITY 곱
-- Oracle JOIN
SELECT e.last_name,d.department_name
FROM employees e, departments d;

-- ANSI JOIN
SELECT e.last_name,d.department_name
FROM employees e
     cross join departments d;
     
SELECT sysdate from dual;

SELECT e.employee_id, e.last_name,d.department_name 
FROM   employees e, departments d 
WHERE  e.department_id = d.department_id AND e.salary >= 3000;

SELECT e.employee_id,e.last_name,d.department_name 
FROM   employees e JOIN departments d using(department_id) 
      --on e.department_id = d.department_id?          
WHERE  e.salary >= 3000; 

SELECT e.employee_id, 
          e.last_name, 
          d.department_name, 
          l.city, 
          l.state_province, 
          c.country_name 
FROM   employees e 
         JOIN departments d 
            ON e.department_id = d.department_id 
         JOIN locations l 
            ON d.location_id = l.location_id 
         JOIN countries c 
            ON l.country_id = c.country_id; 

SELECT e.employee_id, 
          e.last_name, 
          e.salary, 
          j.job_title 
FROM   employees e, 
          jobs j 
WHERE  e.salary BETWEEN j.min_salary AND j.max_salary 
ORDER  BY e.employee_id; 

SELECT e.ename, 
          e.sal, 
          s.grade 
FROM   emp e, 
          salgrade s 
WHERE  e.sal BETWEEN s.losal AND s.hisal;

-- ANSI 표준 NON-EQUI JOIN
SELECT e.ename, 
          e.sal, 
          s.grade 
FROM   emp e 
         JOIN salgrade s 
           ON e.sal BETWEEN s.losal AND s.hisal; 

-- employees 테이블에에서 부서번호가 NULL 인 Kimberely는 
-- EQUI Join만으로는 검색되지 않음
SELECT e.first_name, d.department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id;


-- 소속부서가 없는 사원도 출력!
-- 오라클 OUTER JOIN
SELECT e.employee_id, 
          e.first_name, 
          e.last_name, 
          d.department_name 
FROM   employees e, 
          departments d 
WHERE  e.department_id = d.department_id(+); 

-- ANSI 표준 OUTER JOIN(LEFT, RIGHT, FULL 예약어 사용)
--  LEFT OUTER JOIN
SELECT e.first_name, 
          d.department_name 
FROM   employees e 
          LEFT OUTER JOIN departments d 
             ON e.department_id = d.department_id; 

-- RIGHT OUTER JOIN
SELECT e.first_name, 
          d.department_name 
FROM   employees e 
          RIGHT OUTER JOIN departments d 
             ON e.department_id = d.department_id; 

-- FULL OUTER JOIN
SELECT e.first_name, 
          d.department_name 
FROM   employees e 
          FULL OUTER JOIN departments d 
             ON e.department_id = d.department_id; 

-- 사원별 상사 검색
SELECT employee.first_name , manager.first_name
FROM EMPLOYEES employee, EMPLOYEES manager
WHERE employee.manager_id = manager.employee_id;

-- 상사가 없는 사원도 검색 시 OUTER JOIN 필요
SELECT employee.first_name , manager.first_name
FROM EMPLOYEES employee, EMPLOYEES manager
WHERE employee.manager_id = manager.employee_id(+);

SELECT employee.first_name , manager.first_name
FROM EMPLOYEES employee LEFT OUTER JOIN EMPLOYEES manager
ON employee.manager_id = manager.employee_id;

