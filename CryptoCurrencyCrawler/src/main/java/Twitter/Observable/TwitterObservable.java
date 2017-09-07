package Twitter.Observable;

import Twitter.Models.TwitterObject;
import Twitter.RequestClient.TwitterConnector;

import java.util.List;
import java.util.Observable;

/**
 * Created by Lennard on 7-9-2017.
 */
public class TwitterObservable extends Observable {
    private TwitterConnector twitterConnector = null;

    public TwitterObservable(List<String> topics) {
        this.twitterConnector =  TwitterConnector.createInstance(null, topics, this);;
    }

    public void newTweet(TwitterObject twitterObject)
    {
        setChanged();
        notifyObservers(twitterObject);
    }

    public void startObserving()
    {
        try {
            twitterConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
