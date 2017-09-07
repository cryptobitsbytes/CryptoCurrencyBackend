/**
 * Created by Lennard on 1-8-2017.
 */

import Twitter.Observable.TwitterObservable;
import Twitter.Observable.TwitterObserver;
import com.google.common.collect.Lists;

public class Main {
    public static void main(String[] args)
    {
//        ScheduleCryptonator.callCryptonatorAndStoreData();
        TwitterObservable twitterObservable = new TwitterObservable(Lists.newArrayList("bitcoin", "cryptocurrency"));
        TwitterObserver twitterObserver = new TwitterObserver(twitterObservable);
        twitterObservable.startObserving();
    }
}
