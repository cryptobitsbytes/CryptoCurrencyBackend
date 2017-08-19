/**
 * Created by Lennard on 1-8-2017.
 */

import Twitter.TwitterConnector;
import com.google.common.collect.Lists;

public class Main {
    public static void main(String[] args)
    {
        //ScheduleCryptonator.callCryptonatorAndStoreData();
        try {
            TwitterConnector twitterConnector = TwitterConnector.createInstance(null, Lists.newArrayList("bitcoin", "cryptocurrency"));
            twitterConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
