-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 08, 2019 at 10:20 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mcq`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`name`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `no` int(11) NOT NULL,
  `marks` int(11) NOT NULL,
  `question` varchar(4000) NOT NULL,
  `a` varchar(100) NOT NULL,
  `b` varchar(100) NOT NULL,
  `c` varchar(100) NOT NULL,
  `d` varchar(100) NOT NULL,
  `answer` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`no`, `marks`, `question`, `a`, `b`, `c`, `d`, `answer`) VALUES
(1, 1, 'Updates that violate __________ are disallowed', 'Integrity constraints', 'Transaction control', 'Authorization', 'DDL constraints', 'Integrity constraints'),
(2, 1, 'Updates that violate __________ are disallowed', 'Integrity constraints', 'Transaction control', 'Authorization', 'DDL constraints', 'Integrity constraints'),
(3, 1, 'Which one of the following is used to define the structure of the relation ,deleting relations and relating schemas ?', 'DML(Data Manipulation Langauge)', 'DDL', 'Query', 'Relational Schema', 'DML(Data Manipulation Langauge)'),
(4, 1, 'Create table employee (name varchar ,id integer) What type of statement is this ?', 'DML', 'DDL', 'View', 'Integrity constraint', 'DDL'),
(5, 1, 'Select * from employee What type of statement is this?', 'DML', 'DDL', 'View', 'Integrity constraint', 'DML'),
(6, 1, 'To remove a relation from an SQL database, we use the ______ command.', 'Delete', 'Purge', 'Remove', 'Drop table', 'Drop table'),
(7, 1, ' Delete from r; r ? relation This command performs which of the following action ?', 'Remove relation', 'Clear relation entries', 'Delete fields', 'Delete rows', 'Clear relation entries'),
(8, 1, ' Delete from r; r ? relation This command performs which of the following action ?', 'Remove relation', 'Clear relation entries', 'Delete fields', 'Delete rows', 'Clear relation entries'),
(9, 1, 'Updates that violate __________ are disallowed', 'Integrity constraints', 'Transaction control', 'Authorization', 'DDL constraints', 'Integrity constraints'),
(10, 1, 'Which one of the following is used to define the structure of the relation ,deleting relations and relating schemas ?', 'DML(Data Manipulation Langauge)', 'DDL', 'Query', 'Relational Schema', 'DML(Data Manipulation Langauge)'),
(11, 1, ' Delete from r; r ? relation This command performs which of the following action ?', 'Remove relation', 'Clear relation entries', 'Delete fields', 'Delete rows', 'Clear relation entries'),
(12, 1, 'Updates that violate __________ are disallowed', 'Integrity constraints', 'Transaction control', 'Authorization', 'DDL constraints', 'Integrity constraints'),
(13, 2, 'To remove a relation from an SQL database, we use the ______ command.', 'Delete', 'DML(Data Manipulation Langauge)', 'Delete fields', 'd', 'Delete fields'),
(14, 1, 'What is C?', '', '', '', '', ''),
(15, 2, 'What is C?', 'a', 'b', 'c', 'd', 'd');

-- --------------------------------------------------------

--
-- Table structure for table `quizsetting`
--

CREATE TABLE `quizsetting` (
  `date` date NOT NULL,
  `time` int(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quizsetting`
--

INSERT INTO `quizsetting` (`date`, `time`, `password`, `id`) VALUES
('2019-10-08', 15, '12345', 1);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `name` int(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `attempt` varchar(10) DEFAULT NULL,
  `marks` varchar(20) DEFAULT NULL,
  `total` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`name`, `password`, `attempt`, `marks`, `total`) VALUES
(-2, '1', '1', '-1', '-1'),
(0, 'student', '1', '-1', '-1'),
(1, '123', '1', '-1', '-1'),
(11, '11', '1', '-1', '-1'),
(12, '12', '1', '-1', '-1'),
(15, '15', '1', '-1', '-1'),
(100, '100', '1', '-1', '-1'),
(101, '101', '1', '-1', '-1'),
(123, '123', '1', '-1', '-1'),
(124, '124', '1', '-1', '-1'),
(10000, 'lksihdik', '1', '-1', '-1'),
(1404019, '1404019', '1', '-1', '-1'),
(1504001, '1504001', '0', '0', '17'),
(1504003, '1504003', '1', '-1', '-1'),
(1504005, '1504005', '1', '-1', '-1'),
(1654004, '1654004', '1', '-1', '-1'),
(1654008, '1654008', '1', '-1', '-1'),
(1654020, '1654020', '1', '-1', '-1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
