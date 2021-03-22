package com.luxoft.ushych.services;

import java.util.Comparator;

import com.luxoft.ushych.models.User;

public class GroupComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int firstGroup = Integer.parseInt(o1.getGroup());
        int secondGroup = Integer.parseInt(o2.getGroup());
        return firstGroup > secondGroup ? 1 : firstGroup == secondGroup ? 0 : -1;
    }

}
