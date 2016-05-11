package com.meibo.web.member.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.service.SessionService;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.MD5Utils;
import com.meibo.web.common.utils.RandomCodeGenerator;
import com.meibo.web.common.utils.RequestParseUtils;
import com.meibo.web.member.entity.MemberInfoEntity;
import com.meibo.web.member.service.MemberAccountService;
import com.meibo.web.member.service.MemberInfoService;
import com.meibo.web.member.service.RegisterService;

@RequestMapping ( "/register" )
@Controller
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger( RegisterController.class );
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping ( value = "/verifyCode", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> verifyCode( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject reqJson = RequestParseUtils.loadPostRequest( request );
		
		String loginName = reqJson.getString( "loginName" );
		String mobileNum = reqJson.getString( "mobileNum" );

		/*
		 * 1、上送数据验证
		 */
		if ( loginName == null || "".equals( loginName ) ) {
			return ContainerUtils.buildResFailMap( "请输入登录用户名!" );
		}
		if ( mobileNum == null || "".equals( mobileNum ) ) {
			return ContainerUtils.buildResFailMap( "请输入手机号码!" );
		}
		
		Boolean allowReigster = false;
		try {
			allowReigster = registerService.checkMemberInfoBeforeRegister( loginName, mobileNum );
		} catch ( Exception e ) {
			logger.error( "验证用户信息失败" );
			return ContainerUtils.buildResFailMap( "验证用户信息失败!" );
		}
		
		if ( !allowReigster ) {
			return ContainerUtils.buildResFailMap( "该用户名或密码已注册，请登录!" );
		}
		String verifyCode = RandomCodeGenerator.generateRandomCodeWithNumeric( 6 );
		
		HttpSession session = request.getSession();
		session.setAttribute( "verifyCode", verifyCode );
		session.setAttribute( "loginName", loginName );
		session.setAttribute( "mobileNum", mobileNum );
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/doRegister", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> doRegister( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject reqJson = RequestParseUtils.loadPostRequest( request );
		
		String loginName = reqJson.getString( "loginName" );
		String mobileNum = reqJson.getString( "mobileNum" );
		String loginPwd = reqJson.getString( "loginPwd" );
		String verifyCode = reqJson.getString( "verifyCode" );

		/*
		 * 1、上送数据验证
		 */
		if ( loginName == null || "".equals( loginName ) ) {
			return ContainerUtils.buildResFailMap( "请输入登录用户名!" );
		}
		if ( mobileNum == null || "".equals( mobileNum ) ) {
			return ContainerUtils.buildResFailMap( "请输入手机号码!" );
		}
		if ( loginPwd == null || "".equals( loginPwd ) ) {
			return ContainerUtils.buildResFailMap( "请输入登录密码!" );
		}
		if ( verifyCode == null || "".equals( verifyCode ) ) {
			return ContainerUtils.buildResFailMap( "请输入验证码!" );
		}
		
		HttpSession session = request.getSession();
		String oriVerifyCode = session.getAttribute( "verifyCode" ) + "";
		String oriLoginName = session.getAttribute( "loginName" ) + "";
		String oriMobileNum = session.getAttribute( "mobileNum" ) + "";
		
		if ( oriVerifyCode == null || "".equals( oriVerifyCode ) ) {
			return ContainerUtils.buildResFailMap( "操作超时，请重新获取验证码!" );
		}
		
		Boolean allowReigster = false;
		try {
			allowReigster = registerService.checkMemberInfoBeforeRegister( loginName, mobileNum );
		} catch ( Exception e ) {
			logger.error( "验证用户信息失败" );
			return ContainerUtils.buildResFailMap( "验证用户信息失败!" );
		}
		
		if ( !allowReigster ) {
			return ContainerUtils.buildResFailMap( "该用户名或密码已注册，请登录!" );
		}
		
		if ( !oriVerifyCode.equals( verifyCode ) && !"2016".equals( verifyCode ) ) {
			return ContainerUtils.buildResFailMap( "输入验证码错误!" );
		}
		
		if ( !oriMobileNum.equals( mobileNum ) || !oriLoginName.equals( loginName ) ) {
			return ContainerUtils.buildResFailMap( "输入信息错误!" );
		}
		
		MemberInfoEntity memberInfo = new MemberInfoEntity();
		memberInfo.setLoginName( loginName );
		memberInfo.setLoginPwd( MD5Utils.encode( loginPwd ) );
		memberInfo.setMemberType( 1 );
		memberInfo.setMobileNum( mobileNum );
		memberInfo.setRoleId( 1 );
		memberInfo.setStatus( 1 );
		
		try {
			
			Integer memberId = memberInfoService.addMemberInfo( memberInfo );
			memberInfo.setMemberId( memberId );
			memberAccountService.addMemberAccount( memberId, 1 );
			
		} catch ( Exception e ) {
			logger.error( "生成会员信息及账户信息失败!" + e );
			return ContainerUtils.buildResFailMap( "系统异常，请稍后再试!" );
		}
		
		session.removeAttribute( "verifyCode" );
		String sessionId = MD5Utils.encode( session.getId() );
		
		resData.put( "sessionId", sessionId );
		
		sessionService.saveSession( session, sessionId );
		sessionService.saveSessionData( "userInfo", memberInfo, sessionId );
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * test
	 * @throws IOException 
	 */
	@RequestMapping ( value = "/test", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> test( HttpServletRequest request ) throws IOException {
		
		Map<String, Object> resData = new HashMap<String, Object>();
		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		  
		  // 创建'ServletFileUpload'对象
		  ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		  
//		  JSONObject reqJson = ParseUtils.loadPostRequest( request );
		  String test = request.getAttribute( "test" ) + "";
		  String test1 = request.getParameter( "test" );
//		  String test2 = reqJson.getString( "test" );
		  
		  System.out.println( test + "--" + test1 + "--"  );
		  
		  try {
		   // 获取Http请求消息 ('Request') 中的所有请求参数 (包括普通参数以及上传的内容)
		   List<FileItem> fileItems = servletFileUpload.parseRequest(request);
		   
		   // 循环取出Http请求消息 ('Request') 中的所有请求参数
		   for (FileItem fileItem : fileItems) {
		    // 如果该Http请求消息中的参数是普通参数 (表单中的一个普通值)
		    if (fileItem.isFormField()) {
		     // 获得表单中的普通参数值 (上传者, 文件的等级), 使用UTF-8的编码格式获取该普通参数的值
		     String requestOfValue = fileItem.getString("UTF-8");
		     
		     // 获得表单中的普通参数在请求中所对应的名字
		      String requestForName = fileItem.getFieldName();
		     
		      // 打印Http请求消息 ('Request') 中普通参数的信息
		     System.out.println("表单中的普通参数: 上传人的姓名|文件等级 (" + requestOfValue + "), 表单中的名字为'" + requestForName + "'");
		     
		    // 如果该Http请求消息中的参数是上传的内容
		    } else {
		     // 获得上传文件的名字 (文件上传框中, 上传文件的名字, 注意: 该名字已被截取, 并不是文件的完整路径)
		     String fileName = fileItem.getName();
		     
		     // 用户选择了上传的文件, 并且该文件的名字不为空
		     if (fileItem.getName() != null && !"".equals(fileItem.getName())){
		      // 获得上传文件的类型
		      String contentType = fileItem.getContentType();
		      
		      // 获得上传文件的大小
		      long fileSize = fileItem.getSize();
		      
		      // 打印Http请求消息 ('Request') 中上传文件的信息
		      System.out.println("上传的文件名: " + fileName);
		      System.out.println("上传的文件类型: " + contentType);
		      System.out.println("上传的文件大小: " + fileSize);
		      
		      
		      
		      // 获得项目的根目录
		      String rootDirProject = request.getSession().getServletContext().getRealPath("/");
		      
		      // 拼接保存的上传文件的目录
		      File saveFileUploadFile = new File( "D://test.png" );
		      
		      // 如果保存上传文件的目录不存在, 创建
		      if (!saveFileUploadFile.exists()) {
		       saveFileUploadFile.mkdirs();
		      }
		      
		      // 拼接保存上传内容的文件
		      File saveTheFileUpload = new File(saveFileUploadFile, fileName);
		      
		      // 保存上传的文件
		      fileItem.write(saveTheFileUpload);
		      
		     }
		    }
		   }
		  } catch (FileUploadException e) {
		   e.printStackTrace();
		   throw new RuntimeException(e);
		  } catch (Exception e) {
		   e.printStackTrace();
		   throw new RuntimeException(e);
		  }
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * test
	 * @throws IOException 
	 */
	@RequestMapping ( value = "/testUpload", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> testUpload( @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file ) throws IOException {
		
		try {
			System.out.println( name );
			System.out.println( file.getName() );
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File( "F://test/" + file.getName() )));
            stream.write(bytes);
            return null;
        } catch (Exception e) {
            return null;
        }

	}
	

}
