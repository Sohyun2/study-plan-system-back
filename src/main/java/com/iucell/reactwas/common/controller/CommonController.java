package com.iucell.reactwas.common.controller;

import java.net.InetAddress;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iucell.reactwas.common.service.CommonService;
import com.iucell.reactwas.service.ActionService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class CommonController {

	private static final String ENTRY_POINT = "/";
	private static final String MAIN_URL = "/{url}";
	private static final String CUSTOMIZE_URL = "/{url}/{actionName}";
	
	@Autowired
	CommonService commonService;
	@Autowired
	ActionService actionService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);	

	@PostMapping(value = MAIN_URL)
	public JSONObject postMainUrl(HttpServletRequest request, @PathVariable("url") String url) {	
		return commonService.commonService(request, url);
	}

	@PostMapping(value = CUSTOMIZE_URL)
	public JSONObject cutsomizeUrl(HttpServletRequest request, @PathVariable("url") String url, @PathVariable("actionName") String actionName) {
		return actionService.mainService(request, actionName);
	}
		
	@GetMapping(value = ENTRY_POINT)
	public String home(Locale locale, Model model) {
		try {
			InetAddress local;
			local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();
			logger.info("===== " + ip + " server start =====");

			// db connection ¿¬°á
			commonService.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}
	
}
