package Twitter.DatabaseManager;

import DatabaseLayer.Implementation.MongoDatabaseImplementation;
import DatabaseLayer.Interface.IDatabase;
import Twitter.Models.TwitterObject;

import java.util.List;

/**
 * Created by Lennard on 20-8-2017.
 */
//TODO make SingleTon
public class TwitterDBManager {
    private IDatabase databaseImpl = null;

    public TwitterDBManager() {
        this.databaseImpl = new MongoDatabaseImplementation("Twitter", "Twitter");
    }

    public void storeTwitterObject(TwitterObject twitterObject)
    {
        databaseImpl.store(twitterObject);
    }
    public List<TwitterObject> findAllTickerResponse()
    {
        return databaseImpl.findAll(TwitterObject.class);
    }
}
