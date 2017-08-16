package Twitter;

import Properties.TwitterPropertiesManager;
import com.google.common.collect.Lists;
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
 * Created by Lennard on 15-8-2017.
 */
public class TwitterConnector {
    private static TwitterPropertiesManager twitterPropertiesManager = new TwitterPropertiesManager();

    public static void connect() throws Exception
    {
        Authentication hosebirdAuth = new OAuth1(twitterPropertiesManager.getConsumerKey(), twitterPropertiesManager.getConsumerSecret(), twitterPropertiesManager.getToken(), twitterPropertiesManager.getTokenSecret());
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);

        List<Long> followings = Lists.newArrayList(1234L, 566788L);
        List<String> terms = Lists.newArrayList("twitter", "api");
        hosebirdEndpoint.followings(followings);
        hosebirdEndpoint.trackTerms(terms);

        ClientBuilder builder = new ClientBuilder()                      // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));
        Client hosebirdClient = builder.build();
// Attempts to establish a connection.
        hosebirdClient.connect();

        while (!hosebirdClient.isDone()) {
            String msg = msgQueue.take();
            System.out.println(msg);
        }
        hosebirdClient.stop();
    }


}
