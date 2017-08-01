package HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lennard on 30-7-2017.
 */
public class HTTPRequest {

    private URL url;
    private HttpURLConnection httpURLConnection;
    private String method;


    public URL getUrl() {
        return url;
    }

    public HttpURLConnection getHttpURLConnection() {
        return httpURLConnection;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HTTPRequest(String url) throws IOException
    {
        URL urlObject = new URL(url);
        httpURLConnection = (HttpURLConnection) urlObject.openConnection();
    }

    public HTTPRequest(URL url) throws IOException {
        this.url = url;
        httpURLConnection = (HttpURLConnection) url.openConnection();
    }

    public void addHeaders(String name, String value)
    {
        httpURLConnection.addRequestProperty(name, value);
    }

    public HTTPResponse executeRequest()
    {
        return new HTTPResponse(httpURLConnection);
    }

}
