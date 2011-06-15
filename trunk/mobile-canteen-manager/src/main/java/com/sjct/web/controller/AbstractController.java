package com.sjct.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

abstract public class AbstractController {
	/**
	 * 操作成功页面
	 */
	final protected static String SUCCESS = "success";
	/**
	 * 操作失败页面
	 */
	final protected static String FAILURE = "failure";
	/**
	 * 操作错误页面
	 */
	final protected static String ERROR = "error";
	/**
	 * 宏页面路径
	 */
	final protected static String MACROS = "macro/";
	/**
	 * 日志
	 */
	final protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 返回文件路径
	 * 
	 * @param path
	 * @return
	 */
	protected String path(String... paths) {
		StringBuffer buffer = new StringBuffer();
		for (String path : paths) {
			buffer.append(path);
		}
		return buffer.toString();
	}

	/**
	 * 返回宏页面文件路径
	 * 
	 * @param paths
	 * @return
	 */
	protected String pathMacro(String... paths) {
		if (paths == null || paths.length < 1) {
			return ERROR;
		}
		paths = (String[]) ArrayUtils.add(paths, 0, MACROS);
		return path(paths);
	}
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Integer.class, null,
				new CustomNumberEditor(Integer.class, null, true));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",request.getLocale());
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}
}