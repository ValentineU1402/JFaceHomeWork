package com.luxoft.ushych.ui.resources_manager;

public enum Icons {
    CHECK_IN("/resources/checkIn.png"), CHECK_OFF("/resources/checkOn.png");

    private final String path;

    private Icons(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
