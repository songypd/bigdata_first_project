pageRank案例

用于衡量特定网页相对于搜索引擎索引中的其他网页而言的重要程度
pageRank实现了将链接价值概念作为排名的因素
    入链数量
        如果一个页面节点接收到的其他网页指向的入链数量越多，那么这个页面越重要
    入链质量
        指向页面A的入链质量不同，质量高的页面会通过链接向其他页面传递更多的权重。所以越是质量高的页面指向页面A，则页面A越重要。

投票
加和


MR的运行方式
    单机  mapreduce.framework.name   local
    集群--客户端将计算程序提交

