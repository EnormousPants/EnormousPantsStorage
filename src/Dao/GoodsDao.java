package Dao;
 import entity.Goods;
 import util.DateCalc;
 import util.JdbcUtil;
 import util.QGPSet;

 import java.sql.*;
 import java.util.ArrayList;
 import java.util.Comparator;
 import java.util.List;

public class GoodsDao {//负责连接到数据库的数据访问对象方法集
    //录入货物信息
    public static int add(String name,String date,int qgp){
        String sql="insert into Goods(Name,Date,QGP) values(?,?,?)";
        JdbcUtil util =new JdbcUtil();
        int result =0;
        PreparedStatement ps = util.createStatement(sql);
        try {//获取参数值，按位置对应到sql语句中的“？”字符位置，下同
            ps.setString(1,name);
            ps.setString(2,date);
            ps.setInt(3,qgp);
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
    public static int updateBySingle(String id,String name,String date,int qgp){
        String sql = "update Goods set Date=?,Name=?,QGP=? where id = ?";
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = util.createStatement(sql);
        int result = 0 ;
        try {
            ps.setInt(4, Integer.parseInt(id));
            ps.setString(2,name);
            ps.setString(1,date);
            ps.setInt(3,qgp);
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
        String id,name,date;
        int qgp=0,mqgp=0;
        QGPSet QgpSet = new QGPSet();
        DateCalc dateCalc=new DateCalc();
        String sql = "update Goods set QGP=? where id = ?";
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = util.createStatement(sql);
        List<Goods> list = GoodsDao.QueryGoods();
        int result=0;
        for(Goods goods:list){
            id=Integer.toString(goods.getId());
            name= goods.getName();
            date=goods.getDate();
            mqgp=QgpSet.qgpSet(name);
            qgp=dateCalc.Calc(date,mqgp);//对剩余保质期进行重新计算
            try {
                ps.setInt(2,Integer.parseInt(id)) ;
                ps.setInt(1,qgp);
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
                int qgp = rs.getInt("QGP");
                Goods goods = new Goods(id,name,date,qgp);
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
        List<Goods> list = GoodsDao.QueryGoods();//首先获取所有货物信息
        list.sort(new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {//按保质期顺序对暂存货物信息进行升序排序
                if(o1.getQGP() > o2.getQGP())
                return 1;
                else return -1;
            }
        });
        return list;
    }
    //按货物录入日期进行查询
    public static List SortByDate() {//按日期进行排序方法，由于写的最早没有对Jdbc方法进行完整封装，按名称进行排序方法同理
        JdbcUtil util = new JdbcUtil();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List list = new ArrayList<>();
        try {//未封装的Jdbc语法，下同
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
                String name = rs.getString("Name");
                String date = rs.getString("Date");
                int qgp = rs.getInt("QGP");
                Goods goods = new Goods(id,name,date,qgp);
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
    public static List QueryByName(String Name_1) {//按名称进行排序方法
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
                int qgp = rs.getInt("QGP");
                if(Name.equals(Name_1)){//只取出符合需求名称的数据列
                    Goods goods = new Goods(id,Name,Date,qgp);
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
    //用于Swing封装后的两个警告方法，区别于下方用于dos输出的一个警告方法
    //判断是否有即将过期（默认为剩余保质期小于等于10天）的货物
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
    //根据上方方法的返回值调出警告窗口的方法
    public static List Alert_L(){
        int id=0,QGP=0;
        String Name=null,Date=null;
        List<Goods> list = GoodsDao.SortByQGP();
        List<Goods> list_1 = new ArrayList<>();
        for(int i=0;i<list.size();i++){//获取即将过期商品按保质期排序的列表
            if(Integer.valueOf(list.get(i).getQGP())<=10){//判断指向货物是否满足报警条件
                list_1.add(list.get(i));//只取走需要报警的货物信息
            }
            else break;
        }
        return list_1;
    }
    //用于dos窗口的判断是否有即将过期（默认为剩余保质期小于等于10天）的货物并输出信息方法
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
                if(Integer.valueOf(goods.getQGP())<=10){//判断指向货物是否满足报警条件
                    //直接输出需要报警的货物信息
                    System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
                }
                else break;
            }
        }
    }
    //自动出货函数
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

