-- 지역 테이블 생성
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


-- 지역 테이블 생성
CREATE TABLE locations (
    location_no   NUMBER(5)       NOT NULL,
    location_name VARCHAR2(20)    NOT NULL
);


-- 지역 테이블 제약조건 설정(기본키 설정)
ALTER TABLE locations
   ADD ( CONSTRAINT locations_locationno_pk PRIMARY KEY(location_no),
        CHECK (location_name IN ('NA', 'SA', 'AF', 'AS', 'EU'))
  );


-- 주소 테이블 생성
CREATE TABLE address(
    address_no      NUMBER(5)     NOT NULL,
    location_no     NUMBER(5)     NOT NULL,
    city            VARCHAR2(20)  NOT NULL,
    post_no         VARCHAR2(20),
    street_address  VARCHAR2(100)
);


-- 주소 테이블 제약조건 설정(기본키 설정)
ALTER TABLE address
    ADD ( CONSTRAINT address_addressno_pk PRIMARY KEY (address_no));


-- 부서 테이블 생성  
CREATE TABLE departments(
    dept_no       NUMBER(5)     NOT NULL,
    address_no    NUMBER(5)     NOT NULL,
    dept_name     VARCHAR2(20)  NOT NULL
);

-- 부서 테이블 제약조건 설정(기본키 설정)
ALTER TABLE departments
    ADD (CONSTRAINT departments_deptno_pk PRIMARY KEY (dept_no));


-- 직무 테이블 생성
CREATE TABLE jobs(
    job_no    NUMBER(5)     NOT NULL,
    job_name  VARCHAR2(30)  NOT NULL
);


-- 직무 테이블 제약조건 설정(기본키 설정)
ALTER TABLE jobs
   ADD (CONSTRAINT jobs_jobno_pk PRIMARY KEY (job_no));


-- 커미션 테이블 생성
CREATE TABLE commissions(
    commission_no   NUMBER(5)   NOT NULL,
    commission_rate NUMBER(3,3) NOT NULL
);


-- 커미션 테이블 제약조건 설정(기본키 설정)
-- 체크 제약 조건 추가 -> 일정한 퍼미션 비율만 입력 가능
ALTER TABLE commissions
   ADD ( CONSTRAINT commissions_commissionno_pk PRIMARY KEY (commission_no),
        CHECK (commission_rate IN (0.1, 0.125, 0.15, 0.175, 0.2)));


-- 직원 테이블 생성
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


-- 직원 테이블 제약조건 생성(기본키 설정)
ALTER TABLE employees
   ADD ( CONSTRAINT employees_employeeno_pk PRIMARY KEY (employee_no)
  );


-- 고객 테이블 생성
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


-- 고객 테이블 제약조건 설정(기본키 설정)
ALTER TABLE customers
   ADD ( CONSTRAINT customers_customerno_pk PRIMARY KEY (customer_no)
  );


-- 신용등급 테이블 생성
CREATE TABLE credits(
    credit_no            NUMBER (5)    NOT NULL,
    credit_name          VARCHAR2(30)  NOT NULL
); 


-- 신용등급 테이블 제약조건 설정(기본키 설정)
ALTER TABLE credits
   ADD ( CONSTRAINT credits_creditno_pk PRIMARY KEY (credit_no)
      );
  
  
-- 주문항목 테이블 생성
CREATE TABLE order_items(
  order_item_no         NUMBER(5) NOT NULL,
  product_no            NUMBER(5) NOT NULL,
  order_no              NUMBER(5) NOT NULL,
  product_quantity      NUMBER(5)
);


-- 주문항목 테이블 제약조건 설정(기본키 설정)
ALTER TABLE order_items
   ADD ( CONSTRAINT order_items_orderitemno_pk PRIMARY KEY (order_item_no)
  );


-- 주문 테이블 생성
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

-- 주문 테이블 제약조건 설정(기본키 설정)
ALTER TABLE orders
   ADD ( CONSTRAINT orders_orderno_pk PRIMARY KEY (order_no)
  );
  
  
-- 결제 테이블 생성
CREATE TABLE payments(
    payment_no            NUMBER (5)    NOT NULL,
    transport_status_no   NUMBER (5),
    order_no              NUMBER (5),
    payment_date          VARCHAR2(30)  NOT NULL
);


-- 결제 테이블 제약조건 설정(기본키 설정)
ALTER TABLE payments
   ADD ( CONSTRAINT payments_paymentno_pk PRIMARY KEY (payment_no)
  );


-- 창고 테이블 생성
CREATE TABLE storages(
    storage_no            NUMBER(5)     NOT NULL,
    employee_no           NUMBER(5),
    address_no            NUMBER(5)     NOT NULL,
    storages_name         VARCHAR2(30)
);


-- 창고 테이블 제약사항 설정(기본키 설정)
ALTER TABLE storages
    ADD (CONSTRAINT storages_storageno_pk PRIMARY KEY(storage_no));
    

-- 재고 항목 테이블 생성
CREATE TABLE stock_items(
    storage_no        NUMBER(5) NOT NULL,
    stock_items_no    NUMBER(5) NOT NULL
);


-- 재고 테이블 생성
CREATE TABLE stocks(
    stock_items_no       NUMBER(5)  NOT NULL,
    product_no           NUMBER(5)  NOT NULL, 
    outstock_reason_no   NUMBER(5),
    employee_no          NUMBER(5),
    stock_max            NUMBER(10) NOT NULL,
    stock_quantity       NUMBER(10) NOT NULL
);


-- 재고 테이블 제약사항 설정(기본키 설정)
ALTER TABLE stocks
    ADD (CONSTRAINT stocks_stockitemsno_pk PRIMARY KEY(stock_items_no));
         

-- 재고 소진 이유 테이블 생성
CREATE TABLE outstock_reasons(
    outstock_reason_no     NUMBER(5) NOT NULL,
    outstock_reason_name   VARCHAR2(30),
    reorder_date           DATE,
    restocked_date         DATE
);


-- 재고 소진 이유 제약사항 설정(기본키 설정)
ALTER TABLE outstock_reasons
    ADD (CONSTRAINT outstock_reasons_or_pk PRIMARY KEY(outstock_reason_no)
    );
    

-- 생산라인 테이블 생성
CREATE TABLE product_lines(
    product_line_no       NUMBER(5)    NOT NULL,
    product_no            NUMBER(5),
    address_no            NUMBER(5)    NOT NULL,
    employee_no           NUMBER(5),
    special_status        VARCHAR2(15)
);

-- 생산라인 테이블 제약사항 설정(기본키)
ALTER TABLE product_lines
    ADD (CONSTRAINT product_lines_productlineno_pk PRIMARY KEY (product_line_no));
         

-- 운송상태 테이블 생성
CREATE TABLE transport_status(
    transport_status_no       NUMBER(5)     NOT NULL,
    payment_no                NUMBER(5)     NOT NULL,
    transport_date            DATE ,
    transport_start           VARCHAR2(30)  NOT NULL,
    transport_destination     VARCHAR2(30)  NOT NULL,
    current_position          VARCHAR2(80)
);


-- 운송상태 테이블 제약사항 설정(기본키 설정
ALTER TABLE transport_status
    ADD (CONSTRAINT transport_status_tsno_pk PRIMARY KEY (transport_status_no));


-- 상품 테이블 생성
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


-- 상품 테이블 제약사항 설정(기본키 설정)
ALTER TABLE products
    ADD (CONSTRAINT products_productno_pk PRIMARY KEY (product_no));


-- 제품군 테이블 생성
CREATE TABLE product_categories(
    product_category_no       NUMBER(5)       NOT NULL,
    product_category_name     VARCHAR2(30)    NOT NULL
);

-- 제품군 테이블 제약사항 설정(기본기 설정)
ALTER TABLE product_categories
  ADD (CONSTRAINT product_categories_pcno_pk PRIMARY KEY (product_category_no));

  
-- 주소 테이블 제약사항 설정(Foreign  Key)  
ALTER TABLE address
 ADD (CONSTRAINT address_locationno_fk FOREIGN KEY (location_no) REFERENCES locations(location_no));


-- 부서 테이블 제약사항 설정(Foreign  Key) 
ALTER TABLE departments
 ADD (CONSTRAINT departments_addressno_fk FOREIGN KEY (address_no) REFERENCES address(address_no));


-- 직원 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE employees
 ADD (
    CONSTRAINT employees_commissionno_fk FOREIGN KEY (commission_no) REFERENCES commissions(commission_no),
    CONSTRAINT employees_deptno_fk FOREIGN KEY (dept_no) REFERENCES departments(dept_no),
    CONSTRAINT employees_jobno_fk FOREIGN KEY (job_no) REFERENCES jobs(job_no));


-- 고객 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE customers
 ADD (   
    CONSTRAINT customers_employeeno_fk FOREIGN KEY (employee_no) REFERENCES employees(employee_no),
    CONSTRAINT customers_addressno_fk FOREIGN KEY (address_no) REFERENCES address(address_no),
    CONSTRAINT customers_creditno_fk FOREIGN KEY (credit_no) REFERENCES credits(credit_no));


-- 신용등급 테이블 체크 제약사항 설정('EXCELLENT', 'GOOD', 'POOR' 외의 데이터 허용 X)
ALTER TABLE credits
 ADD ( CHECK (credit_name IN ('EXCELLENT', 'GOOD', 'POOR')));


-- 주문항목 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE order_items
 ADD (
   CONSTRAINT order_items_productno_fk FOREIGN KEY (product_no) REFERENCES products(product_no),
   CONSTRAINT order_items_orderno_fk FOREIGN KEY (order_no) REFERENCES orders(order_no));


-- 주문 테이블 제약사항 설정(Foreign  Key)
-- 주문 테이블 체크 제약사항 설정(지불방법, 선적여부)
ALTER TABLE orders
 ADD (
    CONSTRAINT orders_customerno_fk FOREIGN KEY (customer_no) REFERENCES customers(customer_no),
    CONSTRAINT orders_employeeno_fk FOREIGN KEY (employee_no) REFERENCES employees(employee_no),
    CHECK (PAY_METHOD IN ('Credit', 'Cash')),
    CHECK (shipment_status IN ('Y', 'N')),
    CHECK (order_method IN ('우편', '팩스', '전화'))
    );


-- 결제 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE payments
 ADD (
    CONSTRAINT payments_customerno_fk FOREIGN KEY (transport_status_no) REFERENCES transport_status(transport_status_no),
    CONSTRAINT payments_orderno_fk FOREIGN KEY (order_no) REFERENCES orders(order_no));


-- 창고 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE storages
 ADD (
    CONSTRAINT storages_employeeno_fk FOREIGN KEY(employee_no) REFERENCES employees(employee_no),
    CONSTRAINT storages_addressno_fk FOREIGN KEY(address_no) REFERENCES address(address_no));


-- 재고 항목 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE stock_items
    ADD (CONSTRAINT stock_items_storageno_fk FOREIGN KEY(storage_no) REFERENCES storages(storage_no),
         CONSTRAINT stock_items_stockitemsno_fk FOREIGN KEY(stock_items_no) REFERENCES stocks(stock_items_no)
    /*CONSTRAINT composite_pk PRIMARY KEY(storage_no, stock_items_no)*/);

-- 재고 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE stocks
    ADD (
      CONSTRAINT stocks_productno_fk FOREIGN KEY(product_no) REFERENCES products(product_no),
      CONSTRAINT stocks_outstockreasonno_fk FOREIGN KEY(outstock_reason_no) REFERENCES outstock_reasons(outstock_reason_no),
      CONSTRAINT stocks_employeeno_fk FOREIGN KEY(employee_no) REFERENCES employees(employee_no));


-- 생산라인 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE product_lines
    ADD (
      CONSTRAINT product_lines_productno_fk      FOREIGN KEY (product_no)   REFERENCES products(product_no),
      CONSTRAINT product_lines_addressno_fk      FOREIGN KEY (address_no)   REFERENCES address(address_no),
      CONSTRAINT product_lines_employeeno_fk     FOREIGN KEY (employee_no)  REFERENCES employees(employee_no));


-- 운송상태 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE transport_status
    ADD (CONSTRAINT transport_status_paymentno_fk  FOREIGN KEY (payment_no) REFERENCES payments(payment_no));

-- 상품 테이블 제약사항 설정(Foreign  Key)
ALTER TABLE products
    ADD (CONSTRAINT products_productcategoryno_fk FOREIGN KEY (product_category_no) REFERENCES product_categories(product_category_no));