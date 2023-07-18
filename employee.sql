USE submissions;
-- write a DDL command to create this table
CREATE TABLE Employee (
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  job_start_date DATE,
  salary DECIMAL(10, 2)
);

-- write a DDL command to add a new column "department"
ALTER TABLE Employee
ADD department VARCHAR(50);

-- Write a SQL query to find the highest salary from an "Employee" table.
SELECT MAX(salary) AS highest_salary
FROM Employee;

-- write a query to find all the employees who joined in the last 6 months;
SELECT *
FROM Employee
WHERE job_start_date >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH);

-- write a query display number of employees in each department.
SELECT department, COUNT(*) AS employee_count
FROM Employee
GROUP BY department;
