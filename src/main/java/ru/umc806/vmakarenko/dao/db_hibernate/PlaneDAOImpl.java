package ru.umc806.vmakarenko.dao.db_hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.PlaneDAO;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public class PlaneDAOImpl  extends CommonDAOImpl implements PlaneDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Plane> list() {
        return list(new Filter());
    }

    @Override
    public List<Plane> list(Filter filter) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Plane.class,"plane");
        if(filter.getPlane()!=null){
            Plane plane = filter.getPlane();
            if(plane.getId()!=null){
                criteria.add(Restrictions.eq("plane_id", plane.getId()));
            }
            if(plane.getManufacturer()!=null){
                criteria.add(Restrictions.eq("manufacturer", plane.getManufacturer()));
            }
            if(plane.getModel()!=null){
                criteria.add(Restrictions.eq("model", plane.getModel()));
            }
            if(plane.getSn()!=null){
                criteria.add(Restrictions.eq("sn", plane.getSn()));
            }
        }
        return criteria.list();
    }

}
