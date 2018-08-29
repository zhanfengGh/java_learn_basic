package com.feng.learn.basic.old.enumClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 存入session的User
 * @author milin
 * @date 2014-9-22 下午06:46:01
 */
public class User {

	/** serialVersionUID */
	private static final long serialVersionUID = 7841983192847845785L;
	private String loginFrom = "";// 登入设备

	/** 单点登录的token */
	private String token;
	private String loginAt;

	// moooo core User
	private String moid;

	private String name;

	private String email;

	private String mugshot128;

	private String mugshot64;

	private String e164;

	private String jid;

	private String webImUrl;

	private String tcpPort;

	private String companyMoid;

	private String xmppPassword;

	private List<String> roleKey = new ArrayList<String>(20);

	private Locale locale;

	// UserDetails
	private String password;

	/** 存放以web方式登录的key(account,email,e164,moid,jid) */
	private String username;

	// 未找到信息
	private String timezone;// 用户所在时区

	// 后来新增
	private String account; // 用户自定义账号，登陆账号
	private Integer accountType;// 帐号类型，0-自定义账号;1-号码;3-xmpp超级管理员;4-IP地址段;5-监控设备;6-第三方终端
	private String mobile; // 手机，登陆账号
	private String userDomainMoid; // 所属用户域Moid
	private String userDomainName; // 所属用户域名称
	private String serviceDomainMoid; // 服务域Moid
	private String serviceDomainName; // 服务域名称
	private String deviceGuid; // 终端GUID
	private String nuServerId; // 所属NU服务器ID
	private String deviceType; // 终端类型
	private Boolean binded; // 是否绑定标识
	private Boolean enable; // 帐号启用禁用标识
	private Date createdAt; // 创建时间
	private Boolean limited; // 是否受限标识

	private String extNum; // 分机

	private Date validityPeriod; // E164号的有效期
	private String restrictUsedOn; // E164号限制登录的设备类型（列表）
	private Integer restrictStrategy; // E164号限制登录使用的策略；1：可以在指定的设备类型列表上登录使用；0：可以在指定的设备类型及更低级设备上登录使用

	private Boolean enableHD = false; // 高清
	private Boolean enableCall = false; // 电话呼叫
	private Boolean enableRoam = false; // 漫游
	private Boolean enableSatellite = false; // 卫星线路
	private Boolean enableSatelliteP2P = false; // 卫星线路 点对点会议
	private Boolean userDomainAdmin = false; // 用户域管理员
	private Boolean enableWeibo = false; // 微博
	private Boolean weiboAdmin = false; // 微博管理员
	private Boolean enableMeeting = false; // 会管
	private Boolean meetingAdmin = false; // 会管管理员
	private Boolean enableMeetingSMS = false; // 会管短信
	private Boolean enableBMC = false; // BMC
	private Boolean enableUMC = false; // UMC
	private Boolean enableDCS = false; // DCS
	private Boolean enableVRS = false; // VRS
	private Boolean enableNM = false; // NM
	private Boolean enableVenueMonitor = false; // 会场监控权限
	private Boolean serviceDomainAdmin = false; // 服务域管理员
	private Boolean defaultServiceDomainAdmin = false; // 默认的服务域管理员
	private Boolean defaultUserDomainAdmin = false; // 默认的用户域管理员
	private Boolean enableOut = false; // 出局
	private Boolean enableIncoming = false; // 入局
	private Boolean dcsAdmin = false; // dcs管理员
	private Boolean vrsAdmin = false; // vrs管理员
	private Boolean nmAdmin = false; // nm管理员
	private Boolean enableVideo = false; // VRS子权限(录像)
	private Boolean enableLive = false; // VRS子权限(直播)
	private Boolean enablePlay = false; // VRS子权限(放像)
	private Boolean cmsApproval = false;


	public String getMoid() {
		return moid;
	}

	public void setMoid(String moid) {
		this.moid = moid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMugshot128() {
		return mugshot128;
	}

	public void setMugshot128(String mugshot128) {
		this.mugshot128 = mugshot128;
	}

	public String getMugshot64() {
		return mugshot64;
	}

	public void setMugshot64(String mugshot64) {
		this.mugshot64 = mugshot64;
	}

	public String getE164() {
		return e164;
	}

	public void setE164(String e164) {
		this.e164 = e164;
	}

	public String getJid() {
		return jid;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	public String getWebImUrl() {
		return webImUrl;
	}

	public void setWebImUrl(String webImUrl) {
		this.webImUrl = webImUrl;
	}

	public String getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(String tcpPort) {
		this.tcpPort = tcpPort;
	}

	public String getCompanyMoid() {
		return companyMoid;
	}

	public void setCompanyMoid(String companyMoid) {
		this.companyMoid = companyMoid;
	}

	public String getXmppPassword() {
		return xmppPassword;
	}

	public void setXmppPassword(String xmppPassword) {
		this.xmppPassword = xmppPassword;
	}

	public List<String> getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(List<String> roleKey) {
		this.roleKey = roleKey;
	}

	public String getLoginFrom() {
		return loginFrom;
	}

	public void setLoginFrom(String loginFrom) {
		this.loginFrom = loginFrom;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLoginAt() {
		return loginAt;
	}

	public void setLoginAt(String loginAt) {
		this.loginAt = loginAt;
	}


	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserDomainMoid() {
		return userDomainMoid;
	}

	public void setUserDomainMoid(String userDomainMoid) {
		this.userDomainMoid = userDomainMoid;
	}

	public String getUserDomainName() {
		return userDomainName;
	}

	public void setUserDomainName(String userDomainName) {
		this.userDomainName = userDomainName;
	}

	public String getServiceDomainMoid() {
		return serviceDomainMoid;
	}

	public void setServiceDomainMoid(String serviceDomainMoid) {
		this.serviceDomainMoid = serviceDomainMoid;
	}

	public String getServiceDomainName() {
		return serviceDomainName;
	}

	public void setServiceDomainName(String serviceDomainName) {
		this.serviceDomainName = serviceDomainName;
	}

	public String getDeviceGuid() {
		return deviceGuid;
	}

	public void setDeviceGuid(String deviceGuid) {
		this.deviceGuid = deviceGuid;
	}

	public String getNuServerId() {
		return nuServerId;
	}

	public void setNuServerId(String nuServerId) {
		this.nuServerId = nuServerId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Boolean getBinded() {
		return binded;
	}

	public void setBinded(Boolean binded) {
		this.binded = binded;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getLimited() {
		return limited;
	}

	public void setLimited(Boolean limited) {
		this.limited = limited;
	}

	public Date getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(Date validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public String getRestrictUsedOn() {
		return restrictUsedOn;
	}

	public void setRestrictUsedOn(String restrictUsedOn) {
		this.restrictUsedOn = restrictUsedOn;
	}

	public Integer getRestrictStrategy() {
		return restrictStrategy;
	}

	public void setRestrictStrategy(Integer restrictStrategy) {
		this.restrictStrategy = restrictStrategy;
	}

	public Boolean getEnableHD() {
		return enableHD;
	}

	public void setEnableHD(Boolean enableHD) {
		this.enableHD = enableHD;
	}

	public Boolean getEnableCall() {
		return enableCall;
	}

	public void setEnableCall(Boolean enableCall) {
		this.enableCall = enableCall;
	}

	public Boolean getEnableRoam() {
		return enableRoam;
	}

	public void setEnableRoam(Boolean enableRoam) {
		this.enableRoam = enableRoam;
	}

	public Boolean getEnableSatellite() {
		return enableSatellite;
	}

	public void setEnableSatellite(Boolean enableSatellite) {
		this.enableSatellite = enableSatellite;
	}

	public Boolean getEnableSatelliteP2P() {
		return enableSatelliteP2P;
	}

	public void setEnableSatelliteP2P(Boolean enableSatelliteP2P) {
		this.enableSatelliteP2P = enableSatelliteP2P;
	}

	public Boolean getUserDomainAdmin() {
		return userDomainAdmin;
	}

	public void setUserDomainAdmin(Boolean userDomainAdmin) {
		this.userDomainAdmin = userDomainAdmin;
	}

	public Boolean getEnableWeibo() {
		return enableWeibo;
	}

	public void setEnableWeibo(Boolean enableWeibo) {
		this.enableWeibo = enableWeibo;
	}

	public Boolean getWeiboAdmin() {
		return weiboAdmin;
	}

	public void setWeiboAdmin(Boolean weiboAdmin) {
		this.weiboAdmin = weiboAdmin;
	}

	public Boolean getEnableMeeting() {
		return enableMeeting;
	}

	public void setEnableMeeting(Boolean enableMeeting) {
		this.enableMeeting = enableMeeting;
	}

	public Boolean getMeetingAdmin() {
		return meetingAdmin;
	}

	public void setMeetingAdmin(Boolean meetingAdmin) {
		this.meetingAdmin = meetingAdmin;
	}

	public Boolean getEnableMeetingSMS() {
		return enableMeetingSMS;
	}

	public void setEnableMeetingSMS(Boolean enableMeetingSMS) {
		this.enableMeetingSMS = enableMeetingSMS;
	}

	public Boolean getEnableBMC() {
		return enableBMC;
	}

	public void setEnableBMC(Boolean enableBMC) {
		this.enableBMC = enableBMC;
	}

	public Boolean getEnableUMC() {
		return enableUMC;
	}

	public void setEnableUMC(Boolean enableUMC) {
		this.enableUMC = enableUMC;
	}

	public Boolean getEnableDCS() {
		return enableDCS;
	}

	public void setEnableDCS(Boolean enableDCS) {
		this.enableDCS = enableDCS;
	}

	public Boolean getEnableVRS() {
		return enableVRS;
	}

	public void setEnableVRS(Boolean enableVRS) {
		this.enableVRS = enableVRS;
	}

	public Boolean getEnableNM() {
		return enableNM;
	}

	public void setEnableNM(Boolean enableNM) {
		this.enableNM = enableNM;
	}

	public Boolean getEnableVenueMonitor() {
		return enableVenueMonitor;
	}

	public void setEnableVenueMonitor(Boolean enableVenueMonitor) {
		this.enableVenueMonitor = enableVenueMonitor;
	}

	public Boolean getServiceDomainAdmin() {
		return serviceDomainAdmin;
	}

	public void setServiceDomainAdmin(Boolean serviceDomainAdmin) {
		this.serviceDomainAdmin = serviceDomainAdmin;
	}

	public Boolean getDefaultServiceDomainAdmin() {
		return defaultServiceDomainAdmin;
	}

	public void setDefaultServiceDomainAdmin(Boolean defaultServiceDomainAdmin) {
		this.defaultServiceDomainAdmin = defaultServiceDomainAdmin;
	}

	public Boolean getDefaultUserDomainAdmin() {
		return defaultUserDomainAdmin;
	}

	public void setDefaultUserDomainAdmin(Boolean defaultUserDomainAdmin) {
		this.defaultUserDomainAdmin = defaultUserDomainAdmin;
	}

	public Boolean getEnableOut() {
		return enableOut;
	}

	public void setEnableOut(Boolean enableOut) {
		this.enableOut = enableOut;
	}

	public Boolean getEnableIncoming() {
		return enableIncoming;
	}

	public void setEnableIncoming(Boolean enableIncoming) {
		this.enableIncoming = enableIncoming;
	}

	public Boolean getDcsAdmin() {
		return dcsAdmin;
	}

	public void setDcsAdmin(Boolean dcsAdmin) {
		this.dcsAdmin = dcsAdmin;
	}

	public Boolean getVrsAdmin() {
		return vrsAdmin;
	}

	public void setVrsAdmin(Boolean vrsAdmin) {
		this.vrsAdmin = vrsAdmin;
	}

	public Boolean getNmAdmin() {
		return nmAdmin;
	}

	public void setNmAdmin(Boolean nmAdmin) {
		this.nmAdmin = nmAdmin;
	}

	public Boolean getEnableVideo() {
		return enableVideo;
	}

	public void setEnableVideo(Boolean enableVideo) {
		this.enableVideo = enableVideo;
	}

	public Boolean getEnableLive() {
		return enableLive;
	}

	public void setEnableLive(Boolean enableLive) {
		this.enableLive = enableLive;
	}

	public Boolean getEnablePlay() {
		return enablePlay;
	}

	public void setEnablePlay(Boolean enablePlay) {
		this.enablePlay = enablePlay;
	}

	public Boolean getCmsApproval() {
		return cmsApproval;
	}

	public void setCmsApproval(Boolean cmsApproval) {
		this.cmsApproval = cmsApproval;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return the timezone

	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the extNum
	 */
	public String getExtNum() {
		return extNum;
	}

	/**
	 * @param extNum the extNum to set
	 */
	public void setExtNum(String extNum) {
		this.extNum = extNum;
	}

	// 单点登录返回

	// web登录返回

	public User() {
	};

}
