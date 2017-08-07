/**
 * Created by Lennard on 1-8-2017.
 */

import DatabaseLayer.MongoDBInstance;

public class Main {
    public static void main(String[] args)
    {
        //ScheduleCryptonator.callCryptonatorAndStoreData();
        MongoDBInstance.getInstance().testDatabase();
        MongoDBInstance.getInstance().retrieveDatabase();
    }
}
