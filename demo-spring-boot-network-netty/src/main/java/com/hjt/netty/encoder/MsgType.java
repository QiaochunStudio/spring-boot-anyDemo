package com.hjt.netty.encoder;

/**
 * Message Type.
 * 
 *
 * 
 */
public enum MsgType {
	EMGW_LOGIN_REQ((byte) 0x00), 
	EMGW_LOGIN_RES((byte) 0x01);

	private byte value;

	public byte getValue() {
		return value;
	}

	private MsgType(byte value) {
		this.value = value;
	}
}
