package service;

import dao.VeXeDao;
import java.util.List;
public class VeXeService {
    private VeXeDao dao=new VeXeDao();
    public boolean datVe(int nguoiDungId,int lichId,String viTriGhe){
        return dao.datVe(nguoiDungId,lichId,viTriGhe);
    }
    public List<String>getGheDaDat(int lichId){
        return dao.getGheDaDat(lichId);
    }
}
