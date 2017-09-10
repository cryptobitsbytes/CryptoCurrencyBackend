package Cryptonator.RequestClient;

import Cryptonator.Models.TickerResponse;
import Cryptonator.Observable.CryptonatorObservable;
import HttpClient.HTTPMethods;
import HttpClient.HTTPRequest;
import HttpClient.HTTPResponse;
import HttpClient.JSONParser;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Lennard on 18-7-2017.
 */
public class CryptonatorConnector {
    private final String apiURL = "https://api.cryptonator.com/api/ticker/btc-usd";
    private CryptonatorObservable cryptonatorObservable = null;

    public CryptonatorConnector(CryptonatorObservable cryptonatorObservable)
    {
        this.cryptonatorObservable = cryptonatorObservable;
    }
    public void connect() throws Exception
    {
        HTTPRequest request = new HTTPRequest(createURL());
        request.setMethod(HTTPMethods.GET);
        request.addHeaders("Accept", "application/json");
        HTTPResponse response = request.executeRequest();

        if(response.getResponseCode() != HttpURLConnection.HTTP_OK)
        {
            throw new Exception("Failed : HTTP error code : "
                    + response.getResponseCode());
        }

        String json = response.getContentString();
        JSONParser parser = new JSONParser();
        TickerResponse tickerResponse = parser.parseJSONToObject(json, TickerResponse.class);
        cryptonatorObservable.newTick(tickerResponse);
    }

    private URL  createURL() throws MalformedURLException
    {
        URL url = new URL(apiURL);
        return url;
    }


}
