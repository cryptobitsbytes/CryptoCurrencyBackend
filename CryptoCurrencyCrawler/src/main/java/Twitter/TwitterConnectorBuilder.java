package Twitter;

import Properties.TwitterPropertiesManager;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Lennard on 19-8-2017.
 */
public class TwitterConnectorBuilder {
    private static TwitterPropertiesManager twitterPropertiesManager = new TwitterPropertiesManager();

    private Authentication authentication = null;
    private Hosts hosts = null;
    private StatusesFilterEndpoint statusesFilterEndpoint = null;
    private StringDelimitedProcessor stringDelimitedProcessor = null;
    private Client client = null;
    private BlockingQueue<String> blockingQueue = null;

    public TwitterConnector build()
    {
        return new TwitterConnector(client, blockingQueue);
    }

    public TwitterConnectorBuilder setAuthentication()
    {
        this.authentication = new OAuth1(twitterPropertiesManager.getConsumerKey(), twitterPropertiesManager.getConsumerSecret(), twitterPropertiesManager.getToken(), twitterPropertiesManager.getTokenSecret());
        return this;
    }

    public TwitterConnectorBuilder setHosts()
    {
        this.hosts = new HttpHosts(Constants.STREAM_HOST);
        return this;
    }

    public TwitterConnectorBuilder setFollowings(List<Long> followings)
    {
        this.statusesFilterEndpoint.followings(followings);
        return this;
    }

    public TwitterConnectorBuilder setTrackTerms(List<String> trackTerms)
    {
        this.statusesFilterEndpoint.trackTerms(trackTerms);
        return this;
    }

    public TwitterConnectorBuilder setEndpoint()
    {
        this.statusesFilterEndpoint = new StatusesFilterEndpoint();
        return this;
    }

    public TwitterConnectorBuilder setBlockingQueue(int i)
    {
        this.blockingQueue = new LinkedBlockingQueue<String>(i);
        return this;
    }

    public TwitterConnectorBuilder setStringDelimitedProcessor()
    {
        this.stringDelimitedProcessor = new StringDelimitedProcessor(blockingQueue);
        return this;
    }

    public TwitterConnectorBuilder setClient()
    {
        ClientBuilder builder = new ClientBuilder()                      // optional: mainly for the logs
                .hosts(hosts)
                .authentication(authentication)
                .endpoint(statusesFilterEndpoint)
                .processor(stringDelimitedProcessor);
        Client hosebirdClient = builder.build();
        this.client = hosebirdClient;
        return this;
    }
}
