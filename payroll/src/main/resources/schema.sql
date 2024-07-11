CREATE TABLE IF NOT EXISTS `salary` (
    `salary_id` int AUTO_INCREMENT PRIMARY KEY,
    `basic_salary` decimal(10,2) NOT NULL,
    `hra` decimal(10,2) NOT NULL,
    `allowances` decimal(10,2) NOT NULL
);

CREATE TABLE if NOT EXISTS `payroll` (
    `payroll_id` int AUTO_INCREMENT PRIMARY KEY,
    `employee_id` int NOT NULL,
    `payrollMonth` varchar(20) NOT NULL,
    `payrollYear` int NOT NULL,
    `deductions` decimal(10,2) NOT NULL,
    `netSalary` decimal(10,2) NOT NULL
);