package Twitter.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lennard on 5-9-2017.
 */
public class Coordinates {

    @SerializedName("coordinates")
    @Expose
    private List<String> coordinates = null;

    @SerializedName("type")
    @Expose
    private String type;

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
