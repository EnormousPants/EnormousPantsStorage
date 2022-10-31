package main;

import Dao.GoodsDao;

import util.*;
import entity.Goods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class Main {//使用dos窗口进行的简单管理系统
    public static void main(String[] args) {
        GoodsDao dao = new GoodsDao();
        int flag1=dao.AutoUpdate();
        if(flag1==1)
            System.out.println("保质期信息已自动更新");
        else System.out.println("保质期信息自动更新失败");//自动更新
        dao.Alert();//过期警告
        String Id,name,date;
        int id = 0,qgp=0,mqgp=0;
        Scanner request = new Scanner(System.in);//生成Scanner读取键盘输入
        int flag = 0;//生成操作数标记
        System.out.println("————————————————欢迎使用货物管理系统————————————————");
        System.out.println("—————————————————请选择您需要的功能————————————————");
        System.out.println("——————————————————1、录入货物信息——————————————————");
        System.out.println("——————————————————2、按录入顺序显示所有货物信息——————");
        System.out.println("——————————————————3、按保质期排序显示所有货物信息————");
        System.out.println("——————————————————4、按录入日期排序显示所有货物信息——");
        System.out.println("——————————————————5、按货物种类查询货物信息—————————");
        System.out.println("——————————————————6、删除货物信息——————————————————");
        System.out.println("——————————————————7、更新货物信息——————————————————");
        System.out.println("——————————————————8、近保质期货物出库———————————————");
        flag = request.nextInt();//读取操作数
        if(flag == 1 ){//录入货物信息
            System.out.println("请输入货物名称");
            name =request.next();//获取货物名称。这一步是不严谨的，因为没有设置对于不包含在数据库中的货物名的检查与纠错功能
            QGPSet qgpSet = new QGPSet();
            mqgp= qgpSet.qgpSet(name);//读取对应商品的最大保质日期
            System.out.println("请按yyyy-MM-dd格式输入录入日期");
            date =request.next();//获取录入日期
            DateCalc dateCalc= new DateCalc();
            qgp=dateCalc.Calc(date,mqgp);//根据录入日期和最大保质期计算剩余保质期
            flag=dao.add(name,date,qgp);//调用录入函数
            if(flag==1)
                System.out.println("信息录入成功！");
            else
                System.out.println("信息录入失败");
        }else if(flag==2){//查询货物信息-1
            List<Goods> list=GoodsDao.QueryGoods();//调用按id（录入顺序）升序排序的货物信息列表
            System.out.println("编号 商品名称    录入日期        剩余保质期");
            for(Goods goods:list){//输出列表，下同
                System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
            }
            System.out.println("信息已全部显示");
        }else if(flag==3) {//查询货物信息-2
            List<Goods> list = dao.SortByQGP();//调用按剩余保质期顺序升序排序的货物信息列表
            System.out.println("编号 商品名称    录入日期        剩余保质期");
            for(Goods goods:list){
                System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
            }
            System.out.println("信息已全部显示");

        }else if(flag==4){//查询货物信息-3
            List<Goods> list =dao.SortByDate();//调用按录入日期顺序升序排序的货物信息列表
            System.out.println("编号 商品名称    录入日期        剩余保质期");
            for(Goods goods:list){
                System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
            }
            System.out.println("信息已全部显示");
        }else if(flag==5) {//查询货物信息-4
            System.out.println("请输入欲查询的货物类名");
            name = request.next();
            List<Goods> list =dao.QueryByName(name);//调用指定货物种类后按录入顺序排序的货物信息列表
            System.out.println("编号 商品名称    录入日期        剩余保质期");
            for(Goods goods:list){
                System.out.println(goods.getId()+"    "+goods.getName()+"    "+goods.getDate()+"录入 保质期还有"+goods.getQGP()+"天");
            }
            System.out.println("信息已全部显示");
        }else if(flag==6) {//删除货物信息
            System.out.println("请输入欲删除货物的编号");
            id = Integer.parseInt(request.next());//获取欲删除货物的id
            flag = dao.delete(id);//调用删除方法
            if (flag == 1)
                System.out.println("信息删除成功！");
            else
                System.out.println("信息删除失败");
        }else if(flag==7){//更新货物信息
            System.out.println("请输入欲更新货物的id编号");
            Id = request.next();//获取字符串形式的Id
            System.out.println("请输入货物名称");
            name =request.next();//获取货物名称，这一步也是不严谨的
            QGPSet QgpSet = new QGPSet();
            mqgp= QgpSet.qgpSet(name);//计算最大保质期
            System.out.println("请按yyyy-MM-dd格式输入录入日期");
            date =request.next();//获取录入日期
            DateCalc dateCalc= new DateCalc();
            qgp=dateCalc.Calc(date,mqgp);//计算剩余保质期
            flag=dao.updateBySingle(Id,name,date,qgp);//调用更新函数
            if (flag == 1)
                System.out.println("信息更新成功！");
            else
                System.out.println("信息更新失败");
        }else if(flag==8){
            System.out.println("请输入欲出货的货物名称");
            name =request.next();//获取货物名称
            flag=dao.Out(name);
            if (flag == 1)
                System.out.println("货物出库成功！");
            else
                System.out.println("货物出库失败");
        }


    }
}
