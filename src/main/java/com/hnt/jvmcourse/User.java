package com.hnt.jvmcourse;

public class User implements Cloneable{
    private String name;
    private Address address;
    private SensitiveObject sensitiveObject;

    public User(String name, Address address, SensitiveObject sensitiveObject) {
        this.name = name;
        this.address = address;
        this.sensitiveObject = sensitiveObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public SensitiveObject getSensitiveObject() {
        return sensitiveObject;
    }

    public void setSensitiveObject(SensitiveObject sensitiveObject) {
        this.sensitiveObject = sensitiveObject;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", sensitiveObject=" + sensitiveObject +
                '}';
    }

@Override
    public Object clone() throws CloneNotSupportedException{
        User userClone = null;
        userClone = (User) super.clone();

        //overwrite all mutable properties, in this case SensitiveObject
    userClone.setSensitiveObject((SensitiveObject)sensitiveObject.clone());
    return userClone;
}
}
