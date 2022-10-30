package main;

import Dao.GoodsDao;

import util.*;
import entity.Goods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GoodsDao dao = new GoodsDao();
        int flag1=dao.AutoUpdate();
        if(flag1==1)
            System.out.println("保质期信息已自动更新");
        else System.out.println("保质期信息自动更新失败");
        dao.Alert();
        //PreparedStatement ps = null;
        //ResultSet rs = null;
        String Id,Name,Date;
        int id = 0,QGP=0,MQGP=0;
        //String sql_1 = "insert into Goods (Name,Date,Qgp) values(?,?,?)";
        Scanner request = new Scanner(System.in);
        int flag = 0;
        System.out.println("————————————————欢迎使用货物管理系统————————————————");
        System.out.println("—————————————————请选择您需要的功能————————————————");
        System.out.println("——————————————————1、录入货物信息——————————————————");
        System.out.println("——————————————————2、显示所有货物信息——————————————");
        System.out.println("——————————————————3、按保质期排序显示所有货物信息————");
        System.out.println("——————————————————4、按录入日期排序显示所有货物信息——");
        System.out.println("——————————————————5、按货物种类查询货物信息—————————");
        System.out.println("——————————————————6、删除货物信息——————————————————");
        System.out.println("——————————————————7、更新货物信息——————————————————");
        System.out.println("——————————————————8、近保质期货物出库———————————————");
        flag = request.nextInt();
        if(flag == 1 ){
            System.out.println("请输入货物名称");
            Name =request.next();
            QGPSet QgpSet = new QGPSet();
            MQGP= QgpSet.qgpSet(Name);
            System.out.println("请按yyyy-MM-dd格式输入录入日期");
            Date =request.next();
            DateCalc dateCalc= new DateCalc();
            QGP=dateCalc.Calc(Date,MQGP);
            flag=dao.add(Name,Date,QGP);
            if(flag==1)
                System.out.println("信息录入成功！");
            else
                System.out.println("信息录入失败");
        }else if(flag==2){
            List<Goods> list=GoodsDao.QueryGoods();
            System.out.println("编号 商品名称    录入日期        剩余保质期");
            for(Goods goods:list){
                System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
            }
            System.out.println("信息已全部显示");
        }else if(flag==3) {
            List<Goods> list = dao.SortByQGP();
            System.out.println("编号 商品名称    录入日期        剩余保质期");
            for(Goods goods:list){
                System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
            }
            System.out.println("信息已全部显示");

        }else if(flag==4){
            List<Goods> list =dao.SortByDate();
            System.out.println("编号 商品名称    录入日期        剩余保质期");
            for(Goods goods:list){
                System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
            }
            System.out.println("信息已全部显示");
        }else if(flag==5) {
            System.out.println("请输入欲查询的货物类名");
            Name = request.next();
            List<Goods> list =dao.QueryByName(Name);
            System.out.println("编号 商品名称    录入日期        剩余保质期");
            for(Goods goods:list){
                System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
            }
            System.out.println("信息已全部显示");
        }else if(flag==6) {
            System.out.println("请输入欲删除货物的编号");
            id = Integer.parseInt(request.next());
            flag = dao.delete(id);
            if (flag == 1)
                System.out.println("信息删除成功！");
            else
                System.out.println("信息删除失败");
        }else if(flag==7){
            System.out.println("请输入欲更新货物的id编号");
            Id = request.next();
            System.out.println("请输入货物名称");
            Name =request.next();
            QGPSet QgpSet = new QGPSet();
            MQGP= QgpSet.qgpSet(Name);
            System.out.println("请按yyyy-MM-dd格式输入录入日期");
            Date =request.next();
            DateCalc dateCalc= new DateCalc();
            QGP=dateCalc.Calc(Date,MQGP);
            flag=dao.updateBySingle(Id,Name,Date,QGP);
            if (flag == 1)
                System.out.println("信息更新成功！");
            else
                System.out.println("信息更新失败");
        }


    }
}
