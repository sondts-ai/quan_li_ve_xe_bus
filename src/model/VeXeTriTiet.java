package model;
import java.util.Date;
public class VeXeTriTiet {
    private int veId;
    private String tenTuyen;
    private Date thoiGianKhoiHanh;
    private String viTriGhe;
    private double giaVe;
    private Date thoiGianDat;
    private String trangThai;
    public VeXeTriTiet() {
    }
    public VeXeTriTiet(int veId, String tenTuyen, Date thoiGianKhoiHanh, String viTriGhe, double giaVe, Date thoiGianDat, String trangThai) {
        this.veId = veId;
        this.tenTuyen = tenTuyen;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.viTriGhe = viTriGhe;
        this.giaVe = giaVe;
        this.thoiGianDat = thoiGianDat;
        this.trangThai = trangThai;
    }
    public int getVeId() { return veId; }
    public void setVeId(int veId) { this.veId = veId; }
    public String getTenTuyen() { return tenTuyen; }
    public void setTenTuyen(String tenTuyen) { this.tenTuyen = tenTuyen; }
    public Date getThoiGianKhoiHanh() { return thoiGianKhoiHanh; }
    public void setThoiGianKhoiHanh(Date thoiGianKhoiHanh) { this.thoiGianKhoiHanh = thoiGianKhoiHanh; }
    public String getViTriGhe() { return viTriGhe; }
    public void setViTriGhe(String viTriGhe) { this.viTriGhe = viTriGhe; }
    public double getGiaVe() { return giaVe; }
    public void setGiaVe(double giaVe) { this.giaVe = giaVe; }
    public Date getThoiGianDat() { return thoiGianDat; }
    public void setThoiGianDat(Date thoiGianDat) { this.thoiGianDat = thoiGianDat; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    @Override
    public String toString() {
        return "Vé " + veId + " | " + tenTuyen + " | Ghế: " + viTriGhe + " | Trạng thái: " + trangThai;
    }
}