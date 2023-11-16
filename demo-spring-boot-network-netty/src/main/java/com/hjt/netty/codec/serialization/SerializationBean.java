package com.hjt.netty.codec.serialization;

import java.io.Serializable;

/**
 * POJO: a Serialization Bean
 * 
 *
 *
 */
public class SerializationBean implements Serializable {

	private static final long serialVersionUID = 3235432002462705915L;
	private int age;
	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SerializationBean [age=" + age + ", name=" + name + "]";
	}

}
