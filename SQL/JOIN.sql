-- CROSS JOIN, CARDINALITY ��
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

-- ANSI ǥ�� NON-EQUI JOIN
SELECT e.ename, 
          e.sal, 
          s.grade 
FROM   emp e 
         JOIN salgrade s 
           ON e.sal BETWEEN s.losal AND s.hisal; 

-- employees ���̺����� �μ���ȣ�� NULL �� Kimberely�� 
-- EQUI Join�����δ� �˻����� ����
SELECT e.first_name, d.department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id;


-- �ҼӺμ��� ���� ����� ���!
-- ����Ŭ OUTER JOIN
SELECT e.employee_id, 
          e.first_name, 
          e.last_name, 
          d.department_name 
FROM   employees e, 
          departments d 
WHERE  e.department_id = d.department_id(+); 

-- ANSI ǥ�� OUTER JOIN(LEFT, RIGHT, FULL ����� ���)
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

-- ����� ��� �˻�
SELECT employee.first_name , manager.first_name
FROM EMPLOYEES employee, EMPLOYEES manager
WHERE employee.manager_id = manager.employee_id;

-- ��簡 ���� ����� �˻� �� OUTER JOIN �ʿ�
SELECT employee.first_name , manager.first_name
FROM EMPLOYEES employee, EMPLOYEES manager
WHERE employee.manager_id = manager.employee_id(+);

SELECT employee.first_name , manager.first_name
FROM EMPLOYEES employee LEFT OUTER JOIN EMPLOYEES manager
ON employee.manager_id = manager.employee_id;

