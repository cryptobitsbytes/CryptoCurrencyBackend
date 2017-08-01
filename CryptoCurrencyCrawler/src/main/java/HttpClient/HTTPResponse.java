package HttpClient;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Created by Lennard on 30-7-2017.
 */
public class HTTPResponse {

    private HttpURLConnection httpURLConnection;

    public HTTPResponse(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public int getResponseCode() throws IOException
    {
        return httpURLConnection.getResponseCode();
    }

    public InputStream getContentStream() throws IOException
    {
        return httpURLConnection.getInputStream();
    }

    public String getContentString() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getContentStream()));
        return returnStringFromRequest(bufferedReader);
    }

    private String returnStringFromRequest(BufferedReader reader) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();
        String response;
        while ((response =reader.readLine()) != null) {
            stringBuilder.append(response);
        }
        return  stringBuilder.toString();
    }
}
