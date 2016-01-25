package com.demo.store.jdg;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

public class Resources {

	@Inject
	CacheManagerProvider cacheManagerProvider;
	
	 @Produces
	    Logger getLogger(InjectionPoint ip) {
	        String category = ip.getMember().getDeclaringClass().getName();
	        return Logger.getLogger(category);
	    }	
}
