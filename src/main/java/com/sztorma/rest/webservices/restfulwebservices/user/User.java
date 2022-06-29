package com.sztorma.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(36)", nullable = false, updatable = false)
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column
    @Size(min = 2, message = "name is too short at least 2 characters required")
    private String name;

    @Column
    @Past(message = "Can not be future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.birthDate = birthDate;
    }

    public User() {
        this.uuid = UUID.randomUUID();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
