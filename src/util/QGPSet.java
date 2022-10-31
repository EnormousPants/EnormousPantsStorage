package util;

public class QGPSet {//返回输入字符串对应的最大保质期
    public static int qgpSet(String name){
        switch (name) {
            case "卫尤辣条":
                return 180;
            case "梅森午餐肉":
                return 180;
            case "老干爹":
                return 360;
            case "可爱多多":
                return 30;
            case "三金水饺":
                return 150;
            default:
                return 0;
        }

    }
}
