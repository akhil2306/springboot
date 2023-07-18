CREATE DATABASE submissions;
USE submissions;

-- Create lead_detail table
CREATE TABLE lead_detail (
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email_address VARCHAR(100),
  phone_number VARCHAR(20)
);

-- Create consultant_detail table
CREATE TABLE consultant_detail (
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email_address VARCHAR(100),
  phone_number VARCHAR(20)
);

-- inserting into consultant_details
INSERT INTO consultant_detail (first_name, last_name, email_address, phone_number)
VALUES
  ('John', 'Doe', 'john.doe@example.com', '1234567890'),
  ('Jane', 'Smith', 'jane.smith@example.com', '9876543210'),
  ('Michael', 'Johnson', 'michael.johnson@example.com', '5555555555'),
  ('Emily', 'Davis', 'emily.davis@example.com', '1111111111'),
  ('David', 'Wilson', 'david.wilson@example.com', '9999999999');

-- updating email address with id = 1
UPDATE consultant_detail
SET email_address = 'new_email@example.com'
WHERE id = 1;


-- Create submission table
CREATE TABLE submission (
  id INT PRIMARY KEY AUTO_INCREMENT,
  lead_id INT,
  consultant_id INT,
  submission_date DATE,
  vendor_company VARCHAR(100),
  vendor_name VARCHAR(100),
  vendor_email_address VARCHAR(100),
  vendor_phone_number VARCHAR(20),
  implementation_partner VARCHAR(100),
  client_name VARCHAR(100),
  pay_rate DECIMAL(10, 2),
  submission_status VARCHAR(50),
  submission_type VARCHAR(50),
  city VARCHAR(50),
  state VARCHAR(50),
  zip VARCHAR(10),
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (lead_id) REFERENCES lead_detail(id),
  FOREIGN KEY (consultant_id) REFERENCES consultant_detail(id)
);

-- inserting into submission table
INSERT INTO submission (lead_id,consultant_id,submission_date,vendor_company,vendor_name,vendor_email_address,vendor_phone_number,
  implementation_partner,client_name,pay_rate,submission_status,submission_type,city,state,zip
) VALUES
  (100, 1001, '2023-07-01', 'Vendor Company 1', 'Vendor Name 1', 'vendor1@example.com', '1234567890', 'Implementation Partner 1', 'Client Name 1', 100.00, 'Pending', 'Type 1', 'City 1', 'State 1', '12345'),
  (104, 1002, '2023-07-02', 'Vendor Company 2', 'Vendor Name 2', 'vendor2@example.com', '2345678901', 'Implementation Partner 2', 'Client Name 2', 200.00, 'Approved', 'Type 2', 'City 2', 'State 2', '23456'),
  (105, 1003, '2023-07-03', 'Vendor Company 3', 'Vendor Name 3', 'vendor3@example.com', '3456789012', 'Implementation Partner 3', 'Client Name 3', 300.00, 'Rejected', 'Type 3', 'City 3', 'State 3', '34567');

-- finding total number of submssions for each consultant
SELECT consultant_id, COUNT(*) AS total_submissions
FROM submission
GROUP BY consultant_id;

-- finding total number of submssions for each consultant by submission day
SELECT consultant_id, submission_date, COUNT(*) AS total_submissions
FROM submission
GROUP BY consultant_id, submission_date;

-- Delete all submissions where "rate" is null
DELETE FROM submission
WHERE pay_rate IS NULL AND id > 0;

-- Retreiving submissions given lead name and submission date
SELECT submission.*
FROM submission
JOIN lead_detail ON submission.lead_id = lead_detail.id
WHERE lead_detail.first_name = 'John' 
  AND lead_detail.last_name = 'Doe'
  AND submission.submission_date = '2023-07-15';

-- finding number of submission by each lead
SELECT lead_detail.id, lead_detail.first_name, lead_detail.last_name, COUNT(submission.id) AS submission_count
FROM lead_detail
LEFT JOIN submission ON lead_detail.id = submission.lead_id
GROUP BY lead_detail.id, lead_detail.first_name, lead_detail.last_name;

-- Create submission_update table
CREATE TABLE submission_update (
  id INT PRIMARY KEY AUTO_INCREMENT,
  submission_id INT,
  update_text VARCHAR(255),
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (submission_id) REFERENCES submission(id)
);

