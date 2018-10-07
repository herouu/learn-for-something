package top.alertcode.trainhigh.netty.lineBased;

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
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alertcode
 * @date 2018-10-06
 * @copyright alertcode.top
 */
public class TimeClient {

  public static void main(String[] args) {
    new TimeClient().connect(8080, "localhost");
  }

  void connect(int port, String host) {
    NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(eventExecutors).channel(NioSocketChannel.class)
        .option(ChannelOption.TCP_NODELAY, true).handler(

        new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            //使用LineBasedFrameDecoder和StringDecoder解决读半包问题
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            //使用分隔符解决
            //ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
            //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
            //socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeClientHandler());
          }
        });
    try {
      ChannelFuture f = bootstrap.connect(host, port).sync();
      f.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      eventExecutors.shutdownGracefully();
    }
  }
}

@Slf4j
class TimeClientHandler extends ChannelInboundHandlerAdapter {

  private int counter;

  private byte[] req;

  public TimeClientHandler() {
    // System.getProperty("line.separator")) 换行符
    req = ("client message" + System.getProperty("line.separator")).getBytes();
    //使用分隔符
    //req = ("client message" + "$_").getBytes();

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ByteBuf message = null;
    for (int i = 0; i < 100; i++) {
      message = Unpooled.buffer(req.length);
      message.writeBytes(req);
      ctx.writeAndFlush(message);
    }
    ctx.writeAndFlush(req);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//    ByteBuf buf = (ByteBuf) msg;
//    byte[] bytes = new byte[buf.readableBytes()];
//    buf.readBytes(bytes);
//    String body = new String(bytes, "UTF-8");
    String body = (String) msg;
    log.info("now is :{},the counter is :{}", body, ++counter);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    log.info("client is exception", cause.getMessage());
    ctx.close();
  }
}
