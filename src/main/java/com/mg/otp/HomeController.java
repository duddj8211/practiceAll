package com.mg.otp;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mg.otp.service.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private HomeService homeService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "index";
	}

	/*
	 * OTP 관련 메소드 시작
	 */
	@ResponseBody
	@RequestMapping("confirmUser")
	public int confirmUser(HttpServletResponse response, @RequestBody Map<String, Object> param) {
		int result = 0;
		try {
			if(homeService.selectUser(param) != null) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("updateOTP")
	public int insertOTP(@RequestBody Map<String, String> param) {
		int result = 0;
		try {
			result = homeService.updateOTP(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("checkOTP")
	public HashMap<String, String> checkOTP(@RequestBody Map<String, String> param) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		HashMap<String, Object> checkMap = new HashMap<String, Object>();
		GoogleOTP otp = new GoogleOTP();
		try {
			String name = "mgKim_admin";
			String host = param.get("id");
			String otpkey = "";
			String url = "";
			
			checkMap = homeService.checkOTP(param);
			if (checkMap != null) {
				
				resultMap.put("OTP_YN", "Y");
			} else {
				resultMap = otp.generate(name, host);
				resultMap.put("OTP_YN", "N");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

}
