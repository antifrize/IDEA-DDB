package ru.umc806.vmakarenko.dao.mock;

import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.PlaneDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;

import java.util.ArrayList;
import java.util.List;

public class PlaneDAOImpl implements PlaneDAO {

    @Override
    public List<Plane> getAll() {
        return null;
    }

    @Override
    public List<Plane> getPlanes(Filter filter) {
        return null;
    }

    @Override
    public boolean addPlane(Plane plane) {
        return false;
    }

    @Override
    public boolean deletePlane(int id) {
        return false;
    }
}
