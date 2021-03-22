package com.luxoft.ushych.services;

import java.util.Comparator;

import com.luxoft.ushych.models.User;

public class TaskDoneComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int i = o1.getTaskDone() == true && o2.getTaskDone() == false ? -1
                : o1.getTaskDone() == true && o2.getTaskDone() == true ? 0
                        : o1.getTaskDone() == false && o2.getTaskDone() == false ? 0 : 1;
        if(i ==0) {
            i = o1.getName().compareToIgnoreCase(o2.getName());
        }
        return i;
    }

}
