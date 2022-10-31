package util;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalc {//计算剩余保质期方法
    public static int Calc(String Date,int MQGP){
        int QGP;
        LocalDate parse = LocalDate.parse(Date);//将输入的日期字符串转为yyyy-MM-dd格式的LocalDate类
        LocalDate localdate=LocalDate.now();//以yyyy-MM-dd格式获取LocalDate类的今日时间
        long between = ChronoUnit.DAYS.between(parse,localdate);//计算从录入日期到今天间隔多少天
        int x=(int)between;
        int y = MQGP;
        int z = y-x;
        QGP=z;//计算式：剩余保质期=最大保质期-今日日期与录入日期间隔天数
        return QGP;
    }

}
