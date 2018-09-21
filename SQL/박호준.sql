--�� ���º� - ���ѽð� 40��(bangry313@naver.com �̸��� ����)

--1. employees ���̺��� �޿��� 5000�̻� 15000���� ���̿� ���Ե��� �ʴ� ����� �����ȣ(employee_id),
--�̸�(last_name), �޿�(salary), �Ի�����(hire_date)�� ��ȸ�Ͻÿ�.

SELECT last_name, 
       salary, 
       hire_date 
FROM   employees 
WHERE  salary < 5000 
        OR salary > 15000; 


--2. �μ���ȣ(department_id) 50, ����(job_id) 'ST_MAN', �Ի��� 2004-7-18���� ����� �����ȣ, �̸�, ����, �Ի����� ��ȸ�Ͻÿ�.
SELECT employee_id, 
       last_name, 
       job_id, 
       hire_date 
FROM   employees 
WHERE  department_id = 50 
       AND job_id = 'ST_MAN' 
       AND hire_date = To_date('2004-7-18', 'YYYY-MM-DD'); 

--3. 2002�� ������ ���� �Ի��� ����߿� ���('ST_MAN', 'ST_CLERK')������ ����ϴ� ������� ��� �÷��� ��ȸ�Ͻÿ�.
SELECT * 
FROM   employees 
WHERE  To_char(hire_date, 'YYYY') > '2002' 
       AND job_id IN( 'ST_MAN', 'ST_CLERK' ); 

--4. ���(manager_id)�� ���� ����� ��� �÷��� ��ȸ�Ͻÿ�.
SELECT * 
FROM   employees 
WHERE  manager_id IS NULL; 


--5. �޿��� 10000 �̸��� ����߿��� ���('SH_CLERK')�̳� ����('PU_MAN', 'PU_CLERK')������ ����ϴ� ������� ��� �÷��� ��ȸ�Ͻÿ�.
SELECT * 
FROM   employees 
WHERE  salary < 10000 
       AND job_id IN ( 'SH_CLERK', 'PU_MAN', 'PU_CLERK' ); 

--6. ����̸�(last_name)�� J, A �Ǵ� M���� �����ϴ� ��� ����� �̸�(ù ���ڴ� �빮�ڷ�, ������ ���ڴ� �ҹ��ڷ� ǥ��)
--�� �̸� ���̸� ��ȸ�Ͻÿ�(��, last_name ���������� ���� ����).

SELECT Initcap(last_name), 
       Length(last_name) 
FROM   employees 
WHERE  last_name LIKE 'J%' 
        OR last_name LIKE 'A%' 
        OR last_name LIKE 'M%'; 

--7. �⵵�� �Ի��ο����� ��ȸ�Ͻÿ�.
SELECT To_char(hire_date, 'YYYY'), 
       Count(*) 
FROM   employees 
GROUP  BY To_char(hire_date, 'YYYY'); 

--8. �����ȣ(employee_id)�� Ȧ���� ����� ��� ������ ��ȸ�Ͻÿ�.
SELECT * 
FROM   employees 
WHERE  MOD(employee_id, 2) = 1; 

--9.���ú��� 6���� �� ���ƿ��� ù��° �ݿ����� ��¥�� ����Ͻÿ�.
--   (��¥ ���� ��: 2002-06-05 15:23:10 �ݿ���)
SELECT NEXT_DAY(ADD_MONTHS(SYSDATE, 6),'��')
FROM dual;

-- 10.�����ȣ(employee_id), �����(first_name), ����ȣ(manager_id)�� ��ȸ�ϵ�
--   ��簡 ����(NULL) ��� ����ȣ�� '�뻧'�̶� ����Ͻÿ�.
SELECT employee_id, 
       first_name, 
       Decode(manager_id, NULL, '�뻧', 
                          manager_id) 
FROM   employees; 

--11.������� 3���౸������ �з��ϱ� ���� ����� 3���� ������
--   �������� 0�̸� "��ȭ�����"
--   �������� 1�̸� "���׸���"
--   �������� 2�̸� "������"���� �з��Ͽ� ���̸�, �����ȣ, ������� ����Ͻÿ�.

SELECT CASE 
         WHEN MOD(employee_id, 3) = 0 THEN '��ȭ�����' 
         WHEN MOD(employee_id, 3) = 1 THEN '���׸���' 
         WHEN MOD(employee_id, 3) = 2 THEN '������' 
       END "���̸�", 
       employee_id, 
       last_name 
FROM   employees; 

/*12.����� �޿��׷����� �Ʒ��� ���� ����Ͻÿ�.
   Steven King     *****($5,000) // $1000�޷��� �� 1���߰�.
   Neena Kochhar   ***($3,000)--    .........
   XXXX XXXXX      *****************($17,000)
*/
select LENGTH(100000)
FROM employees;

SELECT employee_id,LPAD('($'||TRIM(to_char(salary,'999,999'))||')',(FLOOR(salary/1000))+LENGTH(salary)+4,'*')
FROM employees
;
--13.2002�� 3������ 2003�� 2�� �Ⱓ ���� �Ի��� ����� ������� �μ��� �ο����� ��ȸ�Ͻÿ�.
--   (����� �ο����� ���� ������� �����Ͽ� ���)
SELECT To_char(hire_date, 'YYYY-MM'), 
       department_id, 
       Count(To_char(hire_date, 'YYYY-MM')) 
FROM   employees 
WHERE  To_char(hire_date, 'YYYY-MM-DD') > '2002-02-28' 
       AND To_char(hire_date, 'YYYY-MM-DD') <= '2003-03-01' 
GROUP  BY To_char(hire_date, 'YYYY-MM'), 
          department_id 
ORDER  BY Count(To_char(hire_date, 'YYYY-MM')) DESC; 

--14.������ ��� �޿��� ����϶�. ��, ��ձ޿��� 10000�� �ʰ��ϴ� ���� �����ϰ� ��� �޿��� ���� ������������ �����Ͻÿ�.
SELECT job_id, 
       Avg(salary) 
FROM   employees 
GROUP  BY job_id 
HAVING Avg(salary) < 10000 
ORDER  BY Avg(salary) DESC; 

--15.2004�⿡ �Ի��� ������� ������κ��� �б⺰ �Ի����� ���� �Ʒ��� ���� ����Ͻÿ�.(��Ʈ: ���˹��� Q Ȱ��)
/*�б�   �����
---------------
1      3
2      1
3      2*/

SELECT TO_DATE(hire_date,'YYYY Q'),COUNT(TO_DATE(hire_date,'YYYY Q'))
FROM employees
WHERE TO_CHAR(hire_date,'YYYY') = '2004'
GROUP BY  TO_DATE(hire_date,'YYYY Q');