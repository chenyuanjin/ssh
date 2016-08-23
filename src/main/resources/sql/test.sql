-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2016-01-14 03:43:22
-- 服务器版本： 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;

DELIMITER $$
--
-- 存储过程
--
DROP PROCEDURE IF EXISTS `randuser_connection`$$
CREATE DEFINER=`cpp`@`%` PROCEDURE `randuser_connection`(k int)
begin
  declare i int;
  set i=0;
  while i<k*5 do
     insert ignore into tbl_user_connection_t(self_id,friend_id,family,colleague,schoolmate,business) 
values(floor(rand()*50+1),floor(rand()*50+1),round(rand()),round(rand()),round(rand()),round(rand()));
      set i=i+1;
  end while;
  end$$

DROP PROCEDURE IF EXISTS `test_multi_sets`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `test_multi_sets`()
    DETERMINISTIC
begin
        select user() as first_col;
        select user() as first_col, now() as second_col;
        select user() as first_col, now() as second_col, now() as third_col;
        end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
`id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`id`, `name`, `create_time`) VALUES
(1, 'Eric', '2016-01-13 13:55:19'),
(2, 'David', '2016-01-13 13:57:26'),
(3, 'Bob', '2016-01-13 13:57:26'),
(4, 'liyan', '2016-01-13 13:57:51'),
(5, 'gaohuan', '2016-01-13 13:57:51');

-- --------------------------------------------------------

--
-- 表的结构 `student_teacher`
--

DROP TABLE IF EXISTS `student_teacher`;
CREATE TABLE IF NOT EXISTS `student_teacher` (
  `student_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
`id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `student_teacher`
--

INSERT INTO `student_teacher` (`student_id`, `teacher_id`, `id`) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 1, 3),
(2, 2, 4),
(3, 1, 5),
(4, 1, 6),
(5, 2, 7);

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
`id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='教师表';

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `create_time`) VALUES
(1, 'Tom', '2016-01-13 13:58:16'),
(2, 'Bob', '2016-01-13 13:58:16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student`
--
ALTER TABLE `student`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_teacher`
--
ALTER TABLE `student_teacher`
 ADD PRIMARY KEY (`id`), ADD KEY `teacher_id` (`teacher_id`), ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `student_teacher`
--
ALTER TABLE `student_teacher`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


--
-- 表的结构 `sys_users`
--

CREATE TABLE IF NOT EXISTS `sys_users` (
  `id` char(32) NOT NULL,
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `user_account` varchar(60) NOT NULL COMMENT '帐号',
  `user_password` varchar(45) NOT NULL COMMENT '密码',
  `user_type` int(11) DEFAULT NULL COMMENT '类型',
  `is_account_non_expired` tinyint(1) DEFAULT NULL,
  `is_account_non_locked` tinyint(1) DEFAULT NULL,
  `is_credentials_non_expired` tinyint(1) DEFAULT NULL,
  `is_enabled` tinyint(1) DEFAULT NULL,
  `is_sys` tinyint(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator_id` char(32) DEFAULT NULL,
  `job` char(32) DEFAULT NULL,
  `owner` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;