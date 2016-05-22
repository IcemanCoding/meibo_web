package com.meibo.web.order.viewmodel;

public class BlogMediaCommitOrderViewmodel extends BaseMediaCommitOrderViewmodel {
	
	private String content;
	private int[] blogMediaId;
	// 1-直发 2-转发
	private int[] selectedId;
	
	public int[] getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(int[] selectedId) {
		this.selectedId = selectedId;
	}
	public int[] getBlogMediaId() {
		return blogMediaId;
	}
	public void setBlogMediaId(int[] blogMediaId) {
		this.blogMediaId = blogMediaId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
