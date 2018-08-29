package com.feng.learn.basic.concurrence.cache;


public interface Computable<A,R> {
	R compute(A args) throws Exception;
}
