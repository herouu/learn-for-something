package com.github.herouu.trainhigh.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty 中比较重要的几个对象 参考 http://tutorials.jenkov.com/netty/overview.html
 * 1. Bootstrap、ServerBootstrap  是一个启动 NIO 服务的辅助启动类 Bootstrap 客户端 ServerBootStrap 服务端
 * 2. EventLoopGroop LoopGroop 是netty 线程组，server端启动两个线程组，一个是用于接收Client端连接的，另一个用来处理业务逻辑
 * 3. Channel(SocketChannel) 与网络上的的其他机器建立TCP连接
 * 4. ChannelInitializer 建立Socket对象，初始化Socket的信息
 * 5. ChannelPipeline 每一个SocketChannel都有一个ChannelPipeline，ChannelPipeline对象包含多个ChannelHandler,
 * 内部的ChannelHandler依次调用
 * 6. ChannelHandler 处理来自SocketChannel的数据，并可以写入SocketCannel
 *
 * @author alertcode
 * @date 2018-09-15
 * @copyright alertcode.top
 */
public class NettyServer {


  public static void main(String[] args) throws Exception {
    //1 第一个线程组 是用于接收Client端连接的
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    //2 第二个线程组 是用于实际的业务处理操作的
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    ServerBootstrap b;

    try {
      //3 创建一个辅助类Bootstrap，就是对我们的Server进行一系列的配置
      b = new ServerBootstrap();
      //把俩个工作线程组加入进来
      b.group(bossGroup, workerGroup);
      b.channel(NioServerSocketChannel.class);
      b.option(ChannelOption.SO_BACKLOG, 1024);
      b.childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
          ChannelPipeline pipeline = channel.pipeline();
          pipeline.addLast(new ServerHandler());
        }
      });

      //绑定指定的端口 进行监听
      ChannelFuture f = b.bind(8765).sync();
      System.out.println("服务器启动，监听请求。。。。。。");
      //等待服务监听端口关闭
      f.channel().closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

}
