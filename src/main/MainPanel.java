package main;


import Dao.GoodsDao;
import SWindows.AUFPanel;
import SWindows.AUSPanel;
import SWindows.AlertMenu;
import SWindows.TestMenu;

public class MainPanel {
    public static void main(String[] args) {
        GoodsDao dao = new GoodsDao();
        new TestMenu();
        int flag= dao.Alert_i();
        if(flag==1)new AlertMenu();
        int flag1=dao.AutoUpdate();
        if(flag1==1)
            new AUSPanel();
        else new AUFPanel();
    }
}
