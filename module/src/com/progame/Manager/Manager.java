package com.progame.Manager;

public class Manager {
    private String manager_Name;
    private String manager_Password;

    public Manager() {
    }

    public Manager(String manager_Name, String manager_Password) {
        this.manager_Name = manager_Name;
        this.manager_Password = manager_Password;
    }

    public String getManager_Name() {
        return manager_Name;
    }

    public void setManager_Name(String manager_Name) {
        this.manager_Name = manager_Name;
    }

    public String getManager_Password() {
        return manager_Password;
    }

    public void setManager_Password(String manager_Password) {
        this.manager_Password = manager_Password;
    }
}
