package information;

import com.alibaba.fastjson.annotation.JSONField;

public class Weather {
    @JSONField(name = "fxDate")
    private String fxDate;
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "tempMax")
    private String tempMax;

    @JSONField(name = "tempMin")
    private String tempMin;
    @JSONField(name = "textDay")
    private String textDay;
    public Weather() {
    }
    public Weather(String fxDate, int id, String tempMax, String tempMin, String textDay) {
        this.fxDate = fxDate;
        this.id = id;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.textDay = textDay;
    }

    public String getFxDate() {
        return fxDate;
    }

    public void setFxDate(String fxDate) {
        this.fxDate = fxDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }
}
