package com.example.myapplication;


public class Pokemon
{
    int _id;
    String _name;
    String _level;
    String _type;

    public Pokemon(String _name, String _type, String _level) {
        this._id = 0;
        this._name = _name;
        this._level = _level;
        this._type = _type;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_level() {
        return this._level;
    }

    public void set_level(String _level) {
        this._level = _level;
    }

    public String get_type() {
        return this._type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }
}