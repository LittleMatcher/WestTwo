package WeatherSearch;


import SqlDeal.sqlDealCity;
import SqlDeal.sqlDealWeather;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import information.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class getJson {
    static int tempId=0;
    public static void main(String[] args) throws IOException, JSONException {
        Scanner scanner=new Scanner(System.in);
        String string=scanner.nextLine();
        weatherJson(string);
        
       //cityJson("101230101");


    }
    public static void weatherJson(String ID) throws IOException, JSONException {
        String urlStr="https://devapi.qweather.com/v7/weather/3d?key=ef018f8cb71c49ed90f164f6607e854b&location=";
        urlStr+=ID;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream in = connection.getInputStream();
        GZIPInputStream gzipInputStream = new GZIPInputStream(in);
        StringBuilder res = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
        while ((line = br.readLine()) != null) {
            res.append(line);
        }

        String WeatherStr = String.valueOf(res);

        JSONObject json = (JSONObject) JSON.parse(WeatherStr);
        JSONArray jsonArray = json.getJSONArray("daily");
/*
        JSONObject jsonObject= (JSONObject) jsonArray.get(0);
        sqlDealWeather sqlDealWeather = new sqlDealWeather(jsonObject.getString("fxDate"), jsonObject.getString("tempMax"), jsonObject.getString("tempMin"), jsonObject.getString("textDay"),Integer.parseInt(urlStr.substring(88)));
*/

        for(Object obj:jsonArray)
        {
            JSONObject jsonObject = (JSONObject) obj;
            sqlDealWeather sqlDealWeather = new sqlDealWeather(jsonObject.getString("fxDate"), jsonObject.getString("tempMax"), jsonObject.getString("tempMin"), jsonObject.getString("textDay"),Integer.parseInt(urlStr.substring(88)));

        }

    }
    public static void cityJson(String Name) throws IOException {
        String urlStr="https://geoapi.qweather.com/v2/city/lookup?key=ef018f8cb71c49ed90f164f6607e854b&location=";
        urlStr+=Name;
        URL url=new URL(urlStr);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        InputStream in= connection.getInputStream();
        GZIPInputStream gzipInputStream =new GZIPInputStream(in);
        StringBuilder res=new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
        while ((line = br.readLine()) != null)
        {
            res.append(line);
        }

        String cityStr=String.valueOf(res);

        JSONObject json = (JSONObject) JSON.parse(cityStr);
        JSONArray jsonArray=json.getJSONArray("location");
        JSONObject jsonObject= (JSONObject) jsonArray.get(0);
        jsonObject.getString("name");
        System.out.println(jsonObject.getString("name"));
        try {
            sqlDealCity sqlDealCity = new sqlDealCity(jsonObject.getString("name"), Integer.parseInt(jsonObject.getString("id")), jsonObject.getString("lon"), jsonObject.getString("lat"));
        }catch (java.sql.SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Repeat");
        }
        tempId=Integer.parseInt(jsonObject.getString("id"));
    }


}
