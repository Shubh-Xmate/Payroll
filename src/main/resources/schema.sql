CREATE TABLE IF NOT EXISTS `salary` (
    `salary_id` int PRIMARY KEY,
    `basic_salary` decimal(10,2) NOT NULL,
    `hra` decimal(10,2) NOT NULL,
    `allowances` decimal(10,2) NOT NULL
);