package com.example.myapplication;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlCon {

    // 資料庫定義
    String mysql_ip = "db4free.net";
    int mysql_port = 3306; // Port 預設為 3306
    String db_name = "eaeayo";
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    String db_user = "carloswang";
    String db_password = "12345678";

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public String getData() {
        String data = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM USER1";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                String P_ID = rs.getString("P_ID");
                String P_HID = rs.getString("P_HID");
                String P_PHONE = rs.getString("P_PHONE");
                String P_PID = rs.getString("P_PID");
                data += P_ID + "," + P_HID + "," + P_PHONE + "," + P_PID +"\n";
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getData222() {
        String data = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM USER2";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                String L_ID	 = rs.getString("L_ID");
                String L_NAME = rs.getString("L_NAME");
                String L_KIND = rs.getString("L_KIND");
                String P_ID = rs.getString("P_ID");
                String P_SID = rs.getString("P_SID");
                String P_PHONE = rs.getString("P_PHONE");
                String OPENING = rs.getString("OPENING");
                String L_ADDRESS = rs.getString("L_ADDRESS");
                String LONGTITUDE = rs.getString("LONGTITUDE");
                String LATITUDE = rs.getString("LATITUDE");
                String L_GOVERN = rs.getString("L_GOVERN");
                String L_AIRPORT = rs.getString("L_AIRPORT");
                String L_SCHOOL = rs.getString("L_SCHOOL");
                String L_COMPANY = rs.getString("L_COMPANY");
                String L_PUBLIC = rs.getString("L_PUBLIC");
                String L_CKIND = rs.getString("L_CKIND");
                String L_BUSINESS = rs.getString("L_BUSINESS");
                String L_LICENSE = rs.getString("L_LICENSE");
                String L_A_ID = rs.getString("L_A_ID");
                String L_ROBOT = rs.getString("L_ROBOT");
                data += L_ID + "," + L_NAME + "," + L_KIND + "," + P_ID + "," + P_SID + "," + P_PHONE + ","
                        + OPENING + "," + L_ADDRESS + "," + LONGTITUDE + "," + LATITUDE + "," + L_GOVERN
                        + "," + L_AIRPORT + L_SCHOOL + "," + L_COMPANY + "," + L_PUBLIC + "," + L_CKIND + ","
                        + L_BUSINESS + "," + L_LICENSE + "," + L_A_ID + "," + L_ROBOT + ","  + "\n";
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getData2() {
        String data = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM TEST";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                String L_ID	 = rs.getString("L_ID");
                String L_NAME = rs.getString("L_NAME");
                String L_KIND = rs.getString("L_KIND");
                String L_COMPANY = rs.getString("L_COMPANY");
                String L_LICENSE = rs.getString("L_LICENSE");
                data += L_ID + "," + L_NAME + "," + L_KIND + "," + L_COMPANY + "," +  L_LICENSE + "\n";
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

/*
    public String getData3() {
        String data = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM USER3";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                String id = rs.getString("id");
                String u_phone = rs.getString("u_phone");
                data += id + "," + u_phone + "\n";
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
*/
public String getlid(String ID) {
    String id="";
    try {
        Connection con = DriverManager.getConnection(url, db_user, db_password);
        String sql = "SELECT * FROM ALLLocation WHERE L_COMPANY = '"+ ID +"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next())
        {

            id = rs.getString(1);

        }
        st.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return id;
}

    public String getpid(String ID) {
        String id="";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT P_ID,P_HID FROM People WHERE P_HID = '"+ ID +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {

                id = rs.getString(1);

            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getData_qr(String ID) {
        String id = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT P_ID,P_CON FROM People WHERE P_ID = '"+ ID +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {

                id = rs.getString(1);

            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getCon(String ID) {
        String cool = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT P_ID,P_CON FROM People WHERE P_ID = '"+ ID +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                cool = rs.getString("P_CON");

            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cool;
    }

    public void insertData(String P_ID, String P_HID, String P_PHONE, String P_PID) {
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "INSERT INTO `USER1` VALUES('"+ P_ID +"','"+ P_HID +"','"+ P_PHONE +"','"+ P_PID +"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
            Log.v("DB", "寫入資料完成：" + "\n" + P_ID + "," + P_HID + "," + P_PHONE + "," + P_PID);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
        }
    }

    public void insertData222(String L_ID, String L_NAME, String L_KIND, String P_ID, String P_SID, String P_PHONE, String OPENING, String L_ADDRESS, String LONGTITUDE, String LATITUDE, String L_GOVERN, String L_AIRPORT, String L_SCHOOL, String L_COMPANY, String L_PUBLIC, String L_CKIND, String L_BUSINESS, String L_LICENSE, String L_A_ID, String L_ROBOT) {
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql2 = "INSERT INTO `USER2` VALUES('"+ L_ID + "," + L_NAME + "," + L_KIND + "," + P_ID + "," + P_SID + "," + P_PHONE + "," + OPENING + "," + L_ADDRESS + "," + LONGTITUDE + "," + LATITUDE + "," + L_GOVERN + "," + L_AIRPORT +","+ L_SCHOOL + "," + L_COMPANY + "," + L_PUBLIC + "," + L_CKIND + "," + L_BUSINESS + "," + L_LICENSE + "," + L_A_ID + "," + L_ROBOT + "')";
            Statement st = con.createStatement();
            st.executeUpdate(sql2);
            st.close();
            Log.v("DB", "寫入資料完成：" + "\n" + L_ID + "," + L_NAME + "," + L_KIND + "," + P_ID + "," + P_SID + "," + P_PHONE + ","
                    + OPENING + "," + L_ADDRESS + "," + LONGTITUDE + "," + LATITUDE + "," + L_GOVERN
                    + "," + L_AIRPORT +","+ L_SCHOOL + "," + L_COMPANY + "," + L_PUBLIC + "," + L_CKIND + ","
                    + L_BUSINESS + "," + L_LICENSE + "," + L_A_ID + "," + L_ROBOT);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
        }
    }

    public void insertData2(String L_ID, String L_NAME, String L_KIND, String L_COMPANY, String L_LICENSE) {
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "INSERT INTO `TEST` VALUES('"+ L_ID +"','"+ L_NAME +"','"+ L_KIND +"','" + L_COMPANY +"','"+ L_LICENSE +"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
            Log.v("DB", "寫入資料完成：" + "\n" + L_ID + "," + L_NAME + ","+ L_KIND +"','" + L_COMPANY + "," + L_LICENSE);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
        }
    }

    public void insertData3(String G_ID, String L_ID,String G_NAME,String Longtitude,String Latitude) {
            try {
                Connection con = DriverManager.getConnection(url, db_user, db_password);
                String sql = "INSERT INTO `TEST2` VALUES ('"+ G_ID +"','"+ L_ID +"','"+ G_NAME +"','"+ Longtitude +"','" + Latitude +"')";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                st.close();
                Log.v("DB", "寫入資料完成：" + "\n" + G_ID + "," + L_ID + "," + G_NAME + ","+ Longtitude +"','" + Latitude );
            } catch (SQLException e) {
                e.printStackTrace();
                Log.e("DB", "寫入資料失敗");
                Log.e("DB", e.toString());
            }
        }

    public String addP_ID(int num){ //People流水號
        String ans = "";
        if(num > 1 && num < 8)
            ans = "P00000" + String.valueOf(num+1);
        else if(num > 9 && num < 98)
            ans = "P0000" + String.valueOf(num+1);
        else if(num > 99 && num < 998)
            ans = "P000" + String.valueOf(num+1);
        else if(num > 999 && num < 9998)
            ans = "P00" + String.valueOf(num+1);
        else if(num > 9999 && num < 99998)
            ans = "P0" + String.valueOf(num+1);
        else if(num > 99999 && num < 999998)
            ans = "P" + String.valueOf(num+1);
        else if(num == 0)
            ans = "P000001";
        return ans;
    }

    public String addL_ID(int num){ //Location流水號
        String ans = "";
        if(num > 1 && num < 8)
            ans = "L00000" + String.valueOf(num+1);
        else if(num > 9 && num < 98)
            ans = "L0000" + String.valueOf(num+1);
        else if(num > 99 && num < 998)
            ans = "L000" + String.valueOf(num+1);
        else if(num > 999 && num < 9998)
            ans = "L00" + String.valueOf(num+1);
        else if(num > 9999 && num < 99998)
            ans = "L0" + String.valueOf(num+1);
        else if(num > 99999 && num < 999998)
            ans = "L" + String.valueOf(num+1);
        else if(num == 0)
            ans = "L000001";
        return ans;
    }

    public String addG_ID(int num){ //Location流水號
        String ans = "";
        if(num > 1 && num < 8)
            ans = "G00000" + String.valueOf(num+1);
        else if(num > 9 && num < 98)
            ans = "G0000" + String.valueOf(num+1);
        else if(num > 99 && num < 998)
            ans = "G000" + String.valueOf(num+1);
        else if(num > 999 && num < 9998)
            ans = "G00" + String.valueOf(num+1);
        else if(num > 9999 && num < 99998)
            ans = "G0" + String.valueOf(num+1);
        else if(num > 99999 && num < 999998)
            ans = "G" + String.valueOf(num+1);
        else if(num == 0)
            ans = "G000001";
        return ans;
    }

    public int Column() { //計算People欄位
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT COUNT(*) FROM USER1";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
        }
        return -1;
    }

    public int Column2() { //計算Location欄位
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT COUNT(*) FROM TEST";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
        }
        return -1;
    }

    public int Column3() { //計算Location欄位
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT COUNT(*) FROM TEST2";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
        }
        return -1;
    }

    /*
    public void transportation(String ID){
        try{
            String L_ID ="";
            String L_KIND ="";
            String L_COMPANY ="";
            String L_NAME="";
            String P_ID="";
            String P_SID="";
            String P_PHONE="";
            String OPENING="";
            String L_ADDRESS="";
            String LONGTITUDE="";
            String LATITUDE="";
            String L_GOVERN="";
            String L_AIRPORT="";
            String L_SCHOOL="";
            String L_PUBLIC="";
            String L_CKIND="";
            String L_BUSINESS="";
            String L_LICENSE="";
            String L_A_ID="";
            String L_ROBOT="";
            String P_HID="";
            Connection con = DriverManager.getConnection(url, db_user, db_password);

            String sql ="INSERT INTO USER2 VALUES '"+ L_ID +"','"+ L_KIND +"','"+ L_COMPANY +"','"+ L_NAME +"'," +
                    "'"+ P_ID +"','"+ P_SID +"','"+ P_PHONE +"','"+ OPENING +"',";

        }catch(SQLException e) {
            e.printStackTrace();
            Log.e("DB", "比對資料失敗");
            Log.e("DB", e.toString());
        }
    }

     */

    public boolean Check (String ID) { //健保
        try {
            String P_ID = "";
            String P_SID = "";
            String P_HID = "";
            String P_PID = "";
            String P_RID = "";
            String P_NAME = "";
            String P_ONAME = "";
            String P_PHONE = "";
            String P_HPHONE = "";
            String P_BDAY = "";
            String P_SEX = "";
            String P_ADDRESS = "";
            String P_HADDRESS = "";
            String P_HOTEL = "";
            String P_NATION = "";
            String P_FID = "";
            String P_CON = "";


            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql1 = "SELECT * FROM ALLPeople WHERE P_HID = '" + ID + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            boolean r = rs.next();


            if (r) {
                P_ID = rs.getString(1);
                P_SID = rs.getString(2);
                P_HID = rs.getString(3);
                P_PID = rs.getString(4);
                P_RID = rs.getString(5);
                P_NAME = rs.getString(6);
                P_ONAME = rs.getString(7);
                P_PHONE = rs.getString(8);
                P_HPHONE = rs.getString(9);
                P_BDAY = rs.getString(10);
                P_SEX = rs.getString(11);
                P_ADDRESS = rs.getString(12);
                P_HADDRESS = rs.getString(13);
                P_HOTEL = rs.getString(14);
                P_NATION = rs.getString(15);
                P_FID = rs.getString(16);
                P_CON = rs.getString(17);


                String sql = "INSERT INTO People VALUES('" + P_ID + "','" + P_SID + "','" + P_HID + "','" + P_PID + "','" + P_RID + "','" + P_NAME + "'," +
                        "'" + P_ONAME + "','" + P_PHONE + "','" + P_HPHONE + "','" + P_BDAY + "','" + P_SEX + "','" + P_ADDRESS + "','" + P_HADDRESS + "','" + P_HOTEL + "','" + P_NATION + "','" + P_FID + "','" + P_CON + "');";
                st = con.createStatement();
                st.executeUpdate(sql);
                Log.v("DB", "比對資料成功");
                st.close();
                return true;
            } else {
                Log.e("DB", "沒");
                st.close();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "比對資料失敗");
            Log.e("DB", e.toString());
        }
        return false ;
    }

    public void Check1(String ID) { //
        try {
            String P_ID = "";
            String P_SID = "";
            String P_HID ="";
            String P_PID ="";
            String P_RID ="";
            String P_NAME ="";
            String P_ONAME ="";
            String P_PHONE ="";
            String P_HPHONE ="";
            String P_BDAY ="";
            String P_SEX ="";
            String P_ADDRESS ="";
            String P_HADDRESS ="";
            String P_HOTEL ="";
            String P_NATION ="";
            String P_FID="";
            String P_CON ="";
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql1 = "SELECT * FROM ALLPeople WHERE P_PID = '"+ ID +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            boolean r = rs.next();
            if(r)
                Log.e("DB", "true");
            else
                Log.e("DB", "false");

            if(r){
                P_ID = rs.getString(1);
                P_SID = rs.getString(2);
                P_HID = rs.getString(3);
                P_PID =rs.getString(4);
                P_RID =rs.getString(5);
                P_NAME =rs.getString(6);
                P_ONAME =rs.getString(7);
                P_PHONE =rs.getString(8);
                P_HPHONE =rs.getString(9);
                P_BDAY =rs.getString(10);
                P_SEX =rs.getString(11);
                P_ADDRESS =rs.getString(12);
                P_HADDRESS =rs.getString(13);
                P_HOTEL =rs.getString(14);
                P_NATION =rs.getString(15);
                P_FID=rs.getString(16);
                P_CON =rs.getString(17);

                String sql = "INSERT INTO People VALUES('"+ P_ID +"','"+ P_SID +"','"+ P_HID +"','"+ P_PID +"','"+ P_RID +"','"+ P_NAME +"'," +
                             "'"+ P_ONAME +"','"+ P_PHONE +"','"+ P_HPHONE +"','"+ P_BDAY +"','"+ P_SEX +"','"+ P_ADDRESS +"','"+ P_HADDRESS +
                             "','"+ P_HOTEL +"','"+ P_NATION +"','"+ P_FID +"','"+ P_CON +"');";
                st = con.createStatement();
                st.executeUpdate(sql);
                st.close();
            }else{
                Log.e("DB", "沒");
            }

            st.close();

            Log.v("DB", "比對資料成功");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "比對資料失敗");
            Log.e("DB", e.toString());
        }

    }

    public void Check2(String ID) { //
        try {
            String P_ID = "";
            String P_SID = "";
            String P_HID ="";
            String P_PID ="";
            String P_RID ="";
            String P_NAME ="";
            String P_ONAME ="";
            String P_PHONE ="";
            String P_HPHONE ="";
            String P_BDAY ="";
            String P_SEX ="";
            String P_ADDRESS ="";
            String P_HADDRESS ="";
            String P_HOTEL ="";
            String P_NATION ="";
            String P_FID="";
            String P_CON ="";
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql1 = "SELECT * FROM ALLPeople WHERE P_PHONE = '"+ ID +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            boolean r = rs.next();
            if(r)
                Log.e("DB", "true");
            else
                Log.e("DB", "false");


            if(r){
                P_ID = rs.getString(1);
                P_SID = rs.getString(2);
                P_HID = rs.getString(3);
                P_PID =rs.getString(4);
                P_RID =rs.getString(5);
                P_NAME =rs.getString(6);
                P_ONAME =rs.getString(7);
                P_PHONE =rs.getString(8);
                P_HPHONE =rs.getString(9);
                P_BDAY =rs.getString(10);
                P_SEX =rs.getString(11);
                P_ADDRESS =rs.getString(12);
                P_HADDRESS =rs.getString(13);
                P_HOTEL =rs.getString(14);
                P_NATION =rs.getString(15);
                P_FID=rs.getString(16);
                P_CON =rs.getString(17);

                String sql = "INSERT INTO People VALUES('"+ P_ID +"','"+ P_SID +"','"+ P_HID +"','"+ P_PID +"','"+ P_RID +"','"+ P_NAME +"','" +
                        "','"+ P_ONAME +"','"+ P_PHONE +"','"+ P_HPHONE +"','"+ P_BDAY +"','"+ P_SEX +"','"+ P_ADDRESS +"','"+ P_HADDRESS +"','"+ P_HOTEL +"','"+ P_NATION +"','"+ P_FID +"','"+ P_CON +"');";
                st = con.createStatement();
                st.executeUpdate(sql);
                st.close();
            }else{
                Log.e("DB", "沒");
            }

            st.close();

            Log.v("DB", "比對資料成功");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "比對資料失敗");
            Log.e("DB", e.toString());
        }

    }

    public boolean Check3(String ID) {
        try {
            String L_ID = "";
            String L_NAME = "";
            String L_KIND="";
            String P_ID ="";
            String P_SID ="";
            String P_PHONE ="";
            String OPENING ="";
            String L_ADDRESS ="";
            String LONGTITUDE ="";
            String LATITUDE ="";
            String L_GOVERN ="";
            String L_AIRPORT ="";
            String L_SCHOOL ="";
            String L_COMPANY ="";
            String L_PUBLIC ="";
            String L_CKIND="";
            String L_BUSINESS ="";
            String L_LICENSE ="";
            String L_A_ID ="";
            Integer L_ROBOT = 0;
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql1 = "SELECT * FROM ALLLocation WHERE L_COMPANY = '"+ ID +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            boolean r = rs.next();
            if(r)
                Log.e("DB", "true");
            else
                Log.e("DB", "false");

            if(r){
                L_ID = rs.getString(1);
                L_NAME = rs.getString(2);

                P_ID =rs.getString(3);
                P_SID =rs.getString(4);
                P_PHONE =rs.getString(5);
                OPENING =rs.getString(6);
                L_ADDRESS =rs.getString(7);
                LONGTITUDE =rs.getString(8);
                LATITUDE =rs.getString(9);
                L_GOVERN =rs.getString(10);
                L_AIRPORT =rs.getString(11);
                L_SCHOOL =rs.getString(12);
                L_COMPANY =rs.getString(13);
                L_PUBLIC =rs.getString(14);
                L_CKIND =rs.getString(15);
                L_BUSINESS =rs.getString(16);
                L_LICENSE =rs.getString(17);
                L_A_ID = rs.getString(18);


                String sql = "INSERT INTO Location VALUES('"+ L_ID +"','"+ L_NAME +"','"+ L_KIND +"','"+ P_ID +"','"+ P_SID +"','"+
                        P_PHONE +"','"+ OPENING +"','"+ L_ADDRESS +"','"+ LONGTITUDE +"','"+ LATITUDE +"','"+ L_GOVERN +"','"+ L_AIRPORT +
                        "','"+ L_SCHOOL +"','"+ L_COMPANY +"','"+ L_PUBLIC +"','"+ L_CKIND +"','"+ L_BUSINESS +"','"+ L_LICENSE +"','"+ L_A_ID +"','" + L_ROBOT + "');";
                st = con.createStatement();
                st.executeUpdate(sql);
                st.close();

                Log.v("DB", "比對資料成功");
                st.close();
                return  true;

            }
            else{
                Log.e("DB", "沒");
                st.close();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "比對資料失敗");
            Log.e("DB", e.toString());
        }
        return false;
    }

    public void Check4(String ID) {
        try {
            String L_ID = "";
            String L_NAME = "";
            String L_KIND="";
            String P_ID ="";
            String P_SID ="";
            String P_PHONE ="";
            String OPENING ="";
            String L_ADDRESS ="";
            String LONGTITUDE ="";
            String LATITUDE ="";
            String L_GOVERN ="";
            String L_AIRPORT ="";
            String L_SCHOOL ="";
            String L_COMPANY ="";
            String L_PUBLIC ="";
            String L_CKIND="";
            String L_BUSINESS ="";
            String L_LICENSE ="";
            String L_A_ID ="";
            Integer L_ROBOT = 0;
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql1 = "SELECT * FROM ALLLocation WHERE L_NAME = '"+ ID +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            boolean r = rs.next();
            if(r)
                Log.e("DB", "true");
            else
                Log.e("DB", "false");

            if(r){
                L_ID = rs.getString(1);
                L_NAME = rs.getString(2);
                P_ID =rs.getString(3);
                P_SID =rs.getString(4);
                P_PHONE =rs.getString(5);
                OPENING =rs.getString(6);
                L_ADDRESS =rs.getString(7);
                LONGTITUDE =rs.getString(8);
                LATITUDE =rs.getString(9);
                L_GOVERN =rs.getString(10);
                L_AIRPORT =rs.getString(11);
                L_SCHOOL =rs.getString(12);
                L_COMPANY =rs.getString(13);
                L_PUBLIC =rs.getString(14);
                L_CKIND =rs.getString(15);
                L_BUSINESS =rs.getString(16);
                L_LICENSE =rs.getString(17);
                L_A_ID = rs.getString(18);


                String sql = "INSERT INTO Location VALUES('"+ L_ID +"','"+ L_NAME +"','"+ L_KIND +"','"+ P_ID +"','"+ P_SID +"','"+
                        P_PHONE +"','"+ OPENING +"','"+ L_ADDRESS +"','"+ LONGTITUDE +"','"+ LATITUDE +"','"+ L_GOVERN +"','"+ L_AIRPORT +
                        "','"+ L_SCHOOL +"','"+ L_COMPANY +"','"+ L_PUBLIC +"','"+ L_CKIND +"','"+ L_BUSINESS +"','"+ L_LICENSE +"','"+ L_A_ID +"','" + L_ROBOT + "');";
                st = con.createStatement();
                st.executeUpdate(sql);
                st.close();

                Log.v("DB", "比對資料成功");

            }
            else{
                Log.e("DB", "沒");
            }
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "比對資料失敗");
            Log.e("DB", e.toString());
        }
    }

    public void Check5(String ID) {
        try {
            String L_ID = "";
            String L_NAME = "";
            String L_KIND="";
            String P_ID ="";
            String P_SID ="";
            String P_PHONE ="";
            String OPENING ="";
            String L_ADDRESS ="";
            String LONGTITUDE ="";
            String LATITUDE ="";
            String L_GOVERN ="";
            String L_AIRPORT ="";
            String L_SCHOOL ="";
            String L_COMPANY ="";
            String L_PUBLIC ="";
            String L_CKIND="";
            String L_BUSINESS ="";
            String L_LICENSE ="";
            String L_A_ID ="";
            Integer L_ROBOT = 0;
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql1 = "SELECT * FROM ALLLocation WHERE L_LICENSE = '"+ ID +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            boolean r = rs.next();
            if(r)
                Log.e("DB", "true");
            else
                Log.e("DB", "false");

            if(r){
                L_ID = rs.getString(1);
                L_NAME = rs.getString(2);
                P_ID =rs.getString(3);
                P_SID =rs.getString(4);
                P_PHONE =rs.getString(5);
                OPENING =rs.getString(6);
                L_ADDRESS =rs.getString(7);
                LONGTITUDE =rs.getString(8);
                LATITUDE =rs.getString(9);
                L_GOVERN =rs.getString(10);
                L_AIRPORT =rs.getString(11);
                L_SCHOOL =rs.getString(12);
                L_COMPANY =rs.getString(13);
                L_PUBLIC =rs.getString(14);
                L_CKIND =rs.getString(15);
                L_BUSINESS =rs.getString(16);
                L_LICENSE =rs.getString(17);
                L_A_ID = rs.getString(18);


                String sql = "INSERT INTO Location VALUES('"+ L_ID +"','"+ L_NAME +"','"+ L_KIND +"','"+ P_ID +"','"+ P_SID +"','"+
                        P_PHONE +"','"+ OPENING +"','"+ L_ADDRESS +"','"+ LONGTITUDE +"','"+ LATITUDE +"','"+ L_GOVERN +"','"+ L_AIRPORT +
                        "','"+ L_SCHOOL +"','"+ L_COMPANY +"','"+ L_PUBLIC +"','"+ L_CKIND +"','"+ L_BUSINESS +"','"+ L_LICENSE +"','"+ L_A_ID +"','" + L_ROBOT + "');";
                st = con.createStatement();
                st.executeUpdate(sql);
                st.close();

                Log.v("DB", "比對資料成功");

            }
            else{
                Log.e("DB", "沒");
            }
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "比對資料失敗");
            Log.e("DB", e.toString());
        }
    }

    /*public void all(String all[]){
        try{
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql1 = "SELECT * FROM ALLLocation";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            rs.last();
            int r=rs.getRow();
            String[][] alldata = new String[r][18];

            rs.first();
            for(int i=0;i<alldata.length;i++){
                for(int j=0;j<alldata[0].length;j++){
                    alldata[i][j]=rs.getString((j+1));
                }
                rs.next();
            }
            sql1 = "SELECT * FROM Location ";
            st.executeQuery(sql1);
            for(int i=0;i<alldata.length;i++){
                if(alldata[i][12].equals(all[1])){
                    sql1="insert value("+"'"+alldata[i][1]+"'"+","+"'"+alldata[i][2]+"'"+","+"'"+alldata[i][3]+"'"+","+"'"+alldata[i][4]+"'"+","+"'"+alldata[i][5]+"'"+","+"'"+alldata[i][6]+"'"+","+"'"+alldata[i][7]+"'"+","+"'"+alldata[i][8]+"'"+","+"'"+alldata[i][9]+"'"+","+"'"+alldata[i][10]+"'"+","+"'"+alldata[i][11]+"'"+","+"'"+alldata[i][12]+"'"+","+"'"+alldata[i][13]+"'"+","+"'"+alldata[i][14]+"'"+","+"'"+alldata[i][15]+"'"+","+"'"+alldata[i][16]+"'"+","+"'"+alldata[i][17]+"'"+")";
                    st.executeUpdate(sql1);
                }

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
