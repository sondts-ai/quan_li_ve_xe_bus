package dao;

import model.NguoiDung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NguoiDungDao {
    public NguoiDung dangNhap(String taiKhoan,String matKhau){
        String sql="SELECT * FROM nguoidung WHERE taikhoan=? AND matkhau=?";
        try(Connection conn=Connectdb.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setString(1, taiKhoan);
            ps.setString(2, matKhau);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new NguoiDung(
                        rs.getInt("nguoidungid"),
                        rs.getString("hoten"),
                        rs.getDate("ngaysinh"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("taikhoan"),
                        rs.getString("matkhau"),
                        rs.getString("vaitro")

                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean dangKi(NguoiDung nd){
        String sql= """
                INSERT INTO nguoidung(hoten,ngaysinh,sdt,email,taikhoan,matkhau,vaitro)
                VALUES(?,?,?,?,?,?,?)
                """;
        try(Connection conn=Connectdb.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,nd.getHoTen());
            ps.setDate(2,nd.getNgaySinh());
            ps.setString(3, nd.getSdt());
            ps.setString(4,nd.getEmail());
            ps.setString(5, nd.getTaiKhoan());
            ps.setString(6,nd.getMatKhau());
            ps.setString(7,nd.getVaiTro());

            return ps.executeUpdate() > 0;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean tonTaiTaiKhoan(String taiKhoan) {
        String sql = "SELECT 1 FROM nguoidung WHERE taikhoan=?";
        try (Connection con = Connectdb.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, taiKhoan);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public NguoiDung layThongTinTheoId(int id) {
        String sql = "SELECT * FROM nguoidung WHERE nguoidungid = ?";
        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new NguoiDung(
                        rs.getInt("nguoidungid"),
                        rs.getString("hoten"),
                        rs.getDate("ngaysinh"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("taikhoan"),
                        rs.getString("matkhau"),
                        rs.getString("vaitro")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean capNhat(NguoiDung nd) {
        String sql = "UPDATE nguoidung SET hoten=?, ngaysinh=?, sdt=?, email=? WHERE nguoidungid=?";
        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nd.getHoTen());
            ps.setDate(2, nd.getNgaySinh());  // java.sql.Date
            ps.setString(3, nd.getSdt());
            ps.setString(4, nd.getEmail());
            ps.setInt(5, nd.getNguoiDungId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
