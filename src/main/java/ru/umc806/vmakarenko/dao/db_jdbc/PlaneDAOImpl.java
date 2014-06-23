package ru.umc806.vmakarenko.dao.db_jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.PlaneDAO;
import ru.umc806.vmakarenko.dao.db_jdbc.CommonDAOImpl;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.util.Filter;

import java.sql.ResultSet;
import java.util.List;

public class PlaneDAOImpl  extends CommonDAOImpl implements PlaneDAO {

    public PlaneDAOImpl(){
        super(Plane.class);
    }


    @Override
    public List<Plane> list() {
        return null;
    }

    @Override
    public List<Plane> list(Filter filter) {
        return null;
    }


}
