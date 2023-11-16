package com.hjt.netty.codec.jackcon;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Jackson Client.
 * 
 *
 *
 */
public class JacksonClient {

    public static void main(String[] args) throws Exception{
//        new JacksonClient("localhost", 8082).run();
        new JacksonClient("192.168.0.56", 8083).run();
    }

    private final String host;
    private final int port;

    public JacksonClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
		            .handler(new JacksonClientInitializer());
            Channel channel = bootstrap.connect(host, port).sync().channel();

            // 发送对象
			JacksonBean user = new JacksonBean();
			user.setAge(27);
			user.setName("waylau");
			List<String> sons = new ArrayList<String>();
			for (int i = 0;i <10; i++) {
				sons.add("Lucy"+i);
				sons.add("Lily"+i);
			}

			user.setSons(sons);
			Map<String, String> addrs = new HashMap<String, String>();
			for (int i = 0;i <10; i++) {
				addrs.put("001"+i, "18998366112");
				addrs.put("002"+i, "15014965012");
			}

			user.setAddrs(addrs);
			channel.write(user);
			channel.flush();
 
			// 等待连接关闭
			channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("远程主机关闭");
            group.shutdownGracefully();
        }

    }

}
