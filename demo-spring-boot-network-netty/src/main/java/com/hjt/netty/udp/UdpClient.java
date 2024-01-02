package com.hjt.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * udp客户端
 *
 * @author LionLi
 */
public class UdpClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        protected void initChannel(NioDatagramChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new UdpClientHandler());
                        }
                    });
            Channel channel = bootstrap.bind(9000).sync().channel();
            InetSocketAddress address = new InetSocketAddress("localhost", 8088);

            ByteBuf byteBuf = Unpooled.copiedBuffer("你好服务器".getBytes(StandardCharsets.UTF_8));
            channel.writeAndFlush(new DatagramPacket(byteBuf, address)).sync();
            channel.closeFuture().await();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}