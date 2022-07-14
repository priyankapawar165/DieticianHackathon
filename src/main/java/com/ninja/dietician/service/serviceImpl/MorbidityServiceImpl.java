package com.ninja.dietician.service.serviceImpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.ninja.dietician.entity.Morbidity;
import com.ninja.dietician.repositories.MorbidityRepository;
import com.ninja.dietician.service.MorbidityService;

@Service
public class MorbidityServiceImpl implements MorbidityService {

	@Autowired
	MorbidityRepository morbiditySaveRepository;

	@Override
	public List<Morbidity> getMorbidities() {
		return morbiditySaveRepository.getMorbidity();
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
	
		this.morbiditySaveRepository.deleteMorbidities(morbidityName,morbidityTestId);
		return "A record is deleted successfully";
	}

	@Override
	public Morbidity updateMorbidities(String morbidityName, String morbidityTestId, Morbidity morbidity) {
		return this.morbiditySaveRepository.updateMorbidities(morbidityName, morbidityTestId, morbidity);
	}

	@Override
	public List<Morbidity> getMorbiditybyName(String morbidityName) {
		// TODO Auto-generated method stub
		return this.morbiditySaveRepository.getMorbiditybyName(morbidityName);
	}

	@Override
	public List<Morbidity>  getMorbiditybyMorbidityTestId(String morbidityTestId) {
		// TODO Auto-generated method stub
		return this.morbiditySaveRepository.getMorbiditybyMorbidityTestId(morbidityTestId);
	}

}
