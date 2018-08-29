package com.feng.learn.basic.concurrence;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

@SuppressWarnings("serial")
@ThreadSafe
public class VolatileCachedFactorizer extends HttpServlet {
	
	private volatile OneValueCache cache=new OneValueCache(null,null);

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BigInteger i=new BigInteger(req.getParameter("factor"));
		BigInteger[] factors=cache.getFactors(i);
		if (factors==null){
			factors=factors(i);
			cache=new OneValueCache(i,factors);
		}
	}

	private BigInteger[] factors(BigInteger number){
		
		return null;
	}
	

}
