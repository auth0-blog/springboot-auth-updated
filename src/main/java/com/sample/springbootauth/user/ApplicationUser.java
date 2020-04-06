package com.sample.springbootauth.user;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * This class stores info about the user. It is used to store user in mongo.
 * It implements UserDetails interface in order to be compatible with spring boot security (AuthenticationManager)
 */
@Document(collection = "users")
public class ApplicationUser implements UserDetails {

  @Indexed(unique = true)
  private String username;
  private String password;
  private String uuid;
  private String type;
  private boolean isLocked;
  private List<String> scopes;

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !isLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return emptyList();
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public List<String> getScopes() {
    return scopes;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "ApplicationUser{" +
      "username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", uuid='" + uuid + '\'' +
      ", type='" + type + '\'' +
      ", isLocked=" + isLocked +
      ", scopes=" + scopes +
      '}';
  }
}
