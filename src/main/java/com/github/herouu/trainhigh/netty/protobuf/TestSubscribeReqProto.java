package com.github.herouu.trainhigh.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author alertcode
 * @date 2018-10-12
 * @copyright alertcode.top
 */
public class TestSubscribeReqProto {

  public static void main(String[] args) throws InvalidProtocolBufferException {
    SubscribeReqProto.SubscribeReq subscribeReq = createSubscribeReq();
    System.out.println(" before encode:" + subscribeReq.toString());
    SubscribeReqProto.SubscribeReq decode = decode(encode(subscribeReq));
    System.out.println(" after decode:" + decode.toString());
    System.out.println("Assert:" + decode.equals(subscribeReq));
  }

  private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
    return req.toByteArray();
  }


  private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
    return SubscribeReqProto.SubscribeReq.parseFrom(body);
  }


  private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
    SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
    builder.setSubReqId(1);
    builder.setUserName("alertcode");
    builder.setProductName("book for Netty");
    builder.setAddress("dalian");
    return builder.build();
  }

}
