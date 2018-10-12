package top.alertcode.trainhigh.netty.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;
import top.alertcode.trainhigh.netty.protobuf.SubscribeReqProto.SubscribeReq;
import top.alertcode.trainhigh.netty.protobuf.SubscribeReqProto.SubscribeReq.Builder;
import top.alertcode.trainhigh.netty.protobuf.SubscribeRespProto.SubscribeResp;


/**
 * @author alertcode
 * @date 2018-10-12
 * @copyright alertcode.top
 */
public class SubReqClient {

  void connect(String host, int port) {
    NioEventLoopGroup client = new NioEventLoopGroup();
    Bootstrap b = new Bootstrap();
    b.group(client).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {

      @Override
      protected void initChannel(SocketChannel ch) throws Exception {
//        使用protobuf
        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder()).addLast(new ProtobufDecoder(
            SubscribeResp.getDefaultInstance())).addLast(new ProtobufVarint32LengthFieldPrepender())
            .addLast(new ProtobufEncoder())
            .addLast(new SubReqClientHandler());
      }
    });
    ChannelFuture s = null;
    try {
      s = b.connect(host, port).sync();
      s.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
      client.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    new SubReqClient().connect("localhost", 8080);
  }
}

@Slf4j
class SubReqClientHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    for (int i = 0; i < 10; i++) {
      ctx.write(subReq(i));
    }
    ctx.flush();
  }

  private SubscribeReq subReq(int i) {
    Builder builder = SubscribeReq.newBuilder();
    builder.setSubReqId(i);
    builder.setUserName("alertcode");
    builder.setProductName("netty book");
    builder.setAddress("liaoning dalian");
    return builder.build();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    log.info("Receive server response:[{}]", msg);
  }
}
