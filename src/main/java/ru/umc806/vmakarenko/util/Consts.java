package ru.umc806.vmakarenko.util;

/**
 * Created by VMakarenko on 6/25/14.
 */
public class Consts {
    public static final String UPDATE_SQL_QUERY = "UPDATE :tableName SET locker=:locker, lock_time=:lockTime WHERE :idColumn = :idValue AND (locker is null OR locker=:locker)";
}
