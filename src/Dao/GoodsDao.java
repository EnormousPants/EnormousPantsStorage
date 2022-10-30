package Dao;
 import entity.Goods;
 import util.DateCalc;
 import util.JdbcUtil;
 import util.QGPSet;

 import java.sql.*;
 import java.util.ArrayList;
 import java.util.Comparator;
 import java.util.Date;
 import java.util.List;

public class GoodsDao {
    //添加货物信息
    public static int add(String name,String date,int QGP){
        String sql="insert into Goods(Name,Date,QGP) values(?,?,?)";
        JdbcUtil util =new JdbcUtil();
        int result =0;
        PreparedStatement ps = util.createStatement(sql);
        try {
            ps.setString(1,name);
            ps.setString(2,date);
            ps.setInt(3,QGP);
            result=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close();
        }
        return result;
    }
    //删除货物信息
    public static int delete(int id){
        String sql="delete from Goods where id = ?";
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = util.createStatement(sql);
        int result =0;
        try {
            ps.setInt(1,Integer.valueOf(id));
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close();
        }
        return result;
    }
    //更新货物信息
    public static int updateBySingle(String id,String Name,String Date,int QGP){
        String sql = "update Goods set Date=?,Name=?,QGP=? where id = ?";
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = util.createStatement(sql);
        int result = 0 ;
        try {
            ps.setInt(4, Integer.parseInt(id));
            ps.setString(2,Name);
            ps.setString(1,Date);
            ps.setInt(3,QGP);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close();
        }
        return result;
    }
    //自动更新货物剩余保质期
    public static int AutoUpdate(){
        String id,Name,Date;
        int QGP=0,MQGP=0;
        QGPSet QgpSet = new QGPSet();
        DateCalc dateCalc=new DateCalc();
        String sql = "update Goods set QGP=? where id = ?";
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = util.createStatement(sql);
        List<Goods> list = GoodsDao.QueryGoods();
        int result=0;
        for(Goods goods:list){
            id=Integer.toString(goods.getId());
            Name= goods.getName();
            Date=goods.getDate();
            MQGP=QgpSet.qgpSet(Name);
            QGP=dateCalc.Calc(Date,MQGP);
            try {
                ps.setInt(2,Integer.parseInt(id)) ;
                ps.setInt(1,QGP);
                result = ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
            }
        }
        return result;
    }

    //显示当前表内所有货物信息
    public static List QueryGoods(){
        String sql = "select id,Name,Date,QGP from Goods";
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        List list = new ArrayList<>();
        try {
            rs=ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String date = rs.getString("Date");
                int QGP = rs.getInt("QGP");
                Goods goods = new Goods(id,name,date,QGP);
                list.add(goods);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.close(rs);
            return list;
        }
    }
    //按货物剩余保质期进行查询
    public static List SortByQGP(){
        List<Goods> list = GoodsDao.QueryGoods();
        list.sort(new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if(o1.getQGP() > o2.getQGP())
                return 1;
                else return -1;
            }
        });
        return list;
    }
    //按货物录入日期进行查询
    public static List SortByDate() {
        JdbcUtil util = new JdbcUtil();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List list = new ArrayList<>();
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://127.0.0.1:3306/javademo?serverTimezone=UTC";
            String user = "root";
            String password = "Myf63500976mcy";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "select id,Name,Date,QGP from Goods" + " ORDER BY Date ASC ";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {//以列名获取数据
                int id = rs.getInt("id");
                String Name = rs.getString("Name");
                String Date = rs.getString("Date");
                int QGP = rs.getInt("QGP");
                Goods goods = new Goods(id,Name,Date,QGP);
                list.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
            return list;
            }
    }
    //按货物名称进行查询
    public static List QueryByName(String Name_1) {
        JdbcUtil util = new JdbcUtil();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List list = new ArrayList<>();
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://127.0.0.1:3306/javademo?serverTimezone=UTC";
            String user = "root";
            String password = "Myf63500976mcy";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "select id,Name,Date,QGP from Goods" + " ORDER BY Date ASC ";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {//以列名获取数据
                int id = rs.getInt("id");
                String Name = rs.getString("Name");
                String Date = rs.getString("Date");
                int QGP = rs.getInt("QGP");
                if(Name.equals(Name_1)){
                    Goods goods = new Goods(id,Name,Date,QGP);
                    list.add(goods);
                }
                continue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
            return list;
        }
    }
    public static int Alert_i() {
        List<Goods> list = GoodsDao.SortByQGP();
        int flag = 0;
        for (Goods goods : list) {
            if (Integer.valueOf(goods.getQGP()) <= 10)
                flag = 1;
            break;
        }
        return flag;
    }
    public static List Alert_L(){
        int id=0,QGP=0;
        String Name=null,Date=null;
        List<Goods> list = GoodsDao.SortByQGP();
        List<Goods> list_1 = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(Integer.valueOf(list.get(i).getQGP())<=10){
                list_1.add(list.get(i));
            }
            else break;
        }
        return list_1;
    }
    //判断是否有即将过期的货物并输出信息（默认保质期剩余10天内为即将过期）
    public static void Alert(){
        List<Goods> list = GoodsDao.SortByQGP();
        int flag = 0;
        for(Goods goods:list){
            if(Integer.valueOf(goods.getQGP())<=10)
                flag=1;
            break;
        }
        if(flag==0)
            System.out.println("目前没有即将过期的货物");
        else{
            System.out.println("警告！以下商品即将过期！");
            System.out.println("编号  商品名称    录入日期        剩余保质期");
            for(Goods goods:list){
                if(Integer.valueOf(goods.getQGP())<=10){
                    System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
                }
                else break;
            }
        }
    }
    public static int Out(String Name){
        int id=0;
        List<Goods> list = GoodsDao.SortByQGP();
        for(Goods goods:list){
            if(goods.getName().equals(Name)){
                id= goods.getId();
                break;
            }
        }
        int flag=delete(id);
        return flag;
    }
}

