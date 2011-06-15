package com.sjct.web.controller;

import org.apache.commons.lang.ArrayUtils;


abstract public class AbstractWebController extends AbstractController {

	private final static String WEB_PATH = "web/";
	
	@Override
	protected String path(String... paths) {
		if (paths == null || paths.length < 1) {
			return ERROR;
		}
		paths = (String[]) ArrayUtils.add(paths, 0, WEB_PATH);
		return super.path(paths);
	}
}
