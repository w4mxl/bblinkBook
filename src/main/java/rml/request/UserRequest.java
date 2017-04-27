package rml.request;

import java.io.Serializable;


public class UserRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675547126499250212L;

	private String headerImageUrl;
	private String nickName;
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	private  int sex;
	private  long createTime;
	private  long updateTime;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getHeaderImageUrl() {
		return headerImageUrl;
	}

	public void setHeaderImageUrl(String headerImageUrl) {
		this.headerImageUrl = headerImageUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	/*private String mobile;

	private String loginType;

	private String pwd;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}*/
	
	

}
