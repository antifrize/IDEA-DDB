package ru.umc806.vmakarenko.domain;

import java.util.Calendar;

/**
 * Created by VMakarenko on 6/24/14.
 */
public interface Lockable{
    public Calendar getLockTime();
    public Person getLocker();
    public void removeLock();
}
