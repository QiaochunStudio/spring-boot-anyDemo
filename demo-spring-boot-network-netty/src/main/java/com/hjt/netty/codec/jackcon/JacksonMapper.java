package com.hjt.netty.codec.jackcon;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ObjectMapper instance.
 * 
 *
 *
 */
public class JacksonMapper {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static ObjectMapper getInstance() {
		return MAPPER;
	}

}
