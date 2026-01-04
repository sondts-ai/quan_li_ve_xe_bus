package model;

import java.sql.Date;

public class NguoiDung {
    private int nguoiDungId;
    private String hoTen;
    private Date ngaySinh;
    private String sdt;
    private String email;
    private String taiKhoan;
    private String matKhau;
    private String vaiTro;
    public NguoiDung() {}
    public NguoiDung(int nguoiDungId, String hoTen, Date ngaySinh, String sdt,
                     String email, String taiKhoan, String matKhau, String vaiTro) {
        this.nguoiDungId = nguoiDungId;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }
    public NguoiDung(String hoTen, Date ngaySinh, String sdt,
                     String email, String taiKhoan, String matKhau, String vaiTro) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }
    public int getNguoiDungId() {
        return nguoiDungId;
    }
    public void setNguoiDungId(int nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public Date getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTaiKhoan() {
        return taiKhoan;
    }
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
    public String getMatKhau() {
        return matKhau;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    public String getVaiTro() {
        return vaiTro;
    }
    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
}