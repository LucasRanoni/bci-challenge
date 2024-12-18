package com.bci.rest.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public class UserResponse extends User {

  private UUID id;
  private ZonedDateTime created;
  private ZonedDateTime modified;
  private ZonedDateTime lastLogin;
  private String token;
  private Boolean isActive;

  public UserResponse(UUID id, ZonedDateTime created, ZonedDateTime modified, ZonedDateTime lastLogin, String token, Boolean isActive) {
    super();
    this.id = id;
    this.created = created;
    this.modified = modified;
    this.lastLogin = lastLogin;
    this.token = token;
    this.isActive = isActive;
  }

  public UserResponse() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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
