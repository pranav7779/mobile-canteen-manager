package com.sjct.common.entity;

import java.util.Calendar;

import com.sjct.common.enumerated.CertCardType;

public class Member implements java.io.Serializable {

	private Long id;

	private String account;

	private String password;

	private String phone;

	private String email;

	/** 用户状态 (0-正常,1-关闭) */
	private int status;

	/** 证件类型 (0-身份证,1-军官证 ,2-护照) */
	private CertCardType certType;

	/** 证件号码 */
	private String certNo;

	/** 性别 (0-男,1-女) */
	private int sex;

	/**来自区域编号*/
	private Long areaId;

	/** IM */
	private String im;

	/** 消费级别 */
	private int userLevel = 0;

	/** 推荐人 */
	private String recoMember;

	/** 注册时间 */
	private Calendar registerTime;

	/** */
	private Calendar lastLoginTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CertCardType getCertType() {
		return certType;
	}

	public void setCertType(CertCardType certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getRecoMember() {
		return recoMember;
	}

	public void setRecoMember(String recoMember) {
		this.recoMember = recoMember;
	}

	public Calendar getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Calendar registerTime) {
		this.registerTime = registerTime;
	}

	public Calendar getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Calendar lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	
}
