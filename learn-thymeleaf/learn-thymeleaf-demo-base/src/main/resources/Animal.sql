create database JavaStudy;

use JavaStudy;

-- auto Generated on 2019-02-18
-- DROP TABLE IF EXISTS animal_form;
create table animal_form
(
	id    int(11)            not null auto_increment comment 'id',
	name  varchar(50) unique not null default '' comment 'name',
	count int(11)            not null default -1 comment 'count',
	memo  varchar(50)                 default '' comment 'memo',
	primary key (id)
) engine = InnoDB
  default charset = utf8mb4 comment 'animal_form';


insert into JavaStudy.animal_form (name, count, memo) values ('大马猴', 10, '机灵古怪，俏皮活泼');
insert into JavaStudy.animal_form (name, count, memo) values ('大熊猫', 80, '体型笨重，喜欢吃竹子');
insert into JavaStudy.animal_form (name, count, memo) values ('澳洲羊驼', 13, '长相奇特，大国人俗称其草泥马');
