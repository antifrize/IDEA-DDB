package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

/**
 * Created by VMakarenko on 5/25/14.
 */
public interface PlaneService {
    public List<Plane> getAll();
    public List<Plane> getPlanes(Filter filter);
}
