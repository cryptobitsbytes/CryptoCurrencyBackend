package Twitter.Observable;

import Twitter.Models.TwitterObject;

import java.util.Observable;

/**
 * Created by Lennard on 7-9-2017.
 */
public class TwitterObservable extends Observable {

    public void newTweet(TwitterObject twitterObject)
    {
        setChanged();
        notifyObservers(twitterObject);
    }
}
