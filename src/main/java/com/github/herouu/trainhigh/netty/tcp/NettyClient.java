package com.github.herouu.trainhigh.netty.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
              pipeline.addLast(new TCPClientHandler());
            }
          });
      cf1 = b.connect("127.0.0.1", 8765).sync();
      System.out.println("客户端启动，发送消息。。。。。。");
      //buf
      int count = 0;
      Channel channel = cf1.channel();
      while (true) {
        channel.write(Unpooled.copiedBuffer("来自客户端消息！".getBytes()));
        Thread.sleep(1000);
        count++;
        if (count == 10) {
          channel.flush();
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

class TCPClientHandler extends ChannelInboundHandlerAdapter{

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {


  }
}
