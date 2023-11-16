/**
 * 
 */
package com.hjt.netty.codec.serialization;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * SerializationClient Handler.
 * 
 *
 *
 */
public class SerializationClientHandler extends
		SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object obj)
			throws Exception {
		if (obj instanceof SerializationBean) {
			SerializationBean user = (SerializationBean) obj;
			System.out.println("Server -> Client: " + user);
		}

	}

}
