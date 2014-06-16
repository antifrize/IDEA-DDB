package ru.umc806.vmakarenko.service.mock;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.PlaneDAO;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.service.PlaneService;
import ru.umc806.vmakarenko.util.Filter;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by VMakarenko on 5/25/14.
 */
@Transactional
public class PlaneServiceImpl implements PlaneService {
    @Autowired
    private PlaneDAO planeDAO;

    public PlaneDAO getPlaneDAO() {
        return planeDAO;
    }

    public void setPlaneDAO(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    @Override
    public List<Plane> getAll() {
        return planeDAO.list();
    }

    @Override
    public List<Plane> getPlanes(Filter filter) {
        return planeDAO.list(filter);
    }
}
