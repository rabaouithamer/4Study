package com.rabaouithamer.a4study;


public class Contact {
    private int _id;
    private String _name;
    private long _phoneNumber;

    public Contact() {
    }

    public Contact(String name, long phoneNumber) {
        this._name = name;
        this._phoneNumber = phoneNumber;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public long get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(long _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }
}
