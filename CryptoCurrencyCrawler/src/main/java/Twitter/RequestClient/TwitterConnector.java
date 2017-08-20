package Twitter.RequestClient;

import HttpClient.JSONParser;
import Twitter.Models.TwitterObject;
import com.twitter.hbc.core.Client;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Lennard on 15-8-2017.
 */
public class TwitterConnector {

    private Client client = null;
    private BlockingQueue<String> blockingQueue = null;

    public TwitterConnector(Client client, BlockingQueue blockingQueue)
    {
        this.client = client;
        this.blockingQueue = blockingQueue;
    }

    public static TwitterConnector createInstance(List<Long> followings, List<String> trackTerms)
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

        return twitterConnectorBuilder.build();
    }

    public void connect() throws Exception
    {
        client.connect();

        while (!client.isDone()) {
            String msg = blockingQueue.take();
            JSONParser parser = new JSONParser();
            TwitterObject twitterObject = parser.parseJSONToObject(msg, TwitterObject.class);
            System.out.println(twitterObject.getText());
        }
        client.stop();
    }

}
