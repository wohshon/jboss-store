package com.demo.store.jdg;

import java.util.logging.Logger;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

@ApplicationScoped
public class CacheManagerProvider {

	private DefaultCacheManager manager;
	private static final long ENTRY_LIFESPAN = 60 * 1000; // 60 seconds
	
	@Inject
    private Logger log;
	
	public DefaultCacheManager getCacheManager() {
		if (manager==null) {
			log.info("\n\n DefaultCacheManager does not exist - constructing a new one\n\n");
			GlobalConfiguration glob=new GlobalConfigurationBuilder().clusteredDefault()
					.transport().addProperty("configurationFile", "jgroups-udp.xml")
					.globalJmxStatistics().allowDuplicateDomains(true).enable()
					.build();
			Configuration loc=new ConfigurationBuilder().jmxStatistics().enable()
					.clustering().cacheMode(CacheMode.DIST_SYNC)
					.hash().numOwners(2)
					.expiration().lifespan(ENTRY_LIFESPAN)
					.build();
			
			manager=new DefaultCacheManager(glob,loc,true);
			
		}
		return manager;
	}
	
    @PreDestroy
    public void cleanUp() {
        manager.stop();
        manager = null;
    }	
}
