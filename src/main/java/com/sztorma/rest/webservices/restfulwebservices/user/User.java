package com.sztorma.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    @Size(min = 2, message = "name is too short at least 2 characters required")
    private String name;

    @Column
    @Past(message = "Can not be future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
