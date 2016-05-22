package com.meibo.web.order.viewmodel;

public class WechatMediaCommitOrderViewmodel extends BaseMediaCommitOrderViewmodel {
	
	private String content;
	private int[] wechatMediaId;
	// 1-头条 2-第二条 3-其他
	private int[] selectedId;
	private String title;
	private String originalLink;
	
	public int[] getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(int[] selectedId) {
		this.selectedId = selectedId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the wechatMediaId
	 */
	public int[] getWechatMediaId() {
		return wechatMediaId;
	}
	/**
	 * @param wechatMediaId the wechatMediaId to set
	 */
	public void setWechatMediaId(int[] wechatMediaId) {
		this.wechatMediaId = wechatMediaId;
	}
	/**
	 * @return the originalLink
	 */
	public String getOriginalLink() {
		return originalLink;
	}
	/**
	 * @param originalLink the originalLink to set
	 */
	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
