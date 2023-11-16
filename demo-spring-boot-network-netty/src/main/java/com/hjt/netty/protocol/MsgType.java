/**
 * 
 */
package com.hjt.netty.protocol;

/**
 * 说明：消息类型
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
