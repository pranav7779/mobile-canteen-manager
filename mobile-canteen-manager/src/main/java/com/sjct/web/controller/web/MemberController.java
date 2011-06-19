package com.sjct.web.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjct.core.service.MemberService;
import com.sjct.web.controller.AbstractWebController;

@Controller
public class MemberController extends AbstractWebController{
	
	@Autowired
	private MemberService memberService;
	/**
	 * 前台主页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/test/index")
	public String index(ModelMap map, HttpServletRequest req) {
		map.put("msg", "Return Success");
		map.put("list", memberService.getMemberById(1L));
		return path("index");
	}
}
