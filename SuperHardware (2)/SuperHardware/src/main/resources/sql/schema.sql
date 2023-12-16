DROP DATABASE IF EXISTS super_hardware;

CREATE DATABASE IF NOT EXISTS super_hardware;

USE super_hardware;

CREATE TABLE user(user_id VARCHAR(20) PRIMARY KEY,
                  name VARCHAR(50),
                  password VARCHAR(20));



CREATE TABLE customer(cust_id VARCHAR(20) PRIMARY KEY,
                      cust_name VARCHAR(50),
                      address TEXT,
                      contact INT(10));



CREATE TABLE orders(order_id VARCHAR(20) PRIMARY KEY,
                    cust_id VARCHAR(20),
       payment DECIMAL(8,2) NOT NULL ,
                    ord_date DATE,
                    CONSTRAINT FOREIGN KEY(cust_id)REFERENCES customer(cust_id) ON DELETE CASCADE ON UPDATE CASCADE);



CREATE TABLE employee(emp_id VARCHAR(20) PRIMARY KEY,
                      emp_name VARCHAR(50),
                      address TEXT);



CREATE TABLE attendance(attendence_id VARCHAR(20) PRIMARY KEY,
                        emp_id VARCHAR(20),
                        date DATE,
                        time TIME,
                        CONSTRAINT FOREIGN KEY(emp_id)REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE);




CREATE TABLE salary(salary_id VARCHAR(20) PRIMARY KEY,
                    salary_amount DOUBLE(7,2),
                    date DATE,
                    emp_id VARCHAR(20),
                    CONSTRAINT FOREIGN KEY(emp_id)REFERENCES employee(emp_id)ON DELETE CASCADE ON UPDATE CASCADE);




CREATE TABLE delivery(delivery_id VARCHAR(20) PRIMARY KEY,
                      delivered_date DATE,
                      location VARCHAR(50),
                      order_id VARCHAR(20),
                      emp_id VARCHAR(20),
                      CONSTRAINT FOREIGN KEY(order_id)REFERENCES orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE,
                      CONSTRAINT FOREIGN KEY(emp_id)REFERENCES employee(emp_id) ON DELETE CASCADE ON UPDATE CASCADE);




CREATE TABLE item_stock(item_stock_code VARCHAR(20) PRIMARY KEY,
                        item_name VARCHAR(50),
                        item_unit_price DOUBLE(7,2),
                        quantity INT(50),
                        item_cateogry VARCHAR(50));




CREATE TABLE order_item_details(item_stock_code VARCHAR(20),
                                order_id VARCHAR(20),
                                ord_quantity INT(50),
                                CONSTRAINT FOREIGN KEY(item_stock_code)REFERENCES item_stock(item_stock_code)ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT FOREIGN KEY(order_id)REFERENCES orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE);





CREATE TABLE supplier(sup_id VARCHAR(20) PRIMARY KEY,
                      sup_name VARCHAR(25),
                      address TEXT,
                      suplier_email VARCHAR(50));



CREATE TABLE supplier_order(supplier_order_id VARCHAR(20) PRIMARY KEY,
                            date DATE,
                            sup_id VARCHAR(20),
                            CONSTRAINT FOREIGN KEY(sup_id)REFERENCES supplier(sup_id) ON DELETE CASCADE ON UPDATE CASCADE);




CREATE TABLE item_stock_details(item_stock_code VARCHAR(20),
                                supplier_order_id VARCHAR(20),
                                CONSTRAINT FOREIGN KEY(item_stock_code) REFERENCES item_stock(item_stock_code)ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT FOREIGN KEY(supplier_order_id) REFERENCES supplier_order(supplier_order_id) ON DELETE CASCADE ON UPDATE CASCADE);