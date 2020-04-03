package com.sample.springbootauth.security;

import com.sample.springbootauth.repository.UserRepository;
import com.sample.springbootauth.user.ApplicationUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /*
  * When a user tries to authenticate, this method receives the username, searches the database for a record containing it, and (if found) returns an instance of User.
  * The properties of this instance (username and password) are then checked against the credentials passed by the user in the login request.
  * This last process is executed outside this class, by the Spring Security framework.
  * */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser = userRepository.findByUsername(username);
    if (applicationUser == null) {
      throw new UsernameNotFoundException(username);
    }
    return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
  }
}
