package com.feng.learn.basic.classloader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

class Meeting{
	
}

class MyInvocationHandler implements InvocationHandler{

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Method: "+method);
		System.out.println("args: "+Arrays.asList(args));
		return null;
	}
	
}

public interface MeetingService {
	
	Meeting getMeetingByMeetingId(Long meetingId);
	
	void updateMeeting(Meeting meeting);

}
