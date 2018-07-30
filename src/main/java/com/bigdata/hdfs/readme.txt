搭建HDFS的方法
1；搭建免密服务器
2；下载hadoop-2.6.5的包，解压
3；修改hadoop中etc下的配置文件，core-site.xml、hdfs-site.xml、

        zkServer.sh start   ||   zkServer.sh status
        hadoop-daemon.sh start journalnode

初始化；
    内存缓冲区  0.8f（阈值）80%的溢写  排序->溢写 环形缓冲区（默认100M） 环形存储
    快排-未进行io，溢写成为一个内部有序的小文件  基于内存的内排  无序重排序  之后均为有序重排序（归并排序，多个小文件->大文件）
    归并
原语---相同的key为一组，这一组数据调一次reduce方法
    比较器；
    排序比较；字典序，数值序
    用户是否干预排序相比较器，map输出自身的key的比较器

    combiner -little reduce  优化shuffle（数据拉取移动的过程），作业调优 job.setCombiner()  数据压缩


并行是提速的关键-分区

固定的十六个字节16b
            key的开始，value的开始，value的结束，P的信息  每一个四个字节
reduce端分组比较器
reduce的nextKeyValue()的工作
        对key和value赋值（反序列化）input中-真迭代器-指向reduce端 全量数据
        内存中仅需要存在两条，本条数据+下条数据
        预判断下一条与当前数据是否一组
        迭代val会触发nextKeyValue(),这个方法是对key和val的更新   是引用传递


        数据集的关联、操控能力

天气的测试案例
    找出每个月气温最高的两天
    tqTest      字符串代价过高
    时间为对象的属性，年月日，温度
    对象类型必须实现序列与反序列接口       write（序列） readFields（反序列）  compareTo（比较 ）
    排序---必须是实现排序比较器
    重写排序比较器
    重写分组比较器（if null  默认取的是排序比较器（不合适）key的比较器）必须重写
    默认排序为正序，包含日期，日期排序（优先日期），字符串-字典序

cli 、map、reduce
输入、输出
输入格式化。输出格式化
可以多个输入，但是只可以有一个输出
一个切片一个map --记录级数据   一行记录调一次map maptask
重写map  针对一条条记录施加自己已经定义的逻辑
分区器
mapoutputbuffer
超过阈值，发生溢写，溢写之前做排序

reduce
shuffer--拉取资源
分组比较器
触犯reduce主方法 reducetask
submit


分区器不能破坏原语 ---相同的key为一组，这一组数据调用一次reduce方法，方法内迭代这一组数据


dubbo和dubbox的区别就是提供了更高效的RPC序列化方式和REST调用方式


