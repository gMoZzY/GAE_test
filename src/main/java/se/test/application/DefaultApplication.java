package se.test.application;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import se.test.dao.Datastore;
import se.test.dao.impl.DatastoreImpl;
import se.test.resouce.DefaultResource;
import se.test.resouce.GetResource;
import se.test.resouce.SetResource;
import se.test.util.Util;

public class DefaultApplication extends Application {

    private Datastore datastore;
	
    @Override
    public Restlet createInboundRoot() {
    	//Should be injected.
    	datastore = new DatastoreImpl();
        
    	Router router = new Router(getContext());

        router.getContext().getAttributes().put(Util.DATASTORE_DAO_ID, datastore);
        
        router.attachDefault(DefaultResource.class);
        router.attach("/get", GetResource.class);
        router.attach("/set", SetResource.class);


        return router;
    }
}
