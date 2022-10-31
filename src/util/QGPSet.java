package util;

public class QGPSet {//返回输入字符串对应的最大保质期
    public static int qgpSet(String Name){
        if("卫尤辣条".equals(Name))
            return 180;
        else if ("梅森午餐肉".equals(Name)) {
            return 180;
        } else if ("老干爹".equals(Name)) {
            return 360;
        } else if ("可爱多多".equals(Name)) {
            return 30;
        } else if ("三金水饺".equals(Name)) {
            return 150;
        }
        return 0;
    }
}
