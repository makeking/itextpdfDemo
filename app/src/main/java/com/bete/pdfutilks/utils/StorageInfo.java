package com.bete.pdfutilks.utils;


public class StorageInfo {
    public String path;
    public String state;
    public boolean isRemoveable;
    public StorageInfo(String path) {
        this.path = path;
    }
    public boolean isMounted() {
        return "mounted".equals(state);
    }
    @Override
    public String toString() {
        return "StorageInfo [path=" + path + ", state=" + state
                + ", isRemoveable=" + isRemoveable + "]";
    }
}

