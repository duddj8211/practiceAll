package com.mg.otp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.codec.binary.Base32;

public class GoogleOTP {
	// generate를 통해 키값, QR코드 주소를 생성한다.
	// 생성된 키값은 데이터베이스에 저장하고 후에 otp 검증을 한다.
	// otp 검증은 checkCode를 통해서 이루어진다.
	// 생성한 QR코드를 통해서 구글OTP앱에 등록하고 표시되는 번호와 키값을 매개변수로 넣어서 검증한다.
	
	public static void main(String[] args) {
		GoogleOTP otp = new GoogleOTP();
		String name = "";
		String host = "";
		try {
			HashMap<String, String> map = otp.generate(name, host);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, String> generate(String userName, String hostName) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			byte[] buffer = new byte[5+5*5];
			new Random().nextBytes(buffer);
			Base32 codec = new Base32();
			// 배열복사 Arrays.copyOf(원본배열, 복사길이)
			byte[] secretKey = Arrays.copyOf(buffer, 10);
			byte[] bEncodedKey = codec.encode(secretKey);
			
			String encodedKey = new String(bEncodedKey);
			String url = getQRBarcodeURL(userName, hostName, encodedKey);
			
			// Googlr OTP 앱에 userName@hostName으로 저장됨
			// key를 입력하거나 생성된 QR코드를 바코드 스캔하여 등록
			map.put("encodedKey", encodedKey);
			map.put("url", url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	// QR코드 생성
	public static String getQRBarcodeURL(String user, String host, String secret) {
		// QR코드 주소 생성
		/*
		 * cht=qr : QR코드 지정
		 * cht=qr&chs=200x200 : <width>x<height> 이미지 크기 지정
		 * chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s
		 * : 아마 인코딩할 데이터인듯? (chl=<data>)
		 * chld=H|0 : <level>|<margin> 
		 * : QR코드 누락, 잘못 읽음 등 오류 수정 지원 (H-최대 30% 데이터 손실 복구 가능)
		 * : 코드 주변 흰색 테두리 너비
		 */
		// %s 자리에 user, host, secret 값이 들어감
		String format2 = "http://chart.apis.google.com/chart?cht=qr&chs=200x200&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";
		return String.format(format2, user, host, secret);
	}
	
	

}
