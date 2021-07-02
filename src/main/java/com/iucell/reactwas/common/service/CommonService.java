package com.iucell.reactwas.common.service;

import javax.servlet.http.HttpServletRequest;

//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iucell.reactwas.common.dao.DBHelper;
import com.iucell.reactwas.util.Response;

@Service
public class CommonService {

	@Autowired
	DBHelper helper;
	
	public void getConnection() {
		helper.getConnection();
	}
	
	public JSONObject commonService(HttpServletRequest request, String actionName) {
		JSONObject response = null;
		
		if(actionName.equals("login")) {
			response = this.login(request); 
		} else if(actionName.equals("menu")) {
			response = this.menu(request); 
		}
		
		return response;
	}

	public JSONObject login(HttpServletRequest request) {	
		Object[] params = new Object[2];
		params[0] = request.getParameter("ID_USER").toString();
		params[1] = request.getParameter("PW_USER").toString();
		
		Response response = helper.getSqlData("UP_SH_USER_S", params);
		
		return response.getResponse();		
	}	

	public JSONObject menu(HttpServletRequest request) {	
		Object[] params = new Object[1];
		params[0] = request.getParameter("ID_USER").toString();
		
		Response response = helper.getSqlDataList("UP_SH_MENU_S", params);
		
		return response.getResponse();		
	}	
}
