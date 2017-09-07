package Twitter.Observable;

import Twitter.DatabaseManager.TwitterDBManager;
import Twitter.Models.TwitterObject;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Lennard on 7-9-2017.
 */
public class TwitterObserver implements Observer {

    private TwitterDBManager twitterDBManager = null;

    public TwitterObserver(TwitterObservable twitterObservable) {
        this.twitterDBManager = new TwitterDBManager();
        twitterObservable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof TwitterObject)
        {
            twitterDBManager.storeTwitterObject((TwitterObject)o);
            System.out.println("Succesfull storage");
        }
    }
}
