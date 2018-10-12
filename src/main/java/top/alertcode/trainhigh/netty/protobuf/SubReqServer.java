package top.alertcode.trainhigh.netty.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import top.alertcode.trainhigh.netty.protobuf.SubscribeReqProto.SubscribeReq;
import top.alertcode.trainhigh.netty.protobuf.SubscribeRespProto.SubscribeResp;
import top.alertcode.trainhigh.netty.protobuf.SubscribeRespProto.SubscribeResp.Builder;

/**
 * @author alertcode
 * @date 2018-10-12
 * @copyright alertcode.top
 */
public class SubReqServer {

  void bind(int port) {
    NioEventLoopGroup baseGroup = new NioEventLoopGroup();
    NioEventLoopGroup workGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(baseGroup, workGroup).channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG, 100)
          .handler(new LoggingHandler()).childHandler(

          new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
//              使用protobuf
              ch.pipeline().addLast(new ProtobufVarint32FrameDecoder()).addLast(new ProtobufDecoder(
                  SubscribeReq.getDefaultInstance())).addLast(new ProtobufVarint32LengthFieldPrepender())
                  .addLast(new ProtobufEncoder())
                  .addLast(new SubReqServerHandler());
            }
          });

      ChannelFuture sync = b.bind(port).sync();
      sync.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      workGroup.shutdownGracefully();
      baseGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    new SubReqServer().bind(8080);
  }
}

@Slf4j
class SubReqServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    SubscribeReq req = (SubscribeReq) msg;
    if ("alertcode".equalsIgnoreCase(req.getUserName())) {
      log.info("service accept client subscribe req:{}", req.toString());
      ctx.writeAndFlush(resp(req.getSubReqId()));
    }
    super.channelRead(ctx, msg);
  }


  private SubscribeResp resp(int subReqId) {
    Builder builder = SubscribeResp.newBuilder();
    builder.setSubReqId(subReqId);
    builder.setRespCode(1);
    builder.setDesc("success");
    return builder.build();
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }
}
