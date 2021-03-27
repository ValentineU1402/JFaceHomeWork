package com.luxoft.ushych.ui.resources;

/**
 * Each enum contains file name into resources folder on the project
 *
 * @author vushych@luxoft.com
 */
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
