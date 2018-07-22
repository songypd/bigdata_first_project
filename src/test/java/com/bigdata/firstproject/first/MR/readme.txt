配置文件
分别启动rm节点
端口默认8088
由访问standBy节点会跳转到active节点
分布式程序--仅仅只是计算的核心程序--核心逻辑代码
hadoop jar 分布式程序 方法 输入数据集目录s（多个输入）  输出数据集目录
reduce可以为0，但是map必须有
hdfs dfs -get 文件目录1   文件目录2   将hdfs文件目录1上的文件拷贝到另外的文件目录2上
for循环的shell脚本生成一万条数据

一个时间内，内存中只存在一条待分析的数据，不是基于内存计算的，将数据序列化放入一个内存字节数组中去，然后不停的做迭代，将数据放入，当内存字节数组过大时，将会
把数据进行磁盘IO到磁盘上

序列化，反序列化的过程----对象->字节数组->对象

MR原语；相同的key为一组，这一组数据调一次reduce方法，reduce对这一组数据进行迭代运算

splits 最小值默认是1  最大值是long.maxValue(),

熟悉mapReduce的源码。splits、combinner、shuffle的源码
默认情况下，切片大小等于块大小   人工干预切片大小    math.max(minsize,math.min(maxsize,blocksize))

mapTask.class reduceTask.class






