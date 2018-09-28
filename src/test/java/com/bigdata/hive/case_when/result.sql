--1,a
--1,b

create table id_course(
id int ,
course string
)
row format DELIMITED
FIELDS TERMINATED BY ","
LINES TERMINATED BY "\n";

SELECT  id ,
sum (case course when "a" then 1 else 0 end) as a,
sum (case course when "b" then 1 else 0 end) as b,
sum (case course when "c" then 1 else 0 end) as c,
sum (case course when "d" then 1 else 0 end) as d,
sum (case course when "e" then 1 else 0 end) as e,
sum (case course when "f" then 1 else 0 end) as f
from id_course GROUP by id ORDER by id ;

