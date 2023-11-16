package com.hjt.netty.codec;

/**
 * Message.
 * 
 *
 *
 */
public class Msg {

	private MsgHeader msgHeader = new MsgHeader();
	private String body;

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
