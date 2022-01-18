package SqlDeal;

import java.sql.*;
import java.util.Scanner;

public class sqlDealWeather {
    private String fxDate;
    private String tempMax;
    private String tempMin;
    private String textDay;
    private int id;
    private int count;
    public sqlDealWeather(){

    }
    public sqlDealWeather(String fxDate, String tempMax, String tempMin, String textDay, int id) {
        this.fxDate = fxDate;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.textDay = textDay;
        this.id = id;
        count=0;
       /* this.upDateWeather();
        if(count==0){
            this.addWeatherDate();
        }*/
        this.replaceWeather();


    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            //初始化驱动类com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/westtwoweather?characterEncoding=UTF-8","root", "123456");
            //该类就在 mysql-connector-java-5.0.8-bin.jar中,如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void upDateWeather()
    {
        String sql = "update weather set tempMax=?,tempMin=?,textDay=?  where fxDate=? and id=? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = this.getConnection();

            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, tempMax);
            pstmt.setString(2, tempMin);
            pstmt.setString(3, textDay);
            pstmt.setString(4, fxDate);//利用Preparedstatement的set方法给占位符赋值
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            this.close(pstmt);
            this.close(conn);		//必须关闭
        }

    }

    public void replaceWeather()
    {
        String sql = "replace into weather(fxDate,id,tempMax,tempMin,textDay) values(?,?,?,?,?)";
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try{
            conn = this.getConnection();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);

            pstmt.setString(1, fxDate);//给占位符赋值
            pstmt.setInt(2, id);
            pstmt.setString(3, tempMax);
            pstmt.setString(4, tempMin);
            pstmt.setString(5, textDay);
            try {
                pstmt.executeUpdate();
            }catch (java.sql.SQLIntegrityConstraintViolationException e)
            {
                throw e;
            }
            //执行
        }catch(SQLException e){
            e.printStackTrace();
        } finally{
            this.close(pstmt);
            this.close(conn);		//必须关闭
        }
    }
    public void SelectWeatherDate() {
        Scanner scanner= new Scanner(System.in);
        int IDD= scanner.nextInt();
        String sql = "select *from weather where id="+IDD;
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try{
            conn = this.getConnection();
            pstmt=conn.prepareStatement(sql);
            ResultSet y = pstmt.executeQuery();
            while(y.next())
            System.out.println(y.getString("fxDate")+" 城市编号>>"+y.getInt("id")+" 最高气温>>"+y.getString("tempMax")+"℃ 最低气温>>"+y.getString("tempMin")+"℃ "+y.getString("textDay"));

        }catch(SQLException e){
            e.printStackTrace();
        } finally{
            this.close(pstmt);
            this.close(conn);		//必须关闭
        }
    }



    public void addWeatherDate() {
        String sql = "insert into weather(fxDate,id,tempMax,tempMin,textDay) values(?,?,?,?,?)";
        //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try{
            conn = this.getConnection();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);

            pstmt.setString(1, fxDate);//给占位符赋值
            pstmt.setInt(2, id);
            pstmt.setString(3, tempMax);
            pstmt.setString(4, tempMin);
            pstmt.setString(5, textDay);
            try {
                pstmt.executeUpdate();
            }catch (java.sql.SQLIntegrityConstraintViolationException e)
            {
                throw e;
            }
            //执行
        }catch(SQLException e){
            e.printStackTrace();
        } finally{
            this.close(pstmt);
            this.close(conn);		//必须关闭
        }
    }

    /**
     * 封装三个关闭方法
     * @param pstmt
     */
    public static void close(PreparedStatement pstmt){
        if(pstmt != null){						//避免出现空指针异常
            try{
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }

    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
