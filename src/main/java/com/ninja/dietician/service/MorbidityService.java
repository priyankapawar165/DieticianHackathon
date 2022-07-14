package com.ninja.dietician.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.ninja.dietician.entity.Morbidity;


public interface MorbidityService {

	public Morbidity addMorbidity(Morbidity morbidity);

	public PaginatedScanList<Object> getMorbidities();

	public String deleteMorbidities(String pk, String sk);

	public Morbidity updateMorbidities(String morbidityName, String morbidityTestId, Morbidity morbidity);

	public Object getMorbiditybyName(String morbidityName);

	public Object getMorbiditybyMorbidityTestId(String morbidityTestId);



}
