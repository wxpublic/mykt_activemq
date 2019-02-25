# mykt_activemq
activeMQ点对点和发布订阅两种模式的基础案例

# 测试步骤:

1、首先安装activeMQ服务；参考：https://blog.csdn.net/sheinenggaosuwo/article/details/87881865

2、导入idea项目，然后右键启动测试：

p2p对应点对点（常规模式）：先启动生产者，生产者会把请求放在队列中，后启动消费者，由消费者获取消费，一个消费者对应一个队列，一一对应；

p2m对应发布订阅模式（很少用）：先启动消费者，消费者会监听activeMQ服务，再启动生产者，启动时，就会发布消息，与此同时，消费者会马上消费；基本具有实时性，
