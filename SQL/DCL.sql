/* #1. DBA ����(sys or system)���� ���� �� User ���� */
CREATE USER bangry identified BY bangry;

/* #2. ������ User�� �ý��� ���� / ��ü(���̺�, �䡦) ���� �ο� */
-- �ý��� ���� ���� �ο�
GRANT CREATE SESSION TO bangry;
-- ��ü ���� �ο�(sys�� bangry���� hr ��Ű���� employee ��ü�� ���� CRUD ������ �ο�)
GRANT SELECT, INSERT, UPDATE, DELETE ON hr.employees
TO bangry;

/* #3. �ʿ信 ���� User���� �ο��� ���� ȸ�� */
REVOKE SELECT, INSERT, UPDATE, DELETE ON hr.employees
FROM bangry;


/* ��(ROLE) : ���� ���ѵ��� ����(���� �� / ����� ���� ��) */
/* #1. User�� ���� �� �ο� */
GRANT CONNECT, RESOURCE, dba TO bangry;

/* #2. ����� ���� �� ���� */
CREATE ROLE my_role;
--DROP ROLE my_role;

/* #3. �ý��� ���Ѱ� ��ü ������ �ѿ� ��� */
GRANT CREATE SESSION TO my_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON hr.employees TO  my_role;

-- ��  ��� ������ �ѿ� �ο�
GRANT ALL ON hr.employees TO my_role;

/* #4. User�� �� �ο� */
--GRANT CONNECT, RESOURCE TO killer;
GRANT my_role TO bangry;

/* #5. User ��� �� ���� */
ALTER USER bangry ACCOUNT LOCK;
ALTER USER bangry ACCOUNT UNLOCK;

/* #6. User ��й�ȣ ���� */
ALTER USER killer IDENTIFIED BY killer;
--ALTER USER killer IDENTIFIED BY killer ACCOUNT UNLOCK;

/* #7. User ���� */
DROP USER bangry CASCADE;

/* #8. ������ ��ųʸ��κ��� ���� ��� ��ȸ */
SELECT * FROM dba_users;

SELECT *
FROM hr.employees;

CREATE SYNONYM hremp
FOR hr.employees;

