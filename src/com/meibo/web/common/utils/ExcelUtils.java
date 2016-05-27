package com.meibo.web.common.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelUtils {

	public static String[] excel2PhoneList(InputStream inputStream) throws Exception {
		
		Workbook workbook = Workbook.getWorkbook(inputStream); // 处理输入流
		Sheet sheet = workbook.getSheet(0);// 获取第一个sheet
		int rows = sheet.getRows(); // 获取总行号
		String[] mobileList = new String[rows];
		for (int i = 0; i < rows; i++) {// 遍历行获得每行信息
			String phone = sheet.getCell(0, i).getContents();// 获得第i行第1列信息
			mobileList[i] = phone;
		}
		return mobileList;
	}

}
