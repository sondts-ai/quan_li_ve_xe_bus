package model;
import java.sql.Timestamp;
public class VeXe {
    private int veId;
    private int nguoiDungId;
    private int lichId;
    private String viTriGhe;
    private Timestamp thoiGianDat;
    private String trangThai;
    public VeXe(){}
    public VeXe(int veId,int nguoiDungId,int lichId,String viTriGhe,Timestamp thoiGianDat,String trangThai){
        this.veId=veId;
        this.nguoiDungId=nguoiDungId;
        this.lichId=lichId;
        this.viTriGhe=viTriGhe;
        this.thoiGianDat=thoiGianDat;
        this.trangThai=trangThai;
    }
    public VeXe(int nguoiDungId,int lichId,String viTriGhe,Timestamp thoiGianDat,String trangThai){
        this.nguoiDungId=nguoiDungId;
        this.lichId=lichId;
        this.viTriGhe=viTriGhe;
        this.thoiGianDat=thoiGianDat;
        this.trangThai=trangThai;
    }
    public int getVeId() {
        return veId;
    }
    public void setVeId(int veId) {
        this.veId = veId;
    }
    public int getNguoiDungId() {
        return nguoiDungId;
    }
    public void setNguoiDungId(int nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }
    public int getLichId() {
        return lichId;
    }
    public void setLichId(int lichId) {
        this.lichId = lichId;
    }
    public String getViTriGhe() {
        return viTriGhe;
    }
    public void setViTriGhe(String viTriGhe) {
        this.viTriGhe = viTriGhe;
    }
    public Timestamp getThoiGianDat() {
        return thoiGianDat;
    }
    public void setThoiGianDat(Timestamp thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }
    public String getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}