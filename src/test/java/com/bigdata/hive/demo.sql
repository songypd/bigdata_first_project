

create TEMPORARY table IF NOT EXISTS test_person_info
(
id int ,
names string,
likes array<string>,
addr map<string,string>
)
row format DELIMITED
FIELDS TERMINATED BY ","
COLLECTION ITEMS TERMINATED BY "-"
MAP KEYS TERMINATED BY ":"
LINES TERMINATED BY "\n";
[NULL DEFINED AS char]

1,小明1,lol-book-movie,beijing:shangxuetang-shanghai:pudong


create table IF NOT EXISTS test_person_info
(
id int ,
names string,
likes array<string>,
addr map<string,string>
)
row format DELIMITED
FIELDS TERMINATED BY ","
COLLECTION ITEMS TERMINATED BY "-"
MAP KEYS TERMINATED BY ":"
LINES TERMINATED BY "\n";


--基站掉话率
create table if not EXISTS call_info
(
	record_time string,
	imei string,
	cell string,
	ph_num int,
	call_num int,
	drop_num int,
	duration int,
	drop_rate DOUBLE,
	net_type string,
	erl string
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;


create table call_result(
imei string,
total_call_num int,
total_drop_num int,
d_rate DOUBLE
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;



from call_info ci
insert overwrite table call_result
select ci.imei,sum(ci.drop_num),sum(ci.duration),sum(ci.duration)/sum(ci.drop_num) drop_rate
group by ci.imei
sort by drop_rate desc;



--wordcount
create table if not exists wc(
word String
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;

create table if not exists wc_result(
word String,
count int
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;

from (select explode(split(word,' ')) as word from wc) w
insert overwrite table wc_result
select w.word ,count(1) as total
group by w.word
sort by w.word;


--动态分区

from TABLE_NAME
insert overwrite table TABLE_NAME partition(COLUMN_01, COLUMN_02)
select columns distribute by COLUMN_01, COLUMN_02

-- 内部表
-- 外部表
-- 1、外部表创建的时候需要制定数据目录
-- 2、删除数据的时候内部表将表结构和数据全部删除，外部表只删除表结构，并不删除数据

-- 创建分区表  demo
create table psn2(
id int,
name string,
likes array<string>,
address map<string,string>
)
PARTITIONED BY (age int)
row format DELIMITED
FIELDS TERMINATED BY ','
COLLECTION ITEMS TERMINATED BY '-'
MAP KEYS TERMINATED BY ':'
LINES TERMINATED BY '\n';

-- 不能将分区字段定义到表中间


-- 创建分桶表
CREATE TABLE psnbucket( id INT, name STRING, age INT)
CLUSTERED BY (age) INTO 4 BUCKETS
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';

-- 加载数据：
insert into table psnbucket select id, name, age from psn31;

-- 抽样
select id, name, age from psnbucket tablesample(bucket 2 out of 4 on age);








