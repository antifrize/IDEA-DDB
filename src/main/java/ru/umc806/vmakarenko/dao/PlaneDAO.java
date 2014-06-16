package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public interface PlaneDAO {
	public List<Plane> list();
	public List<Plane> list(Filter filter);
}
