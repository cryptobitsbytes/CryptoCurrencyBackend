/**
 * Created by Lennard on 1-8-2017.
 */

import Twitter.TwitterConnector;

public class Main {
    public static void main(String[] args)
    {
        //ScheduleCryptonator.callCryptonatorAndStoreData();
        try {
            TwitterConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
