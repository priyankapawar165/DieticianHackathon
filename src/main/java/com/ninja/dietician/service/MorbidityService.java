package com.ninja.dietician.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.ninja.dietician.entity.Morbidity;


public interface MorbidityService {

	public Morbidity addMorbidity(Morbidity morbidity);

	public List<Morbidity>  getMorbidities();

	public String deleteMorbidities(String pk, String sk);

	public Morbidity updateMorbidities(String morbidityName, String morbidityTestId, Morbidity morbidity);

	public Object getMorbiditybyName(String morbidityName);

	public Object getMorbiditybyMorbidityTestId(String morbidityTestId);



}
