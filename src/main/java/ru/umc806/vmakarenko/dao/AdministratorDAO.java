package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Administrator;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public interface AdministratorDAO extends CommonDAO<Administrator>{
	public List<Administrator> list();
    public List<Administrator> list(Filter filter);

    public Administrator get(int id);
}
