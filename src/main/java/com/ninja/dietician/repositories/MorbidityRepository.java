package com.ninja.dietician.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.ninja.dietician.entity.Morbidity;

@Repository
public class MorbidityRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public List<Morbidity> findAll(DynamoDBScanExpression scanExpression) {
		return mapper.scan(Morbidity.class, scanExpression);
	}

	public void addMorbidity(Morbidity user) {
		mapper.save(user);
	}

	public void deleteMorbidities(DynamoDBScanExpression scanExpression) {
		mapper.delete(mapper.scan(Morbidity.class, scanExpression).get(0));

	}

	public Morbidity updateMorbidities(DynamoDBMapperConfig dynamoDBMapperConfig, Morbidity morbidity) {
		return morbidity;

	}

	public List<Morbidity> getMorbidityList(DynamoDBScanExpression scanExpression) {
		return mapper.scan(Morbidity.class, scanExpression);
	}

	public List<Morbidity> getMorbiditybyName(DynamoDBScanExpression scanExpression) {
		return mapper.scan(Morbidity.class, scanExpression);

	}

	public List<Morbidity> getMorbiditybyMorbidityTestId(DynamoDBScanExpression scanExpression) {
		return mapper.scan(Morbidity.class, scanExpression);

	}

}
