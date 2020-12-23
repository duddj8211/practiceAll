package com.mg.otp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mg.otp.dao.HomeDao;

@Service
@Repository
public class HomeService {
	@Autowired
	private HomeDao homeDao;
	
	public HashMap<String, Object> selectUser(Map<String, Object> param) {
		HashMap<String, Object> searchMap = new HashMap<String, Object>();
		try {
			searchMap = homeDao.selectUser(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchMap;
	}
	
	public HashMap<String, Object> checkOTP(Map<String, String> param) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = homeDao.checkOTP(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
