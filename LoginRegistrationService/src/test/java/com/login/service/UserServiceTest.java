package com.login.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.login.dto.AuthDto;
import com.login.dto.UserDto;
import com.login.exception.InvalidCredentialException;
import com.login.exception.UserAlreadyPresentException;
import com.login.model.User;
import com.login.repository.UserRepository;
import com.login.security.Jwtutil;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Jwtutil jwtutil;

    @InjectMocks
    private UserServiceImpl userService;
  
    @Test
    public void testRegisterUser_Success() throws UserAlreadyPresentException {
        // Mocking UserRepository behavior
        when(userRepository.findAll()).thenReturn(Arrays.asList());
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User user = new User();
        user.setName("TestUser");
        user.setEmail("test@example.com");
       user.setPassword("password");

        boolean result = userService.registerUser(user);

       assertTrue(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testRegisterUser_UserAlreadyPresent() {
        // Mocking UserRepository behavior
        User existingUser = new User();
        existingUser.setEmail("test@example.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(existingUser));

       User user = new User();
        user.setName("TestUser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        assertThrows(UserAlreadyPresentException.class, () -> {
           userService.registerUser(user);
        });
    }

    @Test
    public void testGenerateUniqueRandomId() {
        // Mocking UserRepository behavior
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        String id = userService.genrateUniqueRandomId();

        assertNotNull(id);
        assertTrue(id.matches("\\d{1,6}")); // Check if the ID is a number up to 6 digits
    }

    /*
    @Test
   public void testLogin_Success() throws InvalidCredentialException {
        // Mock AuthenticationManager to return a valid Authentication object
        Authentication mockAuthentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication);

        // Mock Authentication object with principal and authorities
        String username = "testuser";
        String password = "password";
        when(mockAuthentication.getName()).thenReturn(username);
        when(mockAuthentication.getAuthorities()).thenReturn((Collection<? extends GrantedAuthority>) Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        // Mock UserRepository to return a user with valid credentials
        User user = new User();
        user.setId("1");
        user.setName("TestUser");
        user.setEmail("test@example.com");
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        when(userRepository.findByNameOrEmail(anyString(), anyString())).thenReturn(user);

        // Mock JWT token generation
        when(jwtutil.generateToken(anyString(), anyString(), anyString(), anyString()))
                .thenReturn("mocked_jwt_token");

        // Prepare login credentials
        AuthDto authDto = new AuthDto();
        authDto.setUserName(username);
        authDto.setPassword(password);

        // Perform login
        String token = userService.login(authDto);

        // Assert the result
         assertNotNull(token);
        assertEquals("mocked_jwt_token", token);
    }    */

    @Test
    public void testLogin_InvalidCredentials() {
      // Mocking AuthenticationManager behavior to throw BadCredentialsException
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
             .thenThrow(BadCredentialsException.class);

       AuthDto authDto = new AuthDto();
       authDto.setEmail("testuser");
       authDto.setPassword("invalid_password");

        assertThrows(InvalidCredentialException.class, () -> {
           userService.login(authDto);
        });
    }        /*    @Test    public void testLogin_Success() throws InvalidCredentialException {        // Mock AuthenticationManager to return a valid Authentication object        Authentication mockAuthentication = mock(Authentication.class);        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))                .thenReturn(mockAuthentication);        // Mock Authentication object with principal and authorities        String username = "testuser";        String password = "password";        when(mockAuthentication.getName()).thenReturn(username);        // Ensure the authorities are set correctly        Collection<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));        when(mockAuthentication.getAuthorities()).thenReturn(authorities);        // Mock UserRepository to return a user with valid credentials        User user = new User();        user.setId("1");        user.setName("TestUser");        user.setEmail("test@example.com");        user.setPassword(passwordEncoder.encode(password));        user.setRole("ROLE_USER");        when(userRepository.findByNameOrEmail(anyString(), anyString())).thenReturn(user);        // Mock JWT token generation        when(jwtUtil.generateToken(anyString(), anyString(), anyString(), anyString()))                .thenReturn("mocked_jwt_token");        // Prepare login credentials        AuthDto authDto = new AuthDto();        authDto.setUserName(username);        authDto.setPassword(password);        // Perform login        String token = userService.login(authDto);        // Assert the result        assertNotNull(token);        assertEquals("mocked_jwt_token", token);    }}    */            @Test    public void testGetUser_Success() {        // Mock UserRepository to return a user with the given username        String username = "testuser";        User user = new User();                user.setName(username);        user.setEmail("test@example.com");        user.setPassword("password");        user.setRole("ROLE_USER");                when(userRepository.findByName(user.getName())).thenReturn(user);        // Call the method to be tested        UserDto userDto = userService.getUser(username);        // Assert the result        assertNotNull(userDto);        assertEquals(user.getId(), userDto.getId());        assertEquals(user.getName(), userDto.getName());        assertEquals(user.getEmail(), userDto.getEmail());        assertEquals(user.getRole(), userDto.getRole());    }    @Test    public void testGetUser_UserNotFound() {        // Mock UserRepository to return null for a given username        String username = "nonexistentuser";        when(userRepository.findByName(username)).thenReturn(null);        // Assert exception        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {            userService.getUser(username);        });        // Verify the exception message        assertEquals("User with username not found", exception.getMessage());    }    
}