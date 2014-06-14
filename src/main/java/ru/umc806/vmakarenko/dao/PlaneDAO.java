package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public interface PlaneDAO {
	public List<Plane> getAll();
	public List<Plane> getPlanes(Filter filter);
    public boolean addPlane(Plane plane);
    public boolean deletePlane(int id);
 }
