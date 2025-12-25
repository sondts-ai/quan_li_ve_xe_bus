package service;

import dao.NguoiDungDao;
import model.NguoiDung;
public class NguoiDungService {
    private NguoiDungDao dao=new NguoiDungDao();
    //dang nhap
    public NguoiDung login(String taiKhoan,String matKhau){
        return dao.dangNhap(taiKhoan,matKhau);
    }
    //dang ki
    public boolean register(NguoiDung nd){
        if(dao.tonTaiTaiKhoan(nd.getTaiKhoan())){
            return false;
        }
        nd.setVaiTro("khachhang");
        return dao.dangKi(nd);
    }
    //phan quyen
    public boolean isAdmin(NguoiDung nd) {
        return nd != null && "admin".equalsIgnoreCase(nd.getVaiTro());
    }

    public boolean isKhachHang(NguoiDung nd) {
        return nd != null && "khachhang".equalsIgnoreCase(nd.getVaiTro());
    }
}
