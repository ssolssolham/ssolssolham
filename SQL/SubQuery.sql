SELECT *
FROM employees
WHERE salary = (SELECT salary 
                FROM employees
                WHERE LOWER(last_name) = 'seo')
                and LOWER(last_name) != 'seo';
                
SELECT *
FROM employees
WHERE salary > (SELECT AVG(salary)
                FROM employees);
                
SELECT *
FROM emp
WHERE job IN (SELECT job FROM emp WHERE deptno = 30);

SELECT * FROM emp
WHERE sal<ANY(SELECT sal FROM emp WHERE deptno = 30);

SELECT dname,deptno FROM dept WHERE EXISTS
(SELECT * FROM EMP WHERE emp.deptno = 10);

-- IN ������ Ȱ��
-- 30�� �μ��� �Ҽӵ� ������ ������ ������ ������ ��ü ������ ��ȸ
SELECT last_name, 
          job_id, 
          department_id 
FROM   employees 
WHERE  job_id IN (SELECT DISTINCT job_id 
                       FROM   employees 
                       WHERE  department_id = 30);
                       
-- ANY ������ Ȱ��
-- 30�� �μ��� �ּұ޿��� ����  �� ���� �޿��� �޴� ��ü ������ ��ȸ
SELECT * 
FROM   employees 
WHERE  salary > ANY (SELECT salary 
                             FROM   employees 
                             WHERE  department_id = 30);
                    
-- ALL ������ Ȱ��
-- 30�� �μ��� �ִ�޿��� ���� �� ���� �޿��� �޴� ��ü ������ ��ȸ
SELECT * 
FROM   employees 
WHERE  salary > ALL (SELECT salary 
                            FROM   employees 
                            WHERE  department_id = 30);

-- EXISTS ������ Ȱ��
-- ���������� ��� ������ ���� ��ȸ
SELECT * 
FROM   employees 
WHERE  EXISTS(SELECT * 
                     FROM   departments 
                     WHERE  department_id = 30)
           AND department_id = 30;
           
-- �μ��� �ּұ޿��� ����
SELECT * 
FROM   employees 
WHERE  ( department_id, salary ) IN(SELECT department_id, 
                                                         MIN(salary) 
                                              FROM   employees 
                                              GROUP  BY department_id) 
ORDER  BY department_id;
           

SELECT ROWID,ROWNUM,LAST_NAME
FROM employees;


-- ���̺��� ���� ���̶� ���� �ٸ� ROWNUM�� ���� �� �ִ�
SELECT ROWNUM, employee_id
FROM   employees;

SELECT ROWNUM, employee_id
FROM   employees
ORDER BY employee_id DESC;

SELECT *
FROM   employees
WHERE  ROWNUM > 0;


-- ù��° ���� rownum�� 1�̹Ƿ�
-- 1 > 1�� false�� �Ǿ� rownum�� ���̻� �������� ������, �ϳ��� �൵ ��ȯ���� ����
SELECT *
FROM   employees
WHERE  ROWNUM > 1;

SELECT ROWNUM "R"
FROM employees;

-- ù��° 10����(����)���� ��ȸ�� ���
-- ù��° ���� rownum�� 1�̹Ƿ�
-- 1 <= 10�� true�� �Ǿ� ù��° �࿡ 1�� �Ҵ�ǰ� rownum�� 2�� ����
SELECT *
FROM   employees
WHERE  ROWNUM <= 10;
 

/* Ư�� �÷��� �������� �����Ͽ� ���� 5��(����)�� ��ȸ�ϰ��� �Ѵٸ� */
-- ��)��ü ����� �޿������� 5�� ��������
-- ��ü �޿� ������ �ƴ� ó�� 5��ȿ����� �޿������� ��
-- �߸��� ����
SELECT first_name, salary
FROM   employees
WHERE  ROWNUM <= 5
ORDER  BY salary DESC;

-- FROM������ ��������(Inline View)�� ����ؾ� �Ѵ�
SELECT * 
FROM   (SELECT * 
           FROM   employees 
           ORDER  BY salary DESC) 
WHERE  ROWNUM <= 5;

-- �޿������� 10 ~ 20 ����
SELECT page, 
          employee_id, 
          first_name, 
          salary 
FROM   (SELECT CEIL(ROWNUM / 10) page, 
                     employee_id, 
                     first_name, 
                     salary 
           FROM   (SELECT ROWNUM, 
                                employee_id, 
                                first_name, 
                                salary 
                      FROM   employees 
                      ORDER  BY salary DESC)) 
WHERE  page = 1; 

SELECT *
FROM employees e INNER JOIN departments d 
     on e.department_id = d.department_id
ORDER BY e.employee_id;


SELECT DISTINCT e.employee_id,e.last_name
FROM employees e LEFT OUTER JOIN employees e2
     on e.employee_id = e2.manager_id
WHERE e2.employee_id IS NOT NULL;

SELECT job_id,MAX(salary),MIN(salary),SUM(salary),AVG(salary)
FROM employees
GROUP BY job_id;

SELECT job_id,COUNT(job_id)
FROM employees
GROUP BY job_id;

SELECT COUNT(*)
FROM (SELECT manager_id 
      FROM employees
      WHERE manager_id IS NOT NULL
      GROUP BY manager_id);
      
SELECT MAX(salary) - MIN(salary)
FROM employees;

SELECT e.last_name,manager_id,salary
FROM employees e INNER JOIN jobs j ON e.job_id = j.job_id
WHERE manager_id IS NOT NULL AND j.min_salary >= 6000
ORDER BY salary desc;

SELECT *
FROM jobs;

SELECT DISTINCT d.department_name,l.location_id,TRUNC(s."average",2),s."count"
FROM employees e 
     INNER JOIN departments d 
     on e.department_id = d.department_id 
     INNER JOIN locations l
     on d.location_id = l.location_id
     INNER JOIN  (SELECT department_id,AVG(salary) "average",COUNT(department_id) "count"
      FROM employees GROUP BY department_id)  s
      ON e.department_id = s.department_id;
      
SELECT (SELECT COUNT(*) FROM employees) "�� �ο�",
      (SELECT COUNT(*) FROM employees 
       WHERE TO_CHAR(hire_date,'YYYY') = '1995') "1995",
       (SELECT COUNT(*) FROM employees 
       WHERE TO_CHAR(hire_date,'YYYY') = '1996') "1996",
       (SELECT COUNT(*) FROM employees 
       WHERE TO_CHAR(hire_date,'YYYY') = '1997') "1997",
       (SELECT COUNT(*) FROM employees 
       WHERE TO_CHAR(hire_date,'YYYY') = '1998') "1998"
FROM employees;

SELECT department_id,job_id,SUM(salary) 
FROM employees
GROUP BY department_id,job_id
ORDER BY department_id desc;

SELECT employee_id,LAST_NAME,HIRE_DATE,department_id
FROM employees
WHERE department_id = 
      (SELECT department_id 
       FROM employees WHERE last_name LIKE 'Zlotkey')
       AND
       last_name != 'Zlotkey';

SELECT *
FROM employees
WHERE salary > 
      (SELECT AVG(salary)
      FROM employees);

SELECT employee_id,e1.last_name
FROM  employees e1 INNER JOIN      
    (SELECT DISTINCT department_id
    FROM employees
    WHERE last_name LIKE '%u%') e2
    ON e1.department_id = e2.department_id
ORDER BY employee_id;

SELECT e.LAST_NAME,e.DEPARTMENT_ID,e.JOB_ID
FROM employees e INNER JOIN departments d
    ON e.department_id = d.department_id
    INNER JOIN locations l
    ON d.location_id = l.location_id
WHERE l.location_id = '1700';

SELECT *
FROM employees
WHERE manager_id = 100;

SELECT d.department_id,e.last_name,e.job_id
FROM employees e INNER JOIN departments d 
    ON e.department_id = d.department_id
WHERE LOWER(d.department_name) = 'executive';
