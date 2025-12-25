package dao;

public class TestVeXeDao {
    public static void main(String[] args) {

        VeXeDao dao = new VeXeDao();
        boolean ok = dao.datVe(1, 2, "A1");
        System.out.println(ok ? "Đặt vé thành công" : "Đặt vé thất bại");
    }
}
