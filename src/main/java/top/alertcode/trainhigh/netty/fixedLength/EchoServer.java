package top.alertcode.trainhigh.netty.fixedLength;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alertcode
 * @date 2018-10-07
 * @copyright alertcode.top
 */
public class EchoServer {

  public static void main(String[] args) {
    new EchoServer().bind(8080);
  }

  void bind(int port) {
    NioEventLoopGroup worker = new NioEventLoopGroup();
    NioEventLoopGroup base = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(base, worker).channel(NioServerSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
          .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
          socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(20))
              .addLast(new StringDecoder()).addLast(new EchoServerHandler());
        }
      });

      ChannelFuture f = b.bind(port).sync();
      f.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
      worker.shutdownGracefully();
      base.shutdownGracefully();
    }
  }
}

@Slf4j
class EchoServerHandler extends ChannelInboundHandlerAdapter {


  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    String body = (String) msg;
    log.info("this is server,receive client:{}", body);
    ByteBuf byteBuf = Unpooled.copiedBuffer(body.getBytes());
    ctx.writeAndFlush(byteBuf);

  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }
}
