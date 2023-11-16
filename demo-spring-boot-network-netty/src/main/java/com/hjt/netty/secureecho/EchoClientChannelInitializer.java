/**
 * Welcome to https://waylau.com
 */
package com.hjt.netty.secureecho;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * Echo Client ChannelInitializer.
 * 
 *
 *
 */
public class EchoClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {

		// 先添加SslHandler
		String pkPath = System.getProperties().getProperty("user.dir") 
				+ "/src/main/resources/ssl/nettyClient.jks";
		String password = "defaultPass";
		SSLEngine engine = SslContextFactory.getServerContext(pkPath, pkPath, password).createSSLEngine();
		engine.setUseClientMode(true); // 设置为服务器模式
		engine.setNeedClientAuth(true); // 需要客户端认证
		ch.pipeline().addLast(new SslHandler(engine));

		// 再添加其他ChannelHandler
		ch.pipeline().addLast(new EchoClientHandler());
	}

}
