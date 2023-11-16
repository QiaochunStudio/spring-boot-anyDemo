/**
 * Welcome to https://waylau.com
 */
package com.hjt.netty.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * My LineBasedFrameDecoder ServerHandler。
 * 
 *
 *
 */
public class MyLineBasedFrameDecoderServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// 接收msg消息，此处已经无需解码了
		System.out.println("Client -> Server: " + msg);
	}
}
