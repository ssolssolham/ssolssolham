--�� ���º� - ���ѽð� 60��(bangry313@naver.com �̸��� ����)

--1. 'London'���� �ٹ��ϴ� ��� ����� �����ȣ(employee_id), 
--   ����̸�(last_name), �����̸�(job_title), �μ��̸�(department_name), 
--   �μ���ġ(city)�� ��ȸ�Ͻÿ�.
--  -���� ���̺� : employees, jobs, departments, locations

SELECT e.employee_id, 
       e.last_name, 
       j.job_title, 
       d.department_name, 
       l.city 
FROM   employees e 
       inner join jobs j 
               ON e.job_id = j.job_id 
       inner join departments d 
               ON e.department_id = d.department_id 
       inner join locations l 
               ON d.location_id = l.location_id
WHERE city Like 'London';
               
--2. ����̸�(last_name)�� 'A'�� ���Ե� ����� �̸�(last_name)�� �μ���(department_name)�� ��ȸ�Ͻÿ�.
SELECT e.last_name, 
       d.department_name 
FROM   employees e 
       inner join departments d 
               ON e.department_id = d.department_id 
WHERE  last_name LIKE '%A%'; 

--3. �޿�(salary)�� 3000���� 5000 ������ ����� ��ȣ, �̸�, �޿�, �μ����� ��ȸ�϶�.
SELECT e.employee_id, 
       e.last_name, 
       e.salary, 
       d.department_name 
FROM   employees e 
       inner join departments d 
               ON e.department_id = d.department_id 
WHERE  salary BETWEEN 3000 AND 5000; 

--4. 'Accounting' �μ��� �ٹ��ϴ� ����� �̸��� �Ի����� ��ȸ�϶�.
--   - �Ի��� ��� ���� - 2007�� 05�� 21��(������)
SELECT last_name, 
       To_char(hire_date, 'YYYY') 
       ||'�� ' 
       ||To_char(hire_date, 'MM') 
       ||'�� ' 
       ||To_char(hire_date, 'DD') 
       ||'��(' 
       ||To_char(hire_date, 'DAY') 
       ||')' "�Ի���" 
FROM   employees e 
       inner join departments d 
               ON e.department_id = d.department_id 
WHERE  d.department_name = 'Accounting'; 

-- 5. 'Landry(last_name)'�� ���� �μ��� �ٹ��ϴ� ����� ��� ��� ������ ��ȸ�϶�.
--    (��. Landry�� ����)

SELECT employee_id, 
       first_name, 
       last_name, 
       email, 
       phone_number, 
       hire_date, 
       job_id, 
       salary, 
       commission_pct, 
       manager_id, 
       department_id 
FROM   employees 
WHERE  department_id = (SELECT department_id 
                        FROM   employees 
                        WHERE  last_name LIKE 'Landry') 
       AND last_name != 'Landry'; 
       
--6. 'Lee(last_name)'���� �ʰ� �Ի��� ����� �̸��� �Ի��� ��ȸ�϶�.
SELECT last_name, 
       hire_date 
FROM   employees 
WHERE  hire_date > (SELECT hire_date 
                    FROM   employees 
                    WHERE  last_name LIKE 'Lee'); 

--7. 'Lee(last_name)'���� ���� �޿��� �޴� ����� �̸��� �޿��� ��ȸ�϶�.
SELECT last_name, 
       salary 
FROM   employees 
WHERE  salary > (SELECT salary 
                 FROM   employees 
                 WHERE  last_name LIKE 'Lee');             

--8. 50�� �μ��� ������ ���� �޿��� �޴� ����� �̸��� �޿��� ��ȸ�϶�.
SELECT last_name, 
       salary 
FROM   employees 
WHERE  salary = ANY (SELECT salary 
                     FROM   employees 
                     WHERE  department_id = 50); 

--9. ��� ����� ��ձ޿����� ���� �޿��� �޴� ������� ���, �̸�, �޿��� ��ȸ�϶�.
SELECT employee_id, 
       last_name, 
       salary 
FROM   employees 
WHERE  salary > (SELECT Avg(salary) 
                 FROM   employees); 

--10.�̸�(last_name)��  'T'�� ���ԵǾ� �ִ� ����� ������ �μ��� �ٹ��ϴ� ����� ��ȣ, �̸��� ��ȸ�϶�.
SELECT employee_id, 
       last_name 
FROM   employees 
WHERE  department_id = ANY (SELECT DISTINCT department_id 
                            FROM   employees 
                            WHERE  last_name LIKE '%T%'); 

--11.10�� �μ� �ִ�޿��ڿ� ������ �޿��� �޴� ����� ��ȣ, �̸�, �޿��� ��ȸ�϶�.
SELECT employee_id, 
       last_name, 
       salary 
FROM   employees 
WHERE  salary = (SELECT Max(salary) 
                 FROM   employees 
                 WHERE  department_id = 10); 

--12. 10���μ��� �ٹ��ϴ� ����� �̸��� �μ��� ��ȸ�϶�.
SELECT e.last_name, 
       d.department_name 
FROM   employees e 
       inner join departments d 
               ON e.department_id = d.department_id 
WHERE  e.department_id = 10; 

--13. 'IT_PROG' ������ �ִ� �޿��ں��� ���� �޿��� �޴� ��� ����(�μ���ȣ, �����ȣ, �̸�, �޿�)�� ����϶�.
SELECT department_id, 
       employee_id, 
       last_name, 
       salary 
FROM   employees 
WHERE  salary > (SELECT Max(salary) 
                 FROM   employees 
                 WHERE  job_id LIKE 'IT_PROG'); 

--14. ��� �޿����� ���� �޿��� �ް� �̸��� u�� ���Ե� ����� ���� �μ��� 
--�ٹ��ϴ� ��� ����� ��� ����(�����ȣ, �̸�, �޿�)�� ��ȸ�϶�.
SELECT employee_id, 
       last_name, 
       salary 
FROM   employees 
WHERE  salary > (SELECT Avg(salary) 
                 FROM   employees) 
       AND department_id = ANY (SELECT DISTINCT department_id 
                                FROM   employees 
                                WHERE  last_name LIKE '%u%'); 

/*15. ��ձ޿��� ���� ���� ������ȣ(job_id)�� ��ձ޿��� ����϶�
    ��) ������ȣ  ��ձ޿�
       -------------------
        CLERK      2300    */
  
SELECT e.������ȣ,e.��� 
FROM  (SELECT job_id      "������ȣ", 
              Avg(salary) "���" 
       FROM   employees 
       GROUP  BY job_id) e 
      inner join (SELECT Min(���) "�ּ�" 
                  FROM   (SELECT job_id      "������ȣ", 
                                 Avg(salary) "���" 
                          FROM   employees 
                          GROUP  BY job_id)) e2 
              ON e.��� = e2.�ּ�; 
