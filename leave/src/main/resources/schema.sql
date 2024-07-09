CREATE TABLE if NOT EXISTS `leaveDetails` (
    `employee_id` int,
    `name` varchar(200) not null,
    `email` varchar(100) not null,
    `mobileNumber` varchar(20) not null,
    `createdAt` date NOT NULL,
    `createdBy` varchar(30) NOT NULL,
    `updatedAt` date DEFAULT NULL,
    `updatedBy` varchar(30) DEFAULT NULL
);
