package model;

public class TuyenXe {
    private int tuyenId;
    private String khoiHanh;
    private String diemDen;
    private float khoangCach;
    private int thoiGianDiChuyen;

    public TuyenXe() {}

    public TuyenXe(int tuyenId, String khoiHanh, String diemDen, float khoangCach, int thoiGianDiChuyen) {
        this.tuyenId = tuyenId;
        this.khoiHanh = khoiHanh;
        this.diemDen = diemDen;
        this.khoangCach = khoangCach;
        this.thoiGianDiChuyen = thoiGianDiChuyen;
    }

    public TuyenXe( String khoiHanh, String diemDen, float khoangCach, int thoiGianDiChuyen) {
        this.khoiHanh = khoiHanh;
        this.diemDen = diemDen;
        this.khoangCach = khoangCach;
        this.thoiGianDiChuyen = thoiGianDiChuyen;
    }


    public int getTuyenId() {
        return tuyenId;
    }

    public void setTuyenId(int tuyenId) {
        this.tuyenId = tuyenId;
    }

    public String getKhoiHanh() {
        return khoiHanh;
    }

    public void setKhoiHanh(String khoiHanh) {
        this.khoiHanh = khoiHanh;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public float getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(float khoangCach) {
        this.khoangCach = khoangCach;
    }

    public int getThoiGianDiChuyen() {
        return thoiGianDiChuyen;
    }

    public void setThoiGianDiChuyen(int thoiGianDiChuyen) {
        this.thoiGianDiChuyen = thoiGianDiChuyen;
    }

}