package se.test.application;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import se.test.dao.BigQuery;
import se.test.dao.Datastore;
import se.test.dao.Memcache;
import se.test.dao.impl.BigQueryImpl;
import se.test.dao.impl.DatastoreImpl;
import se.test.dao.impl.MemcacheImpl;
import se.test.resouce.DefaultResource;
import se.test.resouce.MemcacheResource;
import se.test.resouce.DatastoreResource;
import se.test.util.Util;

public class DefaultApplication extends Application {

    private Datastore datastore;
    private BigQuery bigQuery;
    private Memcache memcache;
	
    @Override
    public Restlet createInboundRoot() {
    	//Should be injected.
    	datastore = new DatastoreImpl();
        bigQuery = new BigQueryImpl();
        memcache = new MemcacheImpl();
        
    	Router router = new Router(getContext());

        router.getContext().getAttributes().put(Util.DATASTORE_DAO_ID, datastore);
        router.getContext().getAttributes().put(Util.BIG_QUERY_DAO_ID, bigQuery);
        router.getContext().getAttributes().put(Util.MEMCACHE_DAO_ID, memcache);
        
        router.attachDefault(DefaultResource.class);
        //Datastore
        router.attach("/datastore", DatastoreResource.class);
        //BigQuery
        
        //Memcache
        router.attach("/memcache", MemcacheResource.class);

        return router;
    }
}
