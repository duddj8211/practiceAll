package com.mg.otp.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class HomeDao extends baseDao{
	
	public HashMap<String, Object> selectUser(Map<String, Object> param) {
		return selectOne("home.selectUser", param);
	}
	public HashMap<String, Object> checkOTP(Map<String, String> param){
		return selectOne("home.checkOTP", param);
	}
	public int updateOTP(Map<String, String> param) {
		return update("home.updateOTP", param);
	}
}
