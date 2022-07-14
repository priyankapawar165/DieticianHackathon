package com.ninja.dietician.entity;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "DietProject_Table")
public class User implements Serializable{
	
	private static final Long serialVersionUID = 1L;
	//private int i=18;

	@JsonIgnore
	@DynamoDBAttribute(attributeName="UserId")
	private String userId;
	
	@JsonIgnore
	@DynamoDBHashKey(attributeName = "PK")
	@DynamoDBAttribute(attributeName="PK")
	private String pk;
	
	@JsonIgnore
	@DynamoDBRangeKey(attributeName="SK")
	private String sk;
	
	@DynamoDBAttribute(attributeName="InfoType")
	private String infoType;
	
	
	@DynamoDBAttribute(attributeName="UserType")
	private String userType;
	
	@NonNull
	@DynamoDBAttribute(attributeName="FirstName")
	private String firstName;
	
	@NonNull
	@DynamoDBAttribute(attributeName="LastName")
	private String lastName;
	
	@DynamoDBAttribute(attributeName="Address")
	//@DynamoDBTypeConverted(converter = AddressConverter.class)
	//@DynamoDBTypeConvertedJson
	private Address address;
	
	@DynamoDBAttribute(attributeName="Contact")
	private String contact;
	
	@DynamoDBAttribute(attributeName="Email")
	private String email; 
	@DynamoDBAttribute(attributeName="Allergy")
	private String allergy;
	@DynamoDBAttribute(attributeName="FoodCategory")
	private String foodCategory;
	
	@DynamoDBAttribute(attributeName="DieticianId")
	private String dieticianId;
	
	@DynamoDBAttribute(attributeName="LoginUserName")
	private String loginUserName;
	
	@DynamoDBAttribute(attributeName="Password")
	private String password;
	
	
	@JsonIgnore
	@DynamoDBAttribute(attributeName="Createdon")
	private String createdon;
	
	@JsonIgnore
	@DynamoDBAttribute(attributeName="Modifiedon")
	private String modifiedon;
	
	/*
	public void setPk() {
		this.pk= "LOGIN#"+dieticianId;
	}
	public String getPk() {
		return pk;
	}
	
	public void setSk() {
		this.sk= "USER#"+this.userId;
	}

	public String getSk() {
		return sk;
		
	}
	public void setUserId() {
		this.userId="PT0018";
	}
	
	
	public Address getAddress() {
		return address;
	}
	
	
	public void setAddress(Address address) {
		this.address = address;
	}*/
	
}
