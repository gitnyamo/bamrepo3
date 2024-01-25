package crud.bam.simplecrudoperationspringproject.repository;

import crud.bam.simplecrudoperationspringproject.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("margaretr@gmail.com");
        user.setPassword("robi");
        user.setFirstName("Margaret");
        user.setLastName("Nyamohanga");
        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAddNew2() {
        User user = new User();
        user.setEmail("alen@gmail.com");
        user.setPassword("ale");
        user.setFirstName("Alex");
        user.setLastName("Stevenson");
        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = userRepository.findAll();
        assertThat(users).hasSizeGreaterThan(0);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 6;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello2000");
        userRepository.save(user);

        User updateUser = userRepository.findById(userId).get();


        // use the code commented out below
        /*        Integer userId = 2;
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        user.setPassword("hello2000");
        userRepository.save(user);

        User updateUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        assertThat(updateUser.getPassword()).isEqualTo("hello2000");
    */

    }

    @Test
    public void testGet(){
        Integer userId = 7;
        Optional<User> optionalUser = userRepository.findById(userId);
        assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        assertThat(optionalUser).isNotPresent();
    }
}



