package rml.model;

import java.io.Serializable;

public class UserWeapp  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long pid;

	private String userId;

	private String openId;

	private String appId;

	private Integer sex;

	private String province;

	private String city;

	private String country;

	private String headimgurl;

	private String privilege;

	private String unionid;

	private Long createTime;// = System.currentTimeMillis();

	private Long updateTime = System.currentTimeMillis();

	private String createUid;

	private String updateUid;

	private String status;

	private String remark;

	private Integer sort;

	private String nickName;



	/**
	 * 主键不能为空
	 * 
	 * @return
	 */
	public boolean isUpdate() {
		return pid != null && pid > 0;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Long getCreateTime() {
		// return isUpdate() ? createTime : System.currentTimeMillis();
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return isUpdate() ? System.currentTimeMillis() : updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public String getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(String updateUid) {
		this.updateUid = updateUid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


	@Override
	public String toString() {
		return "UserWeapp{" +
				"pid=" + pid +
				", userId='" + userId + '\'' +
				", openId='" + openId + '\'' +
				", appId='" + appId + '\'' +
				", sex=" + sex +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", headimgurl='" + headimgurl + '\'' +
				", privilege='" + privilege + '\'' +
				", unionid='" + unionid + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", createUid='" + createUid + '\'' +
				", updateUid='" + updateUid + '\'' +
				", status='" + status + '\'' +
				", remark='" + remark + '\'' +
				", sort=" + sort +
				", nickName=" +nickName+
				'}';
	}
}
