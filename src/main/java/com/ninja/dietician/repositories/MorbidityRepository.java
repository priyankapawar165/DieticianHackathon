package com.ninja.dietician.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ninja.dietician.entity.Morbidity;

@Repository
public class MorbidityRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public void addMorbidity(Morbidity user) {
		mapper.save(user);
	}

	@SuppressWarnings("unchecked")
	public List getMorbidity() {
		List<Morbidity> list = new ArrayList();
		list = mapper.scan(Morbidity.class, new DynamoDBScanExpression());
		return list;
		//return (PaginatedScanList<T>) mapper.scan(Morbidity.class, new DynamoDBScanExpression());
	}

	public void deleteMorbidities(String morbidityName, String morbidityTestId) {
		

		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(morbidityName));
		eav1.put(":v2", new AttributeValue().withS(morbidityTestId));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("MorbidityName = :v1 and MorbidityTestId = :v2")
				.withExpressionAttributeValues(eav1);

		List<Morbidity> morbidityList = mapper.scan(Morbidity.class, scanExpression);
		mapper.delete(morbidityList.get(0));

	}

	public Morbidity updateMorbidities(String morbidityName, String morbidityTestId, Morbidity morbidity) {

		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(morbidityName));
		eav1.put(":v2", new AttributeValue().withS(morbidityTestId));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("MorbidityName = :v1 and MorbidityTestId = :v2")
				.withExpressionAttributeValues(eav1);

		List<Morbidity> morbidityList = mapper.scan(Morbidity.class, scanExpression);

		morbidityList.forEach(mor -> {
			mor.setMorbidityTestName(morbidity.getMorbidityTestName());
			mor.setMorbidityTestUnit(morbidity.getMorbidityTestUnit());
			mor.setMorbidityTestMarkerRef(morbidity.getMorbidityTestMarkerRef());

			DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
					.withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
					.withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE).build();
			mapper.save(mor, dynamoDBMapperConfig);

		});

		return morbidityList.get(0);

	}

	public List<Morbidity> getMorbiditybyName(String morbidityName) {
		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(morbidityName));
		

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("MorbidityName = :v1 ")
				.withExpressionAttributeValues(eav1);

		List<Morbidity> morbidityList = mapper.scan(Morbidity.class, scanExpression);
		
		return morbidityList;
	}

	public List<Morbidity> getMorbiditybyMorbidityTestId(String morbidityTestId) {
		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(morbidityTestId));
		

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression(" MorbidityTestId = :v1")
				.withExpressionAttributeValues(eav1);

		List<Morbidity> morbidityList = mapper.scan(Morbidity.class, scanExpression);
		
		return morbidityList;
	}

}
