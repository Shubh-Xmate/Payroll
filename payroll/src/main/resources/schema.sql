CREATE TABLE IF NOT EXISTS `salary` (
    `salary_id` int AUTO_INCREMENT PRIMARY KEY,
    `basic_salary` decimal(10,2) NOT NULL,
    `hra` decimal(10,2) NOT NULL,
    `allowances` decimal(10,2) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(30) DEFAULT NULL
);

CREATE TABLE if NOT EXISTS `payroll` (
    `payroll_id` int AUTO_INCREMENT PRIMARY KEY,
    `employee_id` int NOT NULL,
    `payroll_month` varchar(20) NOT NULL,
    `payroll_year` int NOT NULL,
    `deductions` decimal(10,2) NOT NULL,
    `net_salary` decimal(10,2) NOT NULL
);