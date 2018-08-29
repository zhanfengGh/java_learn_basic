package com.feng.learn.basic.old.enumClass;

public class Test {
	public static void main(String[] args){
		for (Season s:Season.values()){
			System.out.println(s);
		}
		
		
		User user=new User();
		user.setEnableLive(true);
		UserAuthorityEnum live=UserAuthorityEnum.Live;
		
		System.out.println(live.doJudge(user));
	}

}
