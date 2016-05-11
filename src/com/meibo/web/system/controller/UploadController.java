package com.meibo.web.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.controller.BaseController;
import com.meibo.web.common.service.SessionService;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.UploadUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;

@RequestMapping ( "/upload" )
@Controller
public class UploadController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( UploadController.class );
	
	@Autowired
	private SessionService sessionService;
	

}
