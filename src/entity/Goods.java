package entity;

public class Goods {
    private  int id;
    private String Name;//货物名称
    private String Date;//入库日期
    private int QGP;//保质期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public int getQGP() {
        return QGP;
    }

    public void setQGP(int QGP) {
        this.QGP = QGP;
    }

    public Goods(int id,String name, String date, int QGP) {
        this.id=id;
        this.Name = name;
        this.Date = date;
        this.QGP = QGP;
    }
}
