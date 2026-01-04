package model;

public class Xe {
    private int xeId;
    private String tenXe;
    private String bienSo;
    private int tongGhe;
    private String loaiXe;
    public Xe(){}
    public Xe(int xeId,String tenXe,String bienSo,int tongGhe,String loaiXe){
        this.xeId=xeId;
        this.tenXe=tenXe;
        this.bienSo=bienSo;
        this.tongGhe=tongGhe;
        this.loaiXe=loaiXe;
    }
    public Xe(String tenXe,String bienSo,int tongGhe,String loaiXe){
        this.tenXe=tenXe;
        this.bienSo=bienSo;
        this.tongGhe=tongGhe;
        this.loaiXe=loaiXe;
    }
    public int getXeId(){
        return xeId;
    }
    public void setXeId(int xeId){
        this.xeId=xeId;
    }
    public String getTenXe(){
        return tenXe;
    }
    public void setTenXe(String tenXe){
        this.tenXe = tenXe;
    }
    public String getBienSo(){
        return bienSo;
    }
    public void setBienSo(String bienSo){
        this.bienSo = bienSo;
    }
    public int getTongGhe(){
        return tongGhe;
    }
    public void setTongGhe(int tongGhe){
        this.tongGhe = tongGhe;
    }
    public String getLoaiXe(){
        return loaiXe;
    }
    public void setLoaiXe(String loaiXe){
        this.loaiXe = loaiXe;
    }
}