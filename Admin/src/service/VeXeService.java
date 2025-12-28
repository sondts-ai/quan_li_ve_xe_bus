package service;

import dao.VeXeDao;
import java.util.List;
import java.util.ArrayList;
import model.VeXeTriTiet;

public class VeXeService {

    private VeXeDao dao = new VeXeDao();

    public boolean datVe(int nguoiDungId, int lichId, String viTriGhe) {
        if (nguoiDungId <= 0 || lichId <= 0) {
            return false;
        }
        if (viTriGhe == null || viTriGhe.trim().isEmpty()) {
            return false;
        }

        return dao.datVe(nguoiDungId, lichId, viTriGhe.trim());
    }


    public boolean huyVe(int veId) {
        if (veId <= 0) {
            return false;
        }

        return dao.huyVe(veId);
    }

    public List<String> getGheDaDat(int lichId) {
        if (lichId <= 0) {
            return new ArrayList<>();
        }

        return dao.getGheDaDat(lichId);
    }
    public List<VeXeTriTiet> getLichSuDatVe(int nguoiDungId) {

        if (nguoiDungId <= 0) {
            return new ArrayList<>();
        }
        return dao.getLichSuDatVe(nguoiDungId);
    }

}