package com.sztorma.rest.webservices.restfulwebservices.user;

import java.util.Date;

public class User {

    private Integer id;

    private String name;

    private Date birthDate;

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", birthDate=" + birthDate +
            '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User received = (User) obj;
            return this.id == received.getId();
        }
        return false;
    }
}
