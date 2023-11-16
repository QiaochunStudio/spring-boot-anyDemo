package com.hjt.netty.encoder;

/**
 * Message Header.
 * 
 *
 * 
 */
public class MsgHeader {
	private byte msgType; // 消息类型
	private int len; // 长度

	public MsgHeader() {
	}

	public MsgHeader(byte msgType, int len) {
		this.msgType = msgType;
		this.len = len;
	}

	public byte getMsgType() {
		return msgType;
	}

	public void setMsgType(byte msgType) {
		this.msgType = msgType;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

}
