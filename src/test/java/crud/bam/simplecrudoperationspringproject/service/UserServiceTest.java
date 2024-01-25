package crud.bam.simplecrudoperationspringproject.service;

import crud.bam.simplecrudoperationspringproject.exception.UserNotFoundException;
import crud.bam.simplecrudoperationspringproject.model.User;
import crud.bam.simplecrudoperationspringproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.listAll();

        assertEquals(2, result.size());
    }

    @Test
    void testSaveUser() {
        User user = new User();
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1);
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(1)).thenReturn(optionalUser);

        User retrievedUser = userService.get(1);

        assertNotNull(retrievedUser);
        assertEquals(1, retrievedUser.getId());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.get(1));
    }




}