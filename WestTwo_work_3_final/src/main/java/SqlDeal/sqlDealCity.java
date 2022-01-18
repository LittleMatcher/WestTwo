package SqlDeal;

import java.sql.*;

public class sqlDealCity {


        private String name;
        private int id;
        private String lon;
        private String lat;

        public sqlDealCity(){}



        public sqlDealCity(String name, int id, String lon, String lat) throws SQLIntegrityConstraintViolationException {
            this.name = name;
            this.id = id;
            this.lon = lon;
            this.lat = lat;
            this.addCityDate();
        }
        /**
         * 取得数据库的连接
         * @return 一个数据库的连接
         */
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


        public void addCityDate() {
            String sql = "insert into city(city_name,city_longitude,city_latitude,city_id) values(?,?,?,?)";
            //该语句为每个 IN 参数保留一个问号（“？”）作为占位符
            Connection conn = null;				//和数据库取得连接
            PreparedStatement pstmt = null;		//创建statement
            try{
                conn = this.getConnection();
                pstmt = (PreparedStatement) conn.prepareStatement(sql);

                pstmt.setString(1, name);//给占位符赋值
                pstmt.setString(2, lon);
                pstmt.setString(3, lat);
                pstmt.setInt(4, id);
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


