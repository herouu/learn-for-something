package top.alertcode.trainhigh.netty.tcppackage;

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
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alertcode
 * @date 2018-10-06
 * @copyright alertcode.top
 */
@Slf4j
public class TimeServer {

  public static void main(String[] args) {
    int port = 8080;
    new TimeServer().bind(port);
  }

  void bind(int port) {
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    serverBootstrap.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {

      @Override
      protected void initChannel(SocketChannel socketChannel) throws Exception {
        //使用LineBasedFrameDecoder和StringDecoder解决半包问题
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //使用DelimiterBasedFrameDecoder自定义分隔符解决半包问题
        //ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
        //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
        socketChannel.pipeline().addLast(new StringDecoder());
        socketChannel.pipeline().addLast(new TimeServerHandler());

      }
    });
    try {
      ChannelFuture sync = serverBootstrap.bind(port).sync();
      sync.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

}

@Slf4j
class TimeServerHandler extends ChannelInboundHandlerAdapter {

  private int counter;

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    //tcp 拆包粘包问题
//    ByteBuf buf = (ByteBuf) msg;
//    byte[] bytes = new byte[buf.readableBytes()];
//    buf.readBytes(bytes);
//    String body = new String(bytes, "UTF-8").substring(0, bytes.length);


    String body = (String) msg;
    String rep =
        "client message".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString()
            : "bad message";
    //使用LineBasedFrameDecoder和StringDecoder解决半包问题,根据换行符进行分割
    rep = rep + System.getProperty("line.separator");
    //使用自定义分隔符"$_"进行分割
    //rep = rep + "$_";
    log.info("the time server receive order:{} counter:{}", body, ++counter);
    ByteBuf buf1 = Unpooled.copiedBuffer(rep.getBytes());
    ctx.writeAndFlush(buf1);
  }


  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }

}
