package com.Hao.codeSharingProject;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CodeItem {
	
	private String code;
	private transient UUID uuid = UUID.randomUUID();
	
	@Override
	public String toString() {
		return "CodeItem [code=" + code + ", uuid=" + uuid + "]";
	}

	public String getCode() {
		return code;
	}
	
	public UUID getUUID() {
		return uuid;
	}
}
