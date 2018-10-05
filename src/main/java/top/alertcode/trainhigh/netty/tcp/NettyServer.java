package top.alertcode.trainhigh.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
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
          pipeline.addLast(new TCPServerHandler());
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

class TCPServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    super.channelRead(ctx, msg);
  }
}
