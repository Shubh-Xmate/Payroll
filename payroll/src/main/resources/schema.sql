CREATE TABLE IF NOT EXISTS `salary` (
    `salary_id` int AUTO_INCREMENT PRIMARY KEY,
    `basic_salary` decimal(10,2) NOT NULL,
    `hra` decimal(10,2) NOT NULL,
    `allowances` decimal(10,2) NOT NULL
);

INSERT INTO salary (basic_salary, hra, allowances) VALUES (150000.0, 45000.0, 20000.0);
INSERT INTO salary (basic_salary, hra, allowances) VALUES (100000.0, 35000.0, 10000.0);
--INSERT INTO salary (basic_salary, hra, allowances) VALUES (50000.0, 25000.0, 8000.0);
--INSERT INTO salary (basic_salary, hra, allowances) VALUES (35000.0, 15000.0, 5000.0);
--INSERT INTO salary (basic_salary, hra, allowances) VALUES (20000.0, 10000.0, 2000.0);

CREATE TABLE if NOT EXISTS `payroll` (
    `payroll_id` int AUTO_INCREMENT PRIMARY KEY,
    `employee_id` int NOT NULL,
    `payrollMonth` varchar(20) NOT NULL,
    `payrollYear` int NOT NULL,
    `deductions` decimal(10,2) NOT NULL,
    `netSalary` decimal(10,2) NOT NULL
);