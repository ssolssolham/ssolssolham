-- ���� ���̺� ����
DROP TABLE locations cascade CONSTRAINTS;
DROP TABLE address cascade CONSTRAINTS;
DROP TABLE departments cascade CONSTRAINTS;
DROP TABLE jobs cascade CONSTRAINTS;
DROP TABLE commissions cascade CONSTRAINTS;
DROP TABLE employees cascade CONSTRAINTS;
DROP TABLE customers cascade CONSTRAINTS;
DROP TABLE credits cascade CONSTRAINTS;
DROP TABLE order_items cascade CONSTRAINTS;
DROP TABLE orders cascade CONSTRAINTS;
DROP TABLE payments cascade CONSTRAINTS;
DROP TABLE product_categories cascade CONSTRAINTS;
DROP TABLE products cascade CONSTRAINTS;
DROP TABLE transport_status cascade CONSTRAINTS;
DROP TABLE product_lines cascade CONSTRAINTS;
DROP TABLE outstock_reasons cascade CONSTRAINTS;
DROP TABLE stocks cascade CONSTRAINTS;
DROP TABLE stock_items cascade CONSTRAINTS;
DROP TABLE storages cascade CONSTRAINTS;


-- ���� ���̺� ����
CREATE TABLE locations (
    location_no   NUMBER(5)       NOT NULL,
    location_name VARCHAR2(20)    NOT NULL
);


-- ���� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE locations
   ADD ( CONSTRAINT locations_locationno_pk PRIMARY KEY(location_no),
        CHECK (location_name IN ('NA', 'SA', 'AF', 'AS', 'EU'))
  );


-- �ּ� ���̺� ����
CREATE TABLE address(
    address_no      NUMBER(5)     NOT NULL,
    location_no     NUMBER(5)     NOT NULL,
    city            VARCHAR2(20)  NOT NULL,
    post_no         VARCHAR2(20),
    street_address  VARCHAR2(100)
);


-- �ּ� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE address
    ADD ( CONSTRAINT address_addressno_pk PRIMARY KEY (address_no));


-- �μ� ���̺� ����  
CREATE TABLE departments(
    dept_no       NUMBER(5)     NOT NULL,
    address_no    NUMBER(5)     NOT NULL,
    dept_name     VARCHAR2(20)  NOT NULL
);

-- �μ� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE departments
    ADD (CONSTRAINT departments_deptno_pk PRIMARY KEY (dept_no));


-- ���� ���̺� ����
CREATE TABLE jobs(
    job_no    NUMBER(5)     NOT NULL,
    job_name  VARCHAR2(30)  NOT NULL
);


-- ���� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE jobs
   ADD (CONSTRAINT jobs_jobno_pk PRIMARY KEY (job_no));


-- Ŀ�̼� ���̺� ����
CREATE TABLE commissions(
    commission_no   NUMBER(5)   NOT NULL,
    commission_rate NUMBER(3,3) NOT NULL
);


-- Ŀ�̼� ���̺� �������� ����(�⺻Ű ����)
-- üũ ���� ���� �߰� -> ������ �۹̼� ������ �Է� ����
ALTER TABLE commissions
   ADD ( CONSTRAINT commissions_commissionno_pk PRIMARY KEY (commission_no),
        CHECK (commission_rate IN (0.1, 0.125, 0.15, 0.175, 0.2)));


-- ���� ���̺� ����
CREATE TABLE employees(
    employee_no       NUMBER(5)     NOT NULL,
    commission_no     NUMBER(5),
    job_no            NUMBER(5),
    employee_position VARCHAR2(30)  NOT NULL,
    dept_no           NUMBER(5),
    salary            NUMBER(5)     NOT NULL,
    comments          VARCHAR2(80),
    employee_id       VARCHAR2(30)  UNIQUE NOT NULL,
    last_name         VARCHAR2(30),
    hiredate          DATE,
    first_name        VARCHAR2(30),
    employee_pw       VARCHAR2(30)  NOT NULL
);


-- ���� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE employees
   ADD ( CONSTRAINT employees_employeeno_pk PRIMARY KEY (employee_no)
  );


-- �� ���̺� ����
CREATE TABLE customers(
    customer_no       NUMBER(5)     NOT NULL,
    employee_no       NUMBER(5),
    address_no        NUMBER(5),
    credit_no         NUMBER(5)     NOT NULL,
    customer_name     VARCHAR2(30)  NOT NULL,
    customer_phone_no VARCHAR2(80),
    customer_comment  VARCHAR2(30),
    customer_id       VARCHAR2(30)  UNIQUE NOT NULL,
    customer_pw       VARCHAR2(30)  NOT NULL
);


-- �� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE customers
   ADD ( CONSTRAINT customers_customerno_pk PRIMARY KEY (customer_no)
  );


-- �ſ��� ���̺� ����
CREATE TABLE credits(
    credit_no            NUMBER (5)    NOT NULL,
    credit_name          VARCHAR2(30)  NOT NULL
); 


-- �ſ��� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE credits
   ADD ( CONSTRAINT credits_creditno_pk PRIMARY KEY (credit_no)
      );
  
  
-- �ֹ��׸� ���̺� ����
CREATE TABLE order_items(
  order_item_no         NUMBER(5) NOT NULL,
  product_no            NUMBER(5) NOT NULL,
  order_no              NUMBER(5) NOT NULL,
  product_quantity      NUMBER(5)
);


-- �ֹ��׸� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE order_items
   ADD ( CONSTRAINT order_items_orderitemno_pk PRIMARY KEY (order_item_no)
  );


-- �ֹ� ���̺� ����
CREATE TABLE orders(
    order_no            NUMBER (5)    NOT NULL,
    customer_no         NUMBER (5)    NOT NULL,
    employee_no         NUMBER (5),
    shipment_date       DATE,
    order_money         NUMBER (30)   NOT NULL,
    pay_method          VARCHAR2(80),
    shipment_status     VARCHAR2(30),
    order_method        VARCHAR2(30),
    order_date          DATE
);

-- �ֹ� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE orders
   ADD ( CONSTRAINT orders_orderno_pk PRIMARY KEY (order_no)
  );
  
  
-- ���� ���̺� ����
CREATE TABLE payments(
    payment_no            NUMBER (5)    NOT NULL,
    transport_status_no   NUMBER (5),
    order_no              NUMBER (5),
    payment_date          VARCHAR2(30)  NOT NULL
);


-- ���� ���̺� �������� ����(�⺻Ű ����)
ALTER TABLE payments
   ADD ( CONSTRAINT payments_paymentno_pk PRIMARY KEY (payment_no)
  );


-- â�� ���̺� ����
CREATE TABLE storages(
    storage_no            NUMBER(5)     NOT NULL,
    employee_no           NUMBER(5),
    address_no            NUMBER(5)     NOT NULL,
    storages_name         VARCHAR2(30)
);


-- â�� ���̺� ������� ����(�⺻Ű ����)
ALTER TABLE storages
    ADD (CONSTRAINT storages_storageno_pk PRIMARY KEY(storage_no));
    

-- ��� �׸� ���̺� ����
CREATE TABLE stock_items(
    storage_no        NUMBER(5) NOT NULL,
    stock_items_no    NUMBER(5) NOT NULL
);


-- ��� ���̺� ����
CREATE TABLE stocks(
    stock_items_no       NUMBER(5)  NOT NULL,
    product_no           NUMBER(5)  NOT NULL, 
    outstock_reason_no   NUMBER(5),
    employee_no          NUMBER(5),
    stock_max            NUMBER(10) NOT NULL,
    stock_quantity       NUMBER(10) NOT NULL
);


-- ��� ���̺� ������� ����(�⺻Ű ����)
ALTER TABLE stocks
    ADD (CONSTRAINT stocks_stockitemsno_pk PRIMARY KEY(stock_items_no));
         

-- ��� ���� ���� ���̺� ����
CREATE TABLE outstock_reasons(
    outstock_reason_no     NUMBER(5) NOT NULL,
    outstock_reason_name   VARCHAR2(30),
    reorder_date           DATE,
    restocked_date         DATE
);


-- ��� ���� ���� ������� ����(�⺻Ű ����)
ALTER TABLE outstock_reasons
    ADD (CONSTRAINT outstock_reasons_or_pk PRIMARY KEY(outstock_reason_no)
    );
    

-- ������� ���̺� ����
CREATE TABLE product_lines(
    product_line_no       NUMBER(5)    NOT NULL,
    product_no            NUMBER(5),
    address_no            NUMBER(5)    NOT NULL,
    employee_no           NUMBER(5),
    special_status        VARCHAR2(15)
);

-- ������� ���̺� ������� ����(�⺻Ű)
ALTER TABLE product_lines
    ADD (CONSTRAINT product_lines_productlineno_pk PRIMARY KEY (product_line_no));
         

-- ��ۻ��� ���̺� ����
CREATE TABLE transport_status(
    transport_status_no       NUMBER(5)     NOT NULL,
    payment_no                NUMBER(5)     NOT NULL,
    transport_date            DATE ,
    transport_start           VARCHAR2(30)  NOT NULL,
    transport_destination     VARCHAR2(30)  NOT NULL,
    current_position          VARCHAR2(80)
);


-- ��ۻ��� ���̺� ������� ����(�⺻Ű ����
ALTER TABLE transport_status
    ADD (CONSTRAINT transport_status_tsno_pk PRIMARY KEY (transport_status_no));


-- ��ǰ ���̺� ����
CREATE TABLE products(
    product_no                NUMBER(5)       NOT NULL,
    product_category_no       NUMBER(5)       NOT NULL,
    product_name              VARCHAR2(80)    NOT NULL,
    product_brief_comment     VARCHAR2(120),
    product_price             VARCHAR2(30)    NOT NULL,
    product_bundle_quantity   VARCHAR2(80),
    product_detail_comment    VARCHAR2(200),
    product_picture           VARCHAR2(30),
    product_release_date      DATE
);


-- ��ǰ ���̺� ������� ����(�⺻Ű ����)
ALTER TABLE products
    ADD (CONSTRAINT products_productno_pk PRIMARY KEY (product_no));


-- ��ǰ�� ���̺� ����
CREATE TABLE product_categories(
    product_category_no       NUMBER(5)       NOT NULL,
    product_category_name     VARCHAR2(30)    NOT NULL
);

-- ��ǰ�� ���̺� ������� ����(�⺻�� ����)
ALTER TABLE product_categories
  ADD (CONSTRAINT product_categories_pcno_pk PRIMARY KEY (product_category_no));

  
-- �ּ� ���̺� ������� ����(Foreign  Key)  
ALTER TABLE address
 ADD (CONSTRAINT address_locationno_fk FOREIGN KEY (location_no) REFERENCES locations(location_no));


-- �μ� ���̺� ������� ����(Foreign  Key) 
ALTER TABLE departments
 ADD (CONSTRAINT departments_addressno_fk FOREIGN KEY (address_no) REFERENCES address(address_no));


-- ���� ���̺� ������� ����(Foreign  Key)
ALTER TABLE employees
 ADD (
    CONSTRAINT employees_commissionno_fk FOREIGN KEY (commission_no) REFERENCES commissions(commission_no),
    CONSTRAINT employees_deptno_fk FOREIGN KEY (dept_no) REFERENCES departments(dept_no),
    CONSTRAINT employees_jobno_fk FOREIGN KEY (job_no) REFERENCES jobs(job_no));


-- �� ���̺� ������� ����(Foreign  Key)
ALTER TABLE customers
 ADD (   
    CONSTRAINT customers_employeeno_fk FOREIGN KEY (employee_no) REFERENCES employees(employee_no),
    CONSTRAINT customers_addressno_fk FOREIGN KEY (address_no) REFERENCES address(address_no),
    CONSTRAINT customers_creditno_fk FOREIGN KEY (credit_no) REFERENCES credits(credit_no));


-- �ſ��� ���̺� üũ ������� ����('EXCELLENT', 'GOOD', 'POOR' ���� ������ ��� X)
ALTER TABLE credits
 ADD ( CHECK (credit_name IN ('EXCELLENT', 'GOOD', 'POOR')));


-- �ֹ��׸� ���̺� ������� ����(Foreign  Key)
ALTER TABLE order_items
 ADD (
   CONSTRAINT order_items_productno_fk FOREIGN KEY (product_no) REFERENCES products(product_no),
   CONSTRAINT order_items_orderno_fk FOREIGN KEY (order_no) REFERENCES orders(order_no));


-- �ֹ� ���̺� ������� ����(Foreign  Key)
-- �ֹ� ���̺� üũ ������� ����(���ҹ��, ��������)
ALTER TABLE orders
 ADD (
    CONSTRAINT orders_customerno_fk FOREIGN KEY (customer_no) REFERENCES customers(customer_no),
    CONSTRAINT orders_employeeno_fk FOREIGN KEY (employee_no) REFERENCES employees(employee_no),
    CHECK (PAY_METHOD IN ('Credit', 'Cash')),
    CHECK (shipment_status IN ('Y', 'N')),
    CHECK (order_method IN ('����', '�ѽ�', '��ȭ'))
    );


-- ���� ���̺� ������� ����(Foreign  Key)
ALTER TABLE payments
 ADD (
    CONSTRAINT payments_customerno_fk FOREIGN KEY (transport_status_no) REFERENCES transport_status(transport_status_no),
    CONSTRAINT payments_orderno_fk FOREIGN KEY (order_no) REFERENCES orders(order_no));


-- â�� ���̺� ������� ����(Foreign  Key)
ALTER TABLE storages
 ADD (
    CONSTRAINT storages_employeeno_fk FOREIGN KEY(employee_no) REFERENCES employees(employee_no),
    CONSTRAINT storages_addressno_fk FOREIGN KEY(address_no) REFERENCES address(address_no));


-- ��� �׸� ���̺� ������� ����(Foreign  Key)
ALTER TABLE stock_items
    ADD (CONSTRAINT stock_items_storageno_fk FOREIGN KEY(storage_no) REFERENCES storages(storage_no),
         CONSTRAINT stock_items_stockitemsno_fk FOREIGN KEY(stock_items_no) REFERENCES stocks(stock_items_no)
    /*CONSTRAINT composite_pk PRIMARY KEY(storage_no, stock_items_no)*/);

-- ��� ���̺� ������� ����(Foreign  Key)
ALTER TABLE stocks
    ADD (
      CONSTRAINT stocks_productno_fk FOREIGN KEY(product_no) REFERENCES products(product_no),
      CONSTRAINT stocks_outstockreasonno_fk FOREIGN KEY(outstock_reason_no) REFERENCES outstock_reasons(outstock_reason_no),
      CONSTRAINT stocks_employeeno_fk FOREIGN KEY(employee_no) REFERENCES employees(employee_no));


-- ������� ���̺� ������� ����(Foreign  Key)
ALTER TABLE product_lines
    ADD (
      CONSTRAINT product_lines_productno_fk      FOREIGN KEY (product_no)   REFERENCES products(product_no),
      CONSTRAINT product_lines_addressno_fk      FOREIGN KEY (address_no)   REFERENCES address(address_no),
      CONSTRAINT product_lines_employeeno_fk     FOREIGN KEY (employee_no)  REFERENCES employees(employee_no));


-- ��ۻ��� ���̺� ������� ����(Foreign  Key)
ALTER TABLE transport_status
    ADD (CONSTRAINT transport_status_paymentno_fk  FOREIGN KEY (payment_no) REFERENCES payments(payment_no));

-- ��ǰ ���̺� ������� ����(Foreign  Key)
ALTER TABLE products
    ADD (CONSTRAINT products_productcategoryno_fk FOREIGN KEY (product_category_no) REFERENCES product_categories(product_category_no));