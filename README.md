# Kubernetes Microservices NAGP
This project will present how the microservices work and how to deploy the microservices on Kubernetes.
Google Kubernetes Engine (GKE) is used as infrastructure for this project.

This application is a mutitier application which has a spring boot service and MySQL database.Once deployed the GET /employees service will return the JSON of all the employees present in the DB.

## Docker hub
The sprint boot jar has been uploaded to a Docker Hub and the image has been used in the YAML file to deploy the service. 
Docker hub repository: https://hub.docker.com/r/parmodhchawla/employeeservice
Image:  parmodhchawla/employeeservice:0.0.1 
 
## Get Started
### 1. Google Kubernetes Engine
- Create Google account.
- Enable Kubernetes Engine.
- Create Kubernetes Cluster.
- Refer to https://cloud.google.com/kubernetes-engine/docs/quickstart

### 2. Clone the project in Google cloud shell from Github (url:) and navigate to the project directory -> Yaml Files
  - Deploy the DB on kubernate with the following commands:
      - kubectl apply -f mysql-secrets.yaml - This will add the secrets being used for the DB
      - kubectl apply -f mysql-configMap.yaml - This file contains the DB name and Host which will be added in to environmanet variables.
      - kubectl apply -f db-deployment.yaml - This will deploy MySql database
      
### 3. Since the database is deployed now we will create table and insert initial data in the DB:
      - kubectl get pods (This will print all the running pods, copy the mysql podname)
      - kubectl exec --stdin --tty #podname -- /bin/bash"  (Replace the podname with the actual mysql podname printed on the shell.)
      - mysql -h mysql -u root -p (This command will enable Mysql editor where we ccan write queries for DB)
      - show databses (This command will print all the active databases)
      - use employee_db 
      - Run following command in mysql editor
      
CREATE TABLE IF NOT EXISTS employee_details (
    id bigint not null auto_increment,
        emp_designation varchar(255),
        emp_id varchar(255),
        emp_joining_date date,
        emp_name varchar(255),
        emp_ogName varchar(255),
        emp_location varchar(255)
        primary key (id),
        unique (emp_id)
);
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Tech Lead', '3122222', '2020-03-10', 'John', 'Michael’, ’US’);
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Junior Lead', '312233', '2018-04-01', 'Sharal', 'Ian Harvey’, ‘India’);
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Sofrware Engineer', '2345678', '2019-07-02', 'Douglas', 'Cornelius' , ‘UK’);
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Sr. Sofrware Engineer', '1234321', '2023-02-03', 'Shawn Marsh', 'Shirley’, ‘Dubai’);
INSERT INTO employee_details (emp_designation, emp_id, emp_joining_date, emp_name, emp_ogName, emp_location) VALUES ('Consultant', '122211', '2023-01-10', 'Shane Watson', 'James Foley’, ‘India’);

- The above command will create employee_details table and add entries to the table.

### 3. Deploy a microservice by running following commands:
    - kubectl apply -f employee-service-deployment.yaml (This will deploy the microservice)
    - kubectl get po (Since we have used replica sets this will create 4 pods for microservice)
    - Once all the pods start running then run the following command:
    - kubectl get services (This will print all the active services, the load balancer will print an external IP which will be used as a base url for the serivce)
    - The port used for this application is 9000
    
### 4. To test the service:
    - Get the external IP from the loadbalancer as mentioned the step 3
    - Paste the IP url on the browser with port externalip/9000/employees
    
    This will print the json as following:
    [{"id":1,"employeeId":"3122222","name":"John","joiningDate":"2020-03-10","ogName":"Michael","designation":"Tech Lead","location":"US"},{"id":2,"employeeId":"312233","name":"Sharal","joiningDate":"2018-04-01","ogName":"Ian Harvey","designation":"Junior Lead","location":"US"},{"id":3,"employeeId":"2345678","name":"Douglas","joiningDate":"2019-07-02","ogName":"Cornelius","designation":"Sofrware Engineer","location":"India"},{"id":4,"employeeId":"1234321","name":"Shawn Marsh","joiningDate":"2023-02-03","ogName":"Shirley","designation":"Sr. Sofrware Engineer","location":"Dubai"},{"id":5,"employeeId":"122211","name":"Shane Watson","joiningDate":"2023-01-10","ogName":"James Foley","designation":"Consultant","location":"UK"}]

### 4. Persistance volume
- To test the persistance of the DB
- Delete pods/service by command: `kubectl delete pod #podname#  (get the db podname from kubectl get po)
- Once you delete the pod it will again start running and the database entries will still be there. Since I have user persistance volume for the database. It will keep all the data.

### 5. Rolling update:
    - I have used rolling update strategy in the yaml which means once we deploy another service with new image, the kubernate will not stop the pods in one go of the previous version and it will start new pod and then kill the existing pod one by one so there is no downtime.
