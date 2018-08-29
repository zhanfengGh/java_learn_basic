package com.feng.learn.basic.old.enumClass;


/**
 * 用户权限枚举类
 * @author zhangzhanfeng
 * @date 2016-02-02
 */
public enum UserAuthorityEnum {
	/** 
	 * VenueMonitor  会场监控
	 * Video VRS子权限(录像)
	 * Live VRS子权限(直播)
	 * Play VRS子权限(放像)
	 * 
	 * */
	VenueMonitor(0),Video(1),Live(2),Play(3);
	
	/**
	 * 判断用户是否具有相应的权限
	 * @param user
	 * @return
	 */
	public boolean doJudge(User user){
		switch (this){
		case VenueMonitor:
			return user.getEnableVenueMonitor();
		case Video:
			return user.getEnableVideo();
		case Live:
			return user.getEnableLive();
		case Play:
			return user.getEnablePlay();
		default :
			return false;
		}
	}
	
	private final int value;
	
	private UserAuthorityEnum(int value){
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}
	
	
	
	
	

}
