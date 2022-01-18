package WeatherSearch;


import SqlDeal.sqlDealCity;
import SqlDeal.sqlDealWeather;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class getJson {
    static int tempId=0;
    static String urlStr1="https://devapi.qweather.com/v7/weather/3d?key=ef018f8cb71c49ed90f164f6607e854b&location=";
    static String urlStr2="https://geoapi.qweather.com/v2/city/lookup?key=ef018f8cb71c49ed90f164f6607e854b&location=";
    static StringBuilder res=null;
    public static void main(String[] args) throws IOException, JSONException, java.sql.SQLIntegrityConstraintViolationException {
        Scanner scanner=new Scanner(System.in);



        /*sqlDealWeather sql=new sqlDealWeather();
        sql.SelectWeatherDate(Integer.parseInt(stringId));*/

        /*sqlDealCity sqlDealCity1=new sqlDealCity();
        sqlDealCity1.SelectCityDate(Integer.parseInt(stringId));*/
        System.out.println("0->退出");
        System.out.println("1->查天气");
        System.out.println("2->查城市");
        System.out.println("3->添加城市");
        System.out.println("4->更新天气");

        System.out.println("请输入功能编号：");
        int count=0;

        boolean judge=true;
        while (judge)
        {
            count=scanner.nextInt();
            switch (count){
                case 0:
                    System.out.println("退出");
                    judge=false;
                    break;
                case 1:
                    System.out.println("查天气");
                    sqlDealWeather sql=new sqlDealWeather();
                    sql.SelectWeatherDate();
                    break;
                case 2:
                    System.out.println("查城市");
                    sqlDealCity sqlDealCity1=new sqlDealCity();
                    sqlDealCity1.SelectCityDate();
                    break;
                case 3:
                    System.out.println("添加城市");
                    addCity();
                    break;
                case 4:
                    System.out.println("更新天气");
                    UpWeather();
                    break;




            }
        }

       // weatherJson(string);

       //cityJson("101230101");


    }
    public static void before(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream in = connection.getInputStream();
        GZIPInputStream gzipInputStream = new GZIPInputStream(in);
         res = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
        while ((line = br.readLine()) != null) {
            res.append(line);
        }
    }
    public static void UpWeather() throws IOException {
        Scanner sc=new Scanner(System.in);
        String stringId=sc.nextLine();
        before(urlStr1+stringId);
        String WeatherStr = String.valueOf(res);
        JSONObject json1 = (JSONObject) JSON.parse(WeatherStr);
        JSONArray jsonArray1 = json1.getJSONArray("daily");
        for(Object obj:jsonArray1)
        {
            JSONObject jsonObject = (JSONObject) obj;
            sqlDealWeather sqlDealWeather = new sqlDealWeather(jsonObject.getString("fxDate"), jsonObject.getString("tempMax"), jsonObject.getString("tempMin"), jsonObject.getString("textDay"),Integer.parseInt(stringId));
        }
        res=null;
    }
    public static void addCity() throws SQLIntegrityConstraintViolationException, IOException {
        Scanner sc=new Scanner(System.in);
        String stringId=sc.nextLine();
        before(urlStr2+stringId);
        String cityStr = String.valueOf(res);
        JSONObject json2 = (JSONObject) JSON.parse(cityStr);
        JSONArray jsonArray2=json2.getJSONArray("location");
        JSONObject jsonObject= (JSONObject) jsonArray2.get(0);
        jsonObject.getString("name");
        System.out.println(jsonObject.getString("name"));
        sqlDealCity sqlDealCity = new sqlDealCity(jsonObject.getString("name"), Integer.parseInt(jsonObject.getString("id")), jsonObject.getString("lon"), jsonObject.getString("lat"));
        res=null;
    }

    /*public static void weatherJson(String ID) throws IOException, JSONException {
        String urlStr1="https://devapi.qweather.com/v7/weather/3d?key=ef018f8cb71c49ed90f164f6607e854b&location=";
        urlStr1+=ID;
        URL url = new URL(urlStr1);
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
*//*
        JSONObject jsonObject= (JSONObject) jsonArray.get(0);
        sqlDealWeather sqlDealWeather = new sqlDealWeather(jsonObject.getString("fxDate"), jsonObject.getString("tempMax"), jsonObject.getString("tempMin"), jsonObject.getString("textDay"),Integer.parseInt(urlStr.substring(88)));
*//*

        for(Object obj:jsonArray)
        {
            JSONObject jsonObject = (JSONObject) obj;
            sqlDealWeather sqlDealWeather = new sqlDealWeather(jsonObject.getString("fxDate"), jsonObject.getString("tempMax"), jsonObject.getString("tempMin"), jsonObject.getString("textDay"),Integer.parseInt(urlStr2.substring(88)));
            sqlDealWeather.SelectWeatherDate();
        }


    }
    public static void cityJson(String Name) throws IOException {
        String urlStr2="https://geoapi.qweather.com/v2/city/lookup?key=ef018f8cb71c49ed90f164f6607e854b&location=";
        urlStr2+=Name;
        URL url=new URL(urlStr2);
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
*/

}
