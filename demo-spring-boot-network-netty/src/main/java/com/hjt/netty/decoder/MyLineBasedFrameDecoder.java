/**
 * Welcome to https://waylau.com
 */
package com.hjt.netty.decoder;

import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * My LineBasedFrameDecoder.
 * 
 *
 *
 */
public class MyLineBasedFrameDecoder extends LineBasedFrameDecoder {

	private final static int MAX_LENGTH = 1024; // 帧的最大长度

	public MyLineBasedFrameDecoder() {
		super(MAX_LENGTH);
	}

}
