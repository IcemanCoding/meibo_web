package com.meibo.web.order.viewmodel;

import org.apache.commons.fileupload.FileItem;

public class BaseMediaCommitOrderViewmodel {
	
	private String executeDate;
	private String resourceLink;
	private FileItem uploadFile;
	private String remark;
	private Integer memberId;
	private String rootDirProject;
	
	public String getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}
	public String getResourceLink() {
		return resourceLink;
	}
	public void setResourceLink(String resourceLink) {
		this.resourceLink = resourceLink;
	}
	public FileItem getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FileItem uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getRootDirProject() {
		return rootDirProject;
	}
	public void setRootDirProject(String rootDirProject) {
		this.rootDirProject = rootDirProject;
	}
	
}
