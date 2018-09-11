


启动flume的方式
    bin/flume-ng agent -n $agent_name -c conf -f conf/flume-conf.properties.template
    bin/flume-ng agent --conf conf --conf-file example.conf --name a1 -Dflume.root.logger=INFO,console


flume 配置文件的解释
    修改flume_env.sh   ->>jdk,内存大小
    配置环境变量

官网地址；
        http://flume.apache.org/FlumeUserGuide.html

由flume将日志信息记录到hdfs中，相关配置文件记录在此package下

        动态的在hdfs中创建目录
        增量分析-》增量
                -》增量查询是一种大趋势
        按照分析的时间间隔创建目录