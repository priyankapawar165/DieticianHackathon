package com.ninja.dietician.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ninja.dietician.entity.Morbidity;
import com.ninja.dietician.repositories.MorbidityRepository;
import com.ninja.dietician.service.MorbidityService;

@Service
public class MorbidityServiceImpl implements MorbidityService {

	@Autowired
	MorbidityRepository morbiditySaveRepository;

	@Override
	public List<Morbidity> getMorbidities() {
		return morbiditySaveRepository.findAll();
	}

	@Override
	public Morbidity addMorbidity(Morbidity morbidity) {
		morbidity.setPk("MR" + generateRandomNumber());
		morbidity.setSk("MR" + generateRandomNumber());
		this.morbiditySaveRepository.addMorbidity(morbidity);
		return morbidity;
	}

	private String generateRandomNumber() {
		Random random = new Random();
		return Integer.toString(random.nextInt());

	}

	@Override
	public String deleteMorbidities(String morbidityName, String morbidityTestId) {

		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(morbidityName));
		eav1.put(":v2", new AttributeValue().withS(morbidityTestId));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("MorbidityName = :v1 and MorbidityTestId = :v2")
				.withExpressionAttributeValues(eav1);

		this.morbiditySaveRepository.deleteMorbidities(scanExpression);
		return "A record is deleted successfully";
	}

	@Override
	public Morbidity updateMorbidities(String morbidityName, String morbidityTestId, Morbidity morbidity) {

		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(morbidityName));
		eav1.put(":v2", new AttributeValue().withS(morbidityTestId));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("MorbidityName = :v1 and MorbidityTestId = :v2")
				.withExpressionAttributeValues(eav1);

		List<Morbidity> morbidityList = this.morbiditySaveRepository.getMorbidityList(scanExpression);

		morbidityList.forEach(mor -> {
			mor.setMorbidityTestName(morbidity.getMorbidityTestName());
			mor.setMorbidityTestUnit(morbidity.getMorbidityTestUnit());
			mor.setMorbidityTestMarkerRef(morbidity.getMorbidityTestMarkerRef());

			DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
					.withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
					.withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE).build();

			this.morbiditySaveRepository.updateMorbidities(dynamoDBMapperConfig, mor);

		});

		return morbidityList.get(0);
	}

	@Override
	public List<Morbidity> getMorbiditybyName(String morbidityName) {

		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(morbidityName));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression("MorbidityName = :v1 ").withExpressionAttributeValues(eav1);

		return this.morbiditySaveRepository.getMorbiditybyName(scanExpression);
	}

	@Override
	public List<Morbidity> getMorbiditybyMorbidityTestId(String morbidityTestId) {

		HashMap<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav1.put(":v1", new AttributeValue().withS(morbidityTestId));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				// Filter Expression
				.withFilterExpression(" MorbidityTestId = :v1").withExpressionAttributeValues(eav1);
		return this.morbiditySaveRepository.getMorbiditybyMorbidityTestId(scanExpression);
	}

}
