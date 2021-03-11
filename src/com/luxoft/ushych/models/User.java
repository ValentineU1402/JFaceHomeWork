package com.luxoft.ushych.models;

public class User {

    private String name;
    private String group;
    private boolean taskDone;

    public User(String name, String group, boolean taskDone) {
        this.name = name;
        this.group = group;
        this.taskDone = taskDone;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public boolean getTaskDone() {
        return taskDone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (taskDone ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (taskDone != other.taskDone)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", group=" + group + ", taskDone=" + taskDone + "]";
    }

}
