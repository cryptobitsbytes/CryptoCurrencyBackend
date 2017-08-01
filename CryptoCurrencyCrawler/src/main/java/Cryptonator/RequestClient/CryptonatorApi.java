package Cryptonator.RequestClient;

import Cryptonator.Models.TickerResponse;
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
public class CryptonatorApi {
    private final String apiURL = "https://api.cryptonator.com/api/ticker/btc-usd";

    public CryptonatorApi()
    {

    }
    public TickerResponse callApi() throws Exception
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
        return  tickerResponse;
    }

    private URL  createURL() throws MalformedURLException
    {
        URL url = new URL(apiURL);
        return url;
    }


}
