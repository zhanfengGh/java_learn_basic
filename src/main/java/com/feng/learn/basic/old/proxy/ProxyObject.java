package com.feng.learn.basic.old.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class ProxyObject extends Proxy implements MeetingService {
	private static Method m1;
	private static Method m5;
	private static Method m0;
	private static Method m4;
	private static Method m3;
	private static Method m2; 

	static {
		try {
			m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
			m5 = Class.forName("learn.proxy.MeetingService").getMethod("loadMeeting", new Class[] { Long.TYPE });
			m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
			m4 = Class.forName("learn.proxy.MeetingService").getMethod("updateMeeting", new Class[] { Class.forName("learn.proxy.Meeting") });
			m3 = Class.forName("learn.proxy.MeetingService").getMethod("getMeetingNameByMeetingId", new Class[] { Long.TYPE });
			m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
		} catch (NoSuchMethodException localNoSuchMethodException) {
			throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
		} catch (ClassNotFoundException localClassNotFoundException) {
			throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
		}
	}

	public ProxyObject(InvocationHandler paramInvocationHandler) {
		super(paramInvocationHandler);
	}

	public final boolean equals(Object paramObject) {
		try {
			return ((Boolean) this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
		} catch (RuntimeException localRuntimeException) {
			throw localRuntimeException;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final Meeting loadMeeting(long paramLong) {
		try {
			return (Meeting) this.h.invoke(this, m5, new Object[] { Long.valueOf(paramLong) });
		} catch (RuntimeException localRuntimeException) {
			throw localRuntimeException;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final int hashCode() {
		try {
			return ((Integer) this.h.invoke(this, m0, null)).intValue();
		} catch (RuntimeException localRuntimeException) {
			throw localRuntimeException;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final void updateMeeting(Meeting paramMeeting) {
		try {
			this.h.invoke(this, m4, new Object[] { paramMeeting });
			return;
		} catch (RuntimeException localRuntimeException) {
			throw localRuntimeException;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final String getMeetingNameByMeetingId(long paramLong) {
		try {
			return (String) this.h.invoke(this, m3, new Object[] { Long.valueOf(paramLong) });
		} catch (RuntimeException localRuntimeException) {
			throw localRuntimeException;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final String toString() {
		try {
			return (String) this.h.invoke(this, m2, null);
		} catch (RuntimeException localRuntimeException) {
			throw localRuntimeException;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

}