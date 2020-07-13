DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `id` BIGINT(11)  NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `open_id` varchar(100),
  `union_id` varchar(100),
  PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS check_in;
CREATE TABLE `check_in` (
  `id` BIGINT(11)  NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  `form_id` varchar(100) NOT NULL,
  `check_in_time` datetime ,
  `create_time` datetime ,
  PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS integral;
CREATE TABLE `integral` (
  `id` BIGINT(11)  NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  `integral` bigint NOT NULL,
  `create_time` datetime ,
  `update_time` datetime ,
  PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS integral_record;
CREATE TABLE `integral_record` (
  `id` BIGINT(11)  NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  `integral_increase` bigint NOT NULL,
  `reason` varchar(100),
  `type` int(1),
  `update_time` datetime ,
  PRIMARY KEY (`id`)
);
