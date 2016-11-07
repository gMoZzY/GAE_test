package se.test.application;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import se.test.dao.BigQuery;
import se.test.dao.Datastore;
import se.test.dao.impl.BigQueryImpl;
import se.test.dao.impl.DatastoreImpl;
import se.test.resouce.DefaultResource;
import se.test.resouce.DatastoreResource;
import se.test.util.Util;

public class DefaultApplication extends Application {

    private Datastore datastore;
    private BigQuery bigQuery;
	
    @Override
    public Restlet createInboundRoot() {
    	//Should be injected.
    	datastore = new DatastoreImpl();
        bigQuery = new BigQueryImpl();
        
    	Router router = new Router(getContext());

        router.getContext().getAttributes().put(Util.DATASTORE_DAO_ID, datastore);
        router.getContext().getAttributes().put(Util.BIG_QUERY_DAO_ID, bigQuery);
        
        
        router.attachDefault(DefaultResource.class);
        //Datastore
        router.attach("/datastore", DatastoreResource.class);
        //BigQuery
        

        return router;
    }
}
