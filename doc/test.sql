### 分别在两个库中创建person表
CREATE TABLE `person` (
  `id`   int(11)      DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

### 在test2中插入
INSERT INTO `test2`.`person` (`id`, `name`)
VALUES ('2', 'datasource2');
### 在test1中插入
INSERT INTO `test2`.`person` (`id`, `name`)
VALUES ('1', 'datasource1');
