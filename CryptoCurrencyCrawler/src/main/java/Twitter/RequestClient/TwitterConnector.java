package Twitter.RequestClient;

import HttpClient.JSONParser;
import Twitter.Models.TwitterObject;
import Twitter.Observable.TwitterObservable;
import com.google.gson.JsonParseException;
import com.twitter.hbc.core.Client;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Lennard on 15-8-2017.
 */
public class TwitterConnector {

    private Client client = null;
    private BlockingQueue<String> blockingQueue = null;
    private  TwitterObservable twitterObservable;

    public TwitterConnector(Client client, BlockingQueue blockingQueue, TwitterObservable twitterObservable)
    {
        this.client = client;
        this.blockingQueue = blockingQueue;
        this.twitterObservable = twitterObservable;
    }

    public static TwitterConnector createInstance(List<Long> followings, List<String> trackTerms, TwitterObservable twitterObservable)
    {
        TwitterConnectorBuilder twitterConnectorBuilder = new TwitterConnectorBuilder()
                .setAuthentication()
                .setHosts()
                .setEndpoint()
                .setBlockingQueue(100000)
                .setStringDelimitedProcessor()
                .setClient();

        if(followings != null)
        {
            twitterConnectorBuilder.setFollowings(followings);
        }

        if(trackTerms != null)
        {
            twitterConnectorBuilder.setTrackTerms(trackTerms);
        }

        return twitterConnectorBuilder.build(twitterObservable);
    }

    public void connect() throws Exception
    {
        client.connect();

        while (!client.isDone()) {
            String msg = blockingQueue.take();
            JSONParser parser = new JSONParser();
            try
            {
                TwitterObject twitterObject = parser.parseJSONToObject(msg, TwitterObject.class);
                System.out.println(twitterObject.getText());
                //twitterObservable.newTweet(twitterObject);

            }
            catch (JsonParseException e)
            {
                System.out.println(e);
            }
        }
        client.stop();
    }

}
