package com.meibo.web.core.service.impl;

import java.util.List;

import com.meibo.web.core.service.ICache;

public class RedisStore implements ICache<Object, Object> {

	@Override
	public Object get( Object key ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put( Object key, Object value ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put( Object key, Object value, int expireTime ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove( Object key ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> keys( String pattern ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isExists( Object key ) {
		// TODO Auto-generated method stub
		return false;
	}



}
