package com.example.myapplication;

public class PokeStop {
    int _id;
    String nameList;

    public PokeStop(String nameList) {
        this._id = 0;
        this.nameList = nameList;
    }

    public int get_idList() {
        return _id;
    }

    public void set_idList(int _idList) {
        this._id = _idList;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }
}
