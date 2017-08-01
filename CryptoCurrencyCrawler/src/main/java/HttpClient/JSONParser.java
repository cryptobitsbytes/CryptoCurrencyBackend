package HttpClient;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Lennard on 30-7-2017.
 */
public class JSONParser {

    public <T> T parseJSONToObject(String json, Type type)
    {
        Gson gson = new Gson();

        T object = gson.fromJson(json, type);
        return object;
    }
}
