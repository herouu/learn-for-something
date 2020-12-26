package com.github.herouu.trainhigh.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author alertcode
 * @date 2018-09-15
 * @copyright alertcode.top
 */
public class NettyClient {

  public static void main(String[] args) throws Exception {

    EventLoopGroup workgroup = new NioEventLoopGroup();
    Bootstrap b = new Bootstrap();
    ChannelFuture cf1;
    try {
      b.group(workgroup)
          .channel(NioSocketChannel.class)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
              //ChannelPipeline 负责安排handler的执行顺序de
              ChannelPipeline pipeline = sc.pipeline();
              pipeline.addLast(new ClientHandler());
            }
          });

      cf1 = b.connect("127.0.0.1", 8765).sync();
      System.out.println("客户端启动，发送消息。。。。。。");
      //buf
      int count = 0;
      while (true) {
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("来自客户端消息！".getBytes()));
        Thread.sleep(1000);
        count++;
        if (count == 10) {
          cf1.channel().closeFuture().sync();
          System.out.println("发送消息结束。。。。。。。");
          return;
        }
      }

    } finally {
      workgroup.shutdownGracefully();
    }
  }
}
