package com.bci.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    @ColumnDefault("random_uuid()")
    private UUID id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<PhoneEntity> phones;

    private ZonedDateTime created;

    private ZonedDateTime modified;

    @Column(name = "last_login")
    private ZonedDateTime lastLogin;

    private String token;

    @Column(name = "is_active")
    private Boolean isActive;

    @PrePersist
    private void onPersist() {
        this.created = ZonedDateTime.now();
        this.lastLogin = ZonedDateTime.now();
        this.isActive = true;
    }

    @PreUpdate
    private void onUpdate() {
        this.modified = ZonedDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneEntity> phones) {
        this.phones = phones;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getModified() {
        return modified;
    }

    public void setModified(ZonedDateTime modified) {
        this.modified = modified;
    }

    public ZonedDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(ZonedDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
