package com.hjt.netty.codec;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * My ClientHandler.
 * 
 *
 *
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object obj) throws Exception {
		Channel incoming = ctx.channel();

		if (obj instanceof Msg) {
			Msg msg = (Msg) obj;
			System.out.println("Server->Client:" + incoming.remoteAddress() + msg.getBody());
		} else {
			System.out.println("Server->Client:" + incoming.remoteAddress() + obj.toString());
		}
	}

}
