package com.meibo.web.media.viewmodel;

import java.math.BigDecimal;
import org.apache.commons.fileupload.FileItem;

public class BlogMediaInsertViewmodel {
	
	// 头像
	private FileItem file;
	// 二维码
	private FileItem qrCode;
	// 媒体名称
	private String nickname;
	// 注册时间
	private String registerDate;
	// 粉丝数
	private Integer fansCount;
	// 分类
	private String typeName;
	// 直发价格
	private BigDecimal publishPrice;
	// 转发价格
	private BigDecimal forwardPrice;
	// 认证类型
	private Integer authType;
	// 认证信息
	private String authInfo;
	// 简介
	private String desc;
	// 发布地区
	private Integer areaId;
	// 创建人
	private Integer memberId;
	// 备注说明
	private String remark;
	
	public FileItem getFile() {
		return file;
	}
	public void setFile(FileItem file) {
		this.file = file;
	}
	public FileItem getQrCode() {
		return qrCode;
	}
	public void setQrCode(FileItem qrCode) {
		this.qrCode = qrCode;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public Integer getFansCount() {
		return fansCount;
	}
	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public BigDecimal getPublishPrice() {
		return publishPrice;
	}
	public void setPublishPrice(BigDecimal publishPrice) {
		this.publishPrice = publishPrice;
	}
	public BigDecimal getForwardPrice() {
		return forwardPrice;
	}
	public void setForwardPrice(BigDecimal forwardPrice) {
		this.forwardPrice = forwardPrice;
	}
	public Integer getAuthType() {
		return authType;
	}
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	public String getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(String authInfo) {
		this.authInfo = authInfo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
