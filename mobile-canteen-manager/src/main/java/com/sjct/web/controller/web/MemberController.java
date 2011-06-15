package com.sjct.web.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjct.web.controller.AbstractWebController;

@Controller
public class MemberController extends AbstractWebController{
	/**
	 * 前台主页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/test/index.htm")
	public String index(ModelMap map, HttpServletRequest req) {
		map.put("msg", "Return Success");
		return path("index");
	}
}
