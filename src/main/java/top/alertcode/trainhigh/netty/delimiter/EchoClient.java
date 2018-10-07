package top.alertcode.trainhigh.netty.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alertcode
 * @date 2018-10-07
 * @copyright alertcode.top
 */
public class EchoClient {

  public static void main(String[] args) {
    new EchoClient().connect(8080, "localhost");
  }

  void connect(int port, String host) {
    NioEventLoopGroup client = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(client).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
        .handler(
            new ChannelInitializer<SocketChannel>() {
              @Override
              protected void initChannel(SocketChannel socketChannel) throws Exception {
                ByteBuf byteBuf = Unpooled.copiedBuffer("$_".getBytes());
                socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf))
                    .addLast(new StringDecoder()).addLast(new EchoClientHandler());

              }
            });
    try {
      ChannelFuture f = bootstrap.connect(host, port).sync();
      f.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      client.shutdownGracefully();
    }
  }
}

@Slf4j
class EchoClientHandler extends ChannelInboundHandlerAdapter {

  private int counter;

  static final String ECHO_MESSAGE = "Hi alertcode,this is Netty.." + "$_";

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    for (int i = 0; i < 10; i++) {
      ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_MESSAGE.getBytes()));
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    log.info("this is counter:{} time receive server:{}", ++counter, msg);
  }
}