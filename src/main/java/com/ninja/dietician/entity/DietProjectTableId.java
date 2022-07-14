package com.ninja.dietician.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DietProjectTableId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@DynamoDBHashKey
	private String pk;
	
	@DynamoDBRangeKey
	private String sk;
	
	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}
	
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}
	
}