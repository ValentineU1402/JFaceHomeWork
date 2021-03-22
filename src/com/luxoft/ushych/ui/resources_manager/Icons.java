package com.luxoft.ushych.ui.resources_manager;

public enum Icons {
    CHECK_IN("checkIn.png"),
    CHECK_OFF("checkOn.png"),
    ARROW_UP("ArrowUp.png"),
    ARROW_DOWN("ArrowDown.png");

    private final String path;

    private Icons(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
