package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VeXeDao {
    public boolean datVe(int nguoiDungId,int lichId,String viTriGhe){
        String checkGheSql="SELECT 1 FROM vexe WHERE lichid=? AND vitrighe=?";
        String insertVeSql="INSERT INTO vexe(nguoidungid,lichid,vitrighe) VALUES(?,?,?)";
        String updateGhe="UPDATE lichtrinh SET soghetrong=soghetrong-1 WHERE lichid=? AND soghetrong>0";

        Connection conn=null;

        try{
            conn=Connectdb.getConnection();
            if (conn == null) {
                System.out.println("LỖI: Không kết nối được CSDL!");
                return false;
            }
            conn.setAutoCommit(false);
            //kiem tra ghe da dat chua
            try(PreparedStatement ps=conn.prepareStatement(checkGheSql)){
                ps.setInt(1,lichId);
                ps.setString(2,viTriGhe);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    conn.rollback();
                    return false;//ghe bi dat
                }
            }

            //tru ghe trong
            try(PreparedStatement ps=conn.prepareStatement(updateGhe)){
                ps.setInt(1,lichId);
                int rows=ps.executeUpdate();
                if(rows==0){
                    conn.rollback();
                    return false;//hetghe
                }
            }

            //insert ghe
            try (PreparedStatement ps = conn.prepareStatement(insertVeSql)) {
                ps.setInt(1, nguoiDungId);
                ps.setInt(2, lichId);
                ps.setString(3, viTriGhe);
                ps.executeUpdate();
            }
            conn.commit();
            return true;

        }catch (Exception e){
            try{
                if(conn!=null) conn.rollback();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                if (conn != null){
                    conn.setAutoCommit(true);
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<String> getGheDaDat(int lichId) {
        List<String> dsGhe = new ArrayList<>();
        String sql = "SELECT vitrighe FROM vexe WHERE lichid=?";

        try (Connection conn = Connectdb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lichId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dsGhe.add(rs.getString("vitrighe"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsGhe;
    }

}
