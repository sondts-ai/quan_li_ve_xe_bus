package model;

import java.util.Date;
public class ThongKe{
    private String tenTuyen;
    private Date ngayKhoiHanh; 
    private int soVeDaBan;
    private double doanhThu;
    public ThongKe() {
    }
    public ThongKe(String tenTuyen, Date ngayKhoiHanh, int soVeDaBan, double doanhThu) {
        this.tenTuyen = tenTuyen;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.soVeDaBan = soVeDaBan;
        this.doanhThu = doanhThu;
    }
    public String getTenTuyen() { return tenTuyen; }
    public void setTenTuyen(String tenTuyen) { this.tenTuyen = tenTuyen; }
    public Date getNgayKhoiHanh() { return ngayKhoiHanh; }
    public void setNgayKhoiHanh(Date ngayKhoiHanh) { this.ngayKhoiHanh = ngayKhoiHanh; }
    public int getSoVeDaBan() { return soVeDaBan; }
    public void setSoVeDaBan(int soVeDaBan) { this.soVeDaBan = soVeDaBan; }
    public double getDoanhThu() { return doanhThu; }
    public void setDoanhThu(double doanhThu) { this.doanhThu = doanhThu; }
}