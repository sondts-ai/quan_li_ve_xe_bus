package model;

import java.sql.Timestamp;

public class LichTrinh {
    private int lichId;
    private int xeId;
    private int tuyenId;
    private Timestamp thoiGianKhoiHanh;
    private Timestamp thoiGianDen;
    private int soGheTrong;
    private double giaVe;

    public LichTrinh() {}

    public LichTrinh(int lichId, int xeId, int tuyenId, Timestamp thoiGianKhoiHanh,
                     Timestamp thoiGianDen, int soGheTrong, double giaVe) {
        this.lichId = lichId;
        this.xeId = xeId;
        this.tuyenId = tuyenId;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.thoiGianDen = thoiGianDen;
        this.soGheTrong = soGheTrong;
        this.giaVe = giaVe;
    }

    public LichTrinh(int xeId, int tuyenId, Timestamp thoiGianKhoiHanh,
                     Timestamp thoiGianDen, int soGheTrong, double giaVe) {
        this.xeId = xeId;
        this.tuyenId = tuyenId;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.thoiGianDen = thoiGianDen;
        this.soGheTrong = soGheTrong;
        this.giaVe = giaVe;
    }

    // Getters and Setters
    public int getLichId() {
        return lichId;
    }

    public void setLichId(int lichId) {
        this.lichId = lichId;
    }

    public int getXeId() {
        return xeId;
    }

    public void setXeId(int xeId) {
        this.xeId = xeId;
    }

    public int getTuyenId() {
        return tuyenId;
    }

    public void setTuyenId(int tuyenId) {
        this.tuyenId = tuyenId;
    }

    public Timestamp getThoiGianKhoiHanh() {
        return thoiGianKhoiHanh;
    }

    public void setThoiGianKhoiHanh(Timestamp thoiGianKhoiHanh) {
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
    }

    public Timestamp getThoiGianDen() {
        return thoiGianDen;
    }

    public void setThoiGianDen(Timestamp thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
    }

    public int getSoGheTrong() {
        return soGheTrong;
    }

    public void setSoGheTrong(int soGheTrong) {
        this.soGheTrong = soGheTrong;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

}