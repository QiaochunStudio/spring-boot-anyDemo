package com.hjt.netty.codec.jackcon;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.InputStream;
import java.util.List;

/**
 * Jackson Decoder.
 * 
 *
 *
 */
public class JacksonDecoder<T> extends ByteToMessageDecoder {

    private final Class<T> clazz;

    public JacksonDecoder(Class<T> clazz) {
        this.clazz = clazz;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
        InputStream byteBufInputStream = new ByteBufInputStream(in);
        ObjectMapper mapper = JacksonMapper.getInstance();
        out.add(mapper.readValue(byteBufInputStream, clazz));
	}

}
