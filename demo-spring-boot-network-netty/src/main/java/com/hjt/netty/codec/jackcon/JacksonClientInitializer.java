package com.hjt.netty.codec.jackcon;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * JacksonClient ChannelInitializer.
 * 
 *
 *
 */
public class JacksonClientInitializer extends
		ChannelInitializer<Channel> {
 
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//设置管道解密器
		pipeline.addLast(new JacksonDecoder<JacksonBean>(JacksonBean.class));
		//设置管道编码器
		pipeline.addLast(new JacksonEncoder());
		//设置管道客户端处理器
		pipeline.addLast(new JacksonClientHandler());
	}
}