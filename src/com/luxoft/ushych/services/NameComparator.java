package com.luxoft.ushych.services;

import java.util.Comparator;

import com.luxoft.ushych.models.User;

public class NameComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
