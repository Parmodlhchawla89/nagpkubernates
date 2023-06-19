CREATE SCHEMA IF NOT EXISTS employee_db;

USE employee_db;

CREATE TABLE IF NOT EXISTS employee_details (
		id bigint not null auto_increment,
        emp_designation varchar(255),
        emp_id varchar(255),
        emp_joining_date date,
        emp_name varchar(255),
        emp_ogName varchar(255),
        emp_location varchar(255),
        primary key (id),
        unique (emp_id)
);

INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Tech Lead', '3122222', '2020-03-10', 'John', 'Michael', "US");
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Junior Lead', '312233', '2018-04-01', 'Sharal', 'Ian Harvey', "US");
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Sofrware Engineer', '2345678', '2019-07-02', 'Douglas', 'Cornelius',"India");
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Sr. Sofrware Engineer', '1234321', '2023-02-03', 'Shawn Marsh', 'Shirley',"Dubai");
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Consultant', '122211', '2023-01-10', 'Shane Watson', 'James Foley',"UK");