package ru.umc806.vmakarenko.util.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by VMakarenko on 5/21/14.
 */
public class ScheduleComponentNew {
    private static final int DAYS = 7;
    private List<ScheduleHeaderItem> headerItems = new ArrayList<>();
    public ScheduleComponentNew(){
        Calendar c = Calendar.getInstance();
        for(int i=0;i<DAYS;i++){
            ScheduleHeaderItem item = new ScheduleHeaderItem(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH));
            headerItems.add(item);
            c.add(Calendar.DAY_OF_MONTH,1);
        }
    }

    public List<ScheduleHeaderItem> getHeaderItems() {
        return headerItems;
    }

    public void setHeaderItems(List<ScheduleHeaderItem> headerItems) {
        this.headerItems = headerItems;
    }


}
