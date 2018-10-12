package top.alertcode.trainhigh.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import top.alertcode.trainhigh.netty.protobuf.SubscribeReqProto.SubscribeReq;

/**
 * @author alertcode
 * @date 2018-10-12
 * @copyright alertcode.top
 */
public class TestSubscribeReqProto {

  public static void main(String[] args) throws InvalidProtocolBufferException {
    SubscribeReq subscribeReq = createSubscribeReq();
    System.out.println(" before encode:" + subscribeReq.toString());
    SubscribeReq decode = decode(encode(subscribeReq));
    System.out.println(" after decode:" + decode.toString());
    System.out.println("Assert:" + decode.equals(subscribeReq));
  }

  private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
    return req.toByteArray();
  }


  private static SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
    return SubscribeReqProto.SubscribeReq.parseFrom(body);
  }


  private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
    SubscribeReq.Builder builder = SubscribeReq.newBuilder();
    builder.setSubReqId(1);
    builder.setUserName("alertcode");
    builder.setProductName("book for Netty");
    builder.setAddress("dalian");
    return builder.build();
  }

}
