package com.meibo.web.order.viewmodel;

public class NewsMediaCommitOrderViewmodel extends BaseMediaCommitOrderViewmodel {
	
	private int[] newsMediaId;
	private String title;
	
	public int[] getNewsMediaId() {
		return newsMediaId;
	}
	public void setNewsMediaId(int[] newsMediaId) {
		this.newsMediaId = newsMediaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
