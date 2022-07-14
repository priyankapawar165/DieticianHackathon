package com.ninja.dietician.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@DynamoDBTable(tableName = "DietProject_Table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Morbidity {

	@JsonIgnore
	@DynamoDBHashKey (attributeName="PK")
	@DynamoDBAutoGeneratedKey
	private String pk;

	@JsonIgnore
	@DynamoDBRangeKey(attributeName="SK")
	private String sk;
	
	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}

	@JsonIgnore
	private String infoType;

	private String morbidityName;

	private String morbidityTestId;

	private String morbidityTestName;

	private String morbidityTestUnit;

	private String morbidityTestMarkerRef;

	@JsonIgnore
	private String createdOn;

	@JsonIgnore
	private String modifiedOn;
	
	

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	@DynamoDBAttribute(attributeName = "InfoType")
	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	@DynamoDBAttribute(attributeName = "MorbidityName")
	public String getMorbidityName() {
		return morbidityName;
	}

	public void setMorbidityName(String morbidityName) {
		this.morbidityName = morbidityName;
	}

	@DynamoDBAttribute(attributeName = "MorbidityTestId")
	public String getMorbidityTestId() {
		return morbidityTestId;
	}

	public void setMorbidityTestId(String morbidityTestId) {
		this.morbidityTestId = morbidityTestId;
	}

	@DynamoDBAttribute(attributeName = "MorbidityTestName")
	public String getMorbidityTestName() {
		return morbidityTestName;
	}

	public void setMorbidityTestName(String morbidityTestName) {
		this.morbidityTestName = morbidityTestName;
	}

	@DynamoDBAttribute(attributeName = "MorbidityTestUnit")
	public String getMorbidityTestUnit() {
		return morbidityTestUnit;
	}

	public void setMorbidityTestUnit(String morbidityTestUnit) {
		this.morbidityTestUnit = morbidityTestUnit;
	}

	@DynamoDBAttribute(attributeName = "MorbidityMarkerRef")
	public String getMorbidityTestMarkerRef() {
		return morbidityTestMarkerRef;
	}

	public void setMorbidityTestMarkerRef(String morbidityTestMarkerRef) {
		this.morbidityTestMarkerRef = morbidityTestMarkerRef;
	}

	@DynamoDBAttribute(attributeName = "Createdon")
	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@DynamoDBAttribute(attributeName = "Modifiedon")
	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}