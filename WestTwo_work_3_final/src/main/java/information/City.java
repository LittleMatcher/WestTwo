package information;

import com.alibaba.fastjson.annotation.JSONField;

public class City {
    @JSONField(name = "name")
    private String name;

    @JSONField(name = "id")
    private int id;

    @JSONField(name = "lat")
    private String latitude;
    @JSONField(name = "lon")
    private String longitude;
    public City(){

    }
    public City(String name, int id, String latitude, String longitude) {
        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
