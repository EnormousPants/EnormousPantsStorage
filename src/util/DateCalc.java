package util;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalc {
    public static int Calc(String Date,int MQGP){
        int QGP;
        LocalDate parse = LocalDate.parse(Date);
        LocalDate localdate=LocalDate.now();
        long between = ChronoUnit.DAYS.between(parse,localdate);
        int x=(int)between;
        int y = MQGP;
        int z = y-x;
        QGP=z;
        return QGP;
    }

}
