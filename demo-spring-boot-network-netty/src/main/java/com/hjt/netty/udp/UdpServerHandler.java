package com.hjt.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.StandardCharsets;

/**
 * 服务端业务处理
 *
 * @author LionLi
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        System.out.println("服务端接收到消息：" + packet.content().toString(StandardCharsets.UTF_8));
        // 向客户端发送消息
        ByteBuf byteBuf = Unpooled.copiedBuffer("已经接收到消息!".getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(new DatagramPacket(byteBuf, packet.sender()));
    }
}