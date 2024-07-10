CREATE TABLE if NOT EXISTS `leaveDetails` (
    `leave_detail_id` int AUTO_INCREMENT PRIMARY KEY,
    `employee_id` int NOT NULL,
    `name` varchar(200) not null,
    `email` varchar(100) not null,
    `mobile_number` varchar(20) not null,
    `created_at` date NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(30) DEFAULT NULL
);

CREATE TABLE if NOT EXISTS `leave` (
    `leave_id` int AUTO_INCREMENT PRIMARY KEY,
    `employee_id` int NOT NULL,
    `leave_type` varchar(20) NOT NULL,
    `start_date` date NOT NULL,
    `end_date` date NOT NULL,
    `applied_date` date NOT NULL,
    `status` varchar(30) NOT NULL,
    `comments` varchar(200) NOT NULL,
    `manager_id` int NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(30) DEFAULT NULL

);
