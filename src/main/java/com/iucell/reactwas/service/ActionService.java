package com.iucell.reactwas.service;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iucell.reactwas.common.dao.DBHelper;
import com.iucell.reactwas.util.Response;
import com.iucell.reactwas.util.Util;

@Service
public class ActionService {

	private static final Logger logger = LoggerFactory.getLogger(DBHelper.class);
	
	@Autowired
	DBHelper helper;
	@Autowired
	Util util;
	
	public JSONObject mainService(HttpServletRequest request, String actionName) {
		Response response = null;
		
		if(actionName.equals("save")) {			
			response = this.save(request);
		} else if(actionName.equals("search")) {
			response = this.search(request);
		} else if(actionName.equals("delete")) {
			response = this.delete(request);
		} else if(actionName.equals("complete")) {
			response = this.complete(request);
		}
		
		return response.getResponse();
	}

	public Response search(HttpServletRequest request) {	
		Object[] params = new Object[2];
		params[0] = request.getParameter("no_emp").toString();
		params[1] = request.getParameter("dt_plan").toString();

		logger.info("search");
		return helper.getSqlDataList("UP_SH_PLAN_S", params);		
	}
	
	public Response save(HttpServletRequest request) {	
		Response response = null;	

		helper.setOffAutoCommit();
		
		Object[] param = new Object[6];
		param[0] = request.getParameter("no_emp").toString();
		param[1] = request.getParameter("dt_plan").toString();
		
		util.setStringArray(request.getParameter("planList").toString());
		
		for(int i=0; i<util.getSize(); i++) {
			param[2] = util.getValue(i, "year");
			param[3] = util.getValue(i, "semester");
			param[4] = util.getValue(i, "subject");
			param[5] = util.getValue(i, "content");

			response = helper.save("UP_SH_PLAN_I", param);
			if(!response.getStatus()) {
				return response;
			}
		}
		
		helper.commit();
		logger.info("save success");
		
		return response;
	}

	public Response delete(HttpServletRequest request) {	
		Response response = null;	

		helper.setOffAutoCommit();
		util.setStringArray(request.getParameter("deletedList").toString());
		
		for(int i=0; i<util.getSize(); i++) {
			Object[] param = new Object[1];
			param[0] = util.getValue(i, "key");
			
			response = helper.save("UP_SH_PLAN_D", param);
			if(!response.getStatus()) {
				return response;
			}
		}
		
		helper.commit();
		logger.info("delete success");
		
		return response;
	}

	public Response complete(HttpServletRequest request) {	
		Response response = null;	

		helper.setOffAutoCommit();
		util.setStringArray(request.getParameter("list").toString());

		
		for(int i=0; i<util.getSize(); i++) {
			Object[] param = new Object[2];
			param[0] = util.getValue(i, "key");
			param[1] = request.getParameter("yn_complete").toString();
			
			response = helper.save("UP_SH_PLAN_U1", param);
			if(!response.getStatus()) {
				return response;
			}
		}
		
		helper.commit();
		logger.info("complete update success");
		
		return response;
	}
}
