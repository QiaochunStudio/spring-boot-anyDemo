package com.hjt.netty.codec.serialization;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * SerializationServer Handler.
 * 
 *
 *
 */
public class SerializationServerHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object obj) throws Exception {
		if (obj instanceof SerializationBean) {
			SerializationBean user = (SerializationBean) obj;
			ctx.writeAndFlush(user);
			System.out.println("Client -> Server: " + user);
		}
	}

}
