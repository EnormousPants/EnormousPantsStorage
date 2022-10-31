package main;


import Dao.GoodsDao;
import SWindows.AUFPanel;
import SWindows.AUSPanel;
import SWindows.AlertMenu;
import SWindows.TestMenu;

public class MainPanel {//Swing主函数
    public static void main(String[] args) {
        GoodsDao dao = new GoodsDao();
        //按顺序生成自动更新提示界面、过期警告界面和主界面，最先生成的界面会在最下面
        new TestMenu();//生成主界面，置于最下
        int flag= dao.Alert_i();
        if(flag==1)new AlertMenu();//生成过期警告窗口，若生成则置于中间
        int flag1=dao.AutoUpdate();//生成自动更新提示界面，置于最上
        if(flag1==1)
            new AUSPanel();
        else new AUFPanel();
    }
}
