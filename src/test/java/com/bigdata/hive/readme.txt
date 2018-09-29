编译，解释，优化
元数据；独立于集群的关系型数据   hive的元数据存储在关系型数据库
数据存储在HDFS中

CLi
Thrift Server RPC的访问

Hive的架构
（1）用户接口主要有三个：CLI，Client 和 WUI。其中最常用的是CLI，Cli启动的时候，会同时启动一个Hive副本。Client是Hive的客户端，用户连接至Hive Server。在启动 Client模式的时候，需要指出Hive Server所在节点，并且在该节点启动Hive Server。 WUI是通过浏览器访问Hive。   
（2）Hive将元数据存储在数据库中，如mysql、derby。Hive中的元数据包括表的名字，表的列和分区及其属性，表的属性（是否为外部表等），表的数据所在目录等。   
（3）解释器、编译器、优化器完成HQL查询语句从词法分析、语法分析、编译、优化以及查询计划的生成。生成的查询计划存储在HDFS中，并在随后有MapReduce调用执行。   
（4）Hive的数据存储在HDFS中，大部分的查询、计算由MapReduce完成（包含*的查询，比如select * from tbl不会生成MapRedcue任务）。

Hive的架构
编译器将一个Hive SQL转换操作符
操作符是Hive的最小的处理单元
每个操作符代表HDFS的一个操作或者一道MapReduce作业

Operator
Operator都是hive定义的一个处理过程
Operator都定义有:
protected List <Operator<?  extends Serializable >> childOperators; 
protected List <Operator<?  extends Serializable >> parentOperators; 
protected boolean done; // 初始化值为false


单用户模式
    通过网络连接到一个数据库中，是最经常使用到的模式

多用户模式
    远程服务器模式/多用户模式。
    用于非Java客户端访问元数据库，在服务器端启动MetaStoreServer，客户端利用Thrift协议通过MetaStoreServer访问元数据库

搭建mysql存储元数据
        安装mysql
        Yum install mysql-server -y
        修改mysql权限：
        GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root1234' WITH GRANT OPTION;
        flush privileges;
        删除多余会对权限造成影响的数据
        刷新权限


mysql驱动包
修改配置文件
修改jline这个包

    读时检查
    写时检查

    优化效率


Hive_Sql

Hive的数据类型
: primitive_type
  | array_type   同一种类型的数组
  | map_type     K-V
  | struct_type   类的属性
：primitive_type
  |TINYINT
  | SMALLINT
  | INT
  | BIGINT
  | BOOLEAN
  | FLOAT
  | DOUBLE
  | STRING

Loading files into tables

    LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE] INTO TABLE tablename [PARTITION (partcol1=val1, partcol2=val2 ...)]

    LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE] INTO TABLE tablename [PARTITION (partcol1=val1, partcol2=val2 ...)] [INPUTFORMAT 'inputformat' SERDE 'serde'] (3.0 or later)

读时检查、写时检查

内部表
    CREATE  TABLE [IF NOT EXISTS] table_name
    删除表时，元数据与数据都会被删除
外部表
    CREATE EXTERNAL TABLE [IF NOT EXISTS] table_name LOCATION hdfs_path
    删除外部表只删除metastore的元数据，不删除hdfs中的表数据


创建分区表
    静态分区、动态分区

    Hive 分区partition
    必须在表定义时指定对应的partition字段
    a、单分区建表语句：
    create table day_table (id int, content string) partitioned by (dt string);
    单分区表，按天分区，在表结构中存在id，content，dt三列。
    以dt为文件夹区分
    b、 双分区建表语句：
    create table day_hour_table (id int, content string) partitioned by (dt string, hour string);
    双分区表，按天和小时分区，在表结构中新增加了dt和hour两列。
    先以dt为文件夹，再以hour子文件夹区分

Hive添加分区表语法
    （表已创建，在此基础上添加分区）：
    ALTER TABLE table_name ADD [IF NOT EXISTS] PARTITION partition_spec  [LOCATION 'location1'] partition_spec [LOCATION 'location2'] ...;

    partition_spec:
    : (partition_column = partition_col_value, partition_column = partition_col_value, ...)
    例：
    ALTER TABLE day_table ADD PARTITION (dt='2008-08-08', hour='08')

Hive删除分区语法：

    ALTER TABLE table_name DROP partition_spec, partition_spec,...
    partition_spec:
    : (partition_column = partition_col_value, partition_column = partition_col_value, ...)

    用户可以用 ALTER TABLE DROP PARTITION 来删除分区。
    内部表中、对应分区的元数据和数据将被一并删除。

    例：
    ALTER TABLE day_hour_table DROP PARTITION (dt='2008-08-08', hour='09');

Hive向指定分区添加数据语法：

    LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE] INTO TABLE tablename [PARITION (partcol1=val1, partcol2=val2 ...)]

    例：
    LOAD DATA INPATH '/user/pv.txt' INTO TABLE day_hour_table PARTITION(dt='2008-08- 08', hour='08');
    LOAD DATA local INPATH '/user/hua/*' INTO TABLE day_hour partition(dt='2010-07- 07');

    当数据被加载至表中时，不会对数据进行任何转换。Load操作只是将数据复制至Hive表对应的位置。数据加载时在表下自动创建一个目录


Hive查询执行分区语法

    SELECT day_table.* FROM day_table WHERE day_table.dt>= '2008-08-08';

    分区表的意义在于优化查询。查询时尽量利用分区字段。如果不使用分区字段，就会全部扫描。


    Hive查询表的分区信息语法：
    SHOW PARTITIONS day_hour_table;



hive-dml
    FROM from_statement
    INSERT OVERWRITE TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...) [IF NOT EXISTS]] select_statement1
    [INSERT OVERWRITE TABLE tablename2 [PARTITION ... [IF NOT EXISTS]] elect_statement2]
    [INSERT INTO TABLE tablename2 [PARTITION ...] select_statement2] ...;

Hive SerDe - Serializer and Deserializer
    SerDe 用于做序列化和反序列化。
    构建在数据存储和执行引擎之间，对两者实现解耦。
    Hive通过ROW FORMAT DELIMITED以及SERDE进行内容的读写。
    row_format
    : DELIMITED
              [FIELDS TERMINATED BY char [ESCAPED BY char]]
              [COLLECTION ITEMS TERMINATED BY char]
              [MAP KEYS TERMINATED BY char]
              [LINES TERMINATED BY char]
    : SERDE serde_name [WITH SERDEPROPERTIES (property_name=property_value, property_name=property_value, ...)]

Hive正则匹配
     CREATE TABLE logtbl (
        host STRING,
        identity STRING,
        t_user STRING,
        time STRING,
        request STRING,
        referer STRING,
        agent STRING)
      ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.RegexSerDe'
      WITH SERDEPROPERTIES (
        "input.regex" = "([^ ]*) ([^ ]*) ([^ ]*) \\[(.*)\\] \"(.*)\" (-|[0-9]*) (-|[0-9]*)"
      )
      STORED AS TEXTFILE;


hive 参数设置方式

    1、修改配置文件 ${HIVE_HOME}/conf/hive-site.xml
    2、启动hive cli时，通过--hiveconf key=value的方式进行设置
    例：hive --hiveconf hive.cli.print.header=true
    3、进入cli之后，通过使用set命令设置

hive set命令
    在hive CLI控制台可以通过set对hive中的参数进行查询、设置
        set设置：
            set hive.cli.print.header=true;
        set查看
             set hive.cli.print.header
        hive参数初始化配置
             当前用户家目录下的.hiverc文件
             如:   ~/.hiverc
        如果没有，可直接创建该文件，将需要设置的参数写到该文件中，hive启动运行时，会加载改文件中的配置。
        hive历史操作命令集
            ~/.hivehistory

动态分区
    开启支持动态分区
    set hive.exec.dynamic.partition=true;
    默认：false
    set hive.exec.dynamic.partition.mode=nostrict;
    默认：strict（至少有一个分区列是静态分区）
    相关参数
    set hive.exec.max.dynamic.partitions.pernode;
    每一个执行mr节点上，允许创建的动态分区的最大数量(100)
    set hive.exec.max.dynamic.partitions;
    所有执行mr节点上，允许创建的所有动态分区的最大数量(1000)
    set hive.exec.max.created.files;
    所有的mr job允许创建的文件的最大数量(100000)

分桶
        分桶表是对列值取哈希值的方式，将不同数据放到不同文件中存储。
    对于hive中每一个表、分区都可以进一步进行分桶。
        由列的哈希值除以桶的个数来决定每条数据划分在哪个桶中。
    适用场景：
            数据抽样（ sampling ）、map-join

    桶表 抽样查询
        select * from bucket_table tablesample(bucket 1 out of 4 on columns);

    TABLESAMPLE语法：
        TABLESAMPLE(BUCKET x OUT OF y)
            x：表示从哪个bucket开始抽取数据
            y：必须为该表总bucket数的倍数或因子

当表总bucket数为32时
TABLESAMPLE(BUCKET 3 OUT OF 8)，抽取哪些数据？
共抽取2（32/16）个bucket的数据，抽取第2、第18（16+2）个bucket的数据
TABLESAMPLE(BUCKET 3 OUT OF 256)，抽取哪些数据？


开启支持分桶

        set hive.enforce.bucketing=true;

    默认：false；设置为true之后，mr运行时会根据bucket的个数自动分配reduce task个数。（用户也可以通过mapred.reduce.tasks自己设置reduce任务个数，但分桶时不推荐使用）
    注意：一次作业产生的桶（文件数量）和reduce task个数一致。

往分桶表中加载数据
    insert into table bucket_table select columns from tbl;
    insert overwrite table bucket_table select columns from tbl;

Hive Lateral View
    hive Lateral View
    Lateral View用于和UDTF函数（explode、split）结合来使用。
    首先通过UDTF函数拆分成多行，再将多行结果组合成一个支持别名的虚拟表。
    主要解决在select使用UDTF做查询过程中，查询只能包含单个UDTF，不能包含其他字段、以及多个UDTF的问题

    语法：
    LATERAL VIEW udtf(expression) tableAlias AS columnAlias (',' columnAlias)

    select count(distinct(myCol1)), count(distinct(myCol2)) from psn2
    LATERAL VIEW explode(likes) myTable1 AS myCol1
    LATERAL VIEW explode(address) myTable2 AS myCol2, myCol3


Hive 视图
    hive View视图
    和关系型数据库中的普通视图一样，hive也支持视图
    特点：
    不支持物化视图
    只能查询，不能做加载数据操作
    视图的创建，只是保存一份元数据，查询视图时才执行对应的子查询
    view定义中若包含了ORDER BY/LIMIT语句，当查询视图时也进行ORDER BY/LIMIT语句操作，view当中定义的优先级更高
    view支持迭代视图

    View语法
    创建视图：
    CREATE VIEW [IF NOT EXISTS] [db_name.]view_name
      [(column_name [COMMENT column_comment], ...) ]
      [COMMENT view_comment]
      [TBLPROPERTIES (property_name = property_value, ...)]
      AS SELECT ... ;
    查询视图：
    select colums from view;
    删除视图：
    DROP VIEW [IF EXISTS] [db_name.]view_name;


hive索引
    目的：优化查询以及检索性能
    创建索引：
    create index t1_index on table psn2(name)
    as 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler' with deferred rebuild
    in table t1_index_table;
    as：指定索引器；
    in table：指定索引表，若不指定默认生成在default__psn2_t1_index__表中

    create index t1_index on table psn2(name)
    as 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler' with deferred rebuild;

    查询索引
    show index on psn2;

    重建索引（建立索引之后必须重建索引才能生效）
    ALTER INDEX t1_index ON psn2 REBUILD;

    删除索引
    DROP INDEX IF EXISTS t1_index ON psn2;

Hive 运行方式
    Hive在CLI模式中
        与hdfs交互
            执行执行dfs命令
                例：dfs –ls /
            与Linux交互
                ！开头
                 例： !pwd
    Hive脚本运行方式：
        hive -e ""
        hive -e "">aaa
        hive -S -e "">aaa  静默输出
        hive -f file
        hive -i /home/my/hive-init.sql
        hive> source file (在hive cli中运行)


hive优化
    Hive 优化
    核心思想：把Hive SQL 当做Mapreduce程序去优化
    以下SQL不会转为Mapreduce来执行
        select仅查询本表字段
        where仅对本表字段做条件过滤

    Explain 显示执行计划
    EXPLAIN [EXTENDED] query

    Hive运行方式：
        本地模式
        集群模式

    本地模式
        开启本地模式：
        set hive.exec.mode.local.auto=true;
        注意：
        hive.exec.mode.local.auto.inputbytes.max默认值为128M
        表示加载文件的最大值，若大于该配置仍会以集群方式来运行！

    并行计算
        通过设置以下参数开启并行模式：
            set hive.exec.parallel=true;

        注意：hive.exec.parallel.thread.number
        （一次SQL计算中允许并行执行的job个数的最大值）

    严格模式
        通过设置以下参数开启严格模式：
        set hive.mapred.mode=strict;
        （默认为：nonstrict非严格模式）

        查询限制：
        1、对于分区表，必须添加where对于分区字段的条件过滤；
        2、order by语句必须包含limit输出限制；
        3、限制执行笛卡尔积的查询。

    Hive排序
         Order By - 对于查询结果做全排序，只允许有一个reduce处理（当数据量较大时，应慎用。严格模式下，必须结合limit来使用）

         Sort By - 对于单个reduce的数据进行排序

         Distribute By - 分区排序，经常和Sort By结合使用

         Cluster By - 相当于 Sort By + Distribute By（Cluster By不能通过asc、desc的方式指定排序规则；
            可通过 distribute by column sort by column asc|desc 的方式）

     Hive Join
         自动的mapjoin
             通过修改以下配置启用自动的mapjoin：
             set hive.auto.convert.join = true;
             （该参数为true时，Hive自动对左边的表统计量，如果是小表就加入内存，即对小表使用Map join）

         相关配置参数：
             hive.mapjoin.smalltable.filesize;
             （大表小表判断的阈值，如果表的大小小于该值则会被加载到内存中运行）

             hive.ignore.mapjoin.hint；
             （默认值：true；是否忽略mapjoin hint 即mapjoin标记）

             hive.auto.convert.join.noconditionaltask;
             （默认值：true；将普通的join转化为普通的mapjoin时，是否将多个mapjoin转化为一个mapjoin）

             hive.auto.convert.join.noconditionaltask.size;
             （将多个mapjoin转化为一个mapjoin时，其表的最大值）

    Map-Side聚合
        通过设置以下参数开启在Map端的聚合：
        set hive.map.aggr=true;

        相关配置参数：
            hive.groupby.mapaggr.checkinterval：
            map端group by执行聚合时处理的多少行数据（默认：100000）

            hive.map.aggr.hash.min.reduction：
            进行聚合的最小比例（预先对100000条数据做聚合，若聚合之后的数据量/100000的值大于该配置0.5，则不会聚合）

            hive.map.aggr.hash.percentmemory：
            map端聚合使用的内存的最大值

            hive.map.aggr.hash.force.flush.memory.threshold：
            map端做聚合操作是hash表的最大可用内容，大于该值则会触发flush

            hive.groupby.skewindata
            是否对GroupBy产生的数据倾斜做优化，默认为false

    控制Hive中Map以及Reduce的数量
        Map数量相关的参数
            mapred.max.split.size
            一个split的最大值，即每个map处理文件的最大值

            mapred.min.split.size.per.node
            一个节点上split的最小值

            mapred.min.split.size.per.rack
            一个机架上split的最小值

        Reduce数量相关的参数
            mapred.reduce.tasks
            强制指定reduce任务的数量

            hive.exec.reducers.bytes.per.reducer
            每个reduce任务处理的数据量

            hive.exec.reducers.max
            每个任务最大的reduce数

    Hive - JVM重用
        适用场景：
        1、小文件个数过多
        2、task个数过多

        通过 set mapred.job.reuse.jvm.num.tasks=n; 来设置
        （n为task插槽个数）

        缺点：设置开启之后，task插槽会一直占用资源，不论是否有task运行，直到所有的task即整个job全部执行完成时，才会释放所有的task插槽资源！


