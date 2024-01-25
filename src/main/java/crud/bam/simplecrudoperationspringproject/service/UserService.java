package crud.bam.simplecrudoperationspringproject.service;

import crud.bam.simplecrudoperationspringproject.exception.UserNotFoundException;
import crud.bam.simplecrudoperationspringproject.model.User;
import crud.bam.simplecrudoperationspringproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers(){
        return userRepository.findAll();

    }

    public void save(User user){
        userRepository.save(user);
    }

    public User getUserById(Integer id){
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<User> userResult = userRepository.findById(id);
        if(userResult.isPresent()) {
            return userResult.get();
        }
        throw new UserNotFoundException("Could not find any user with ID "+ id);
    }
     public void delete(Integer id){
        Long count = userRepository.countById(id);
        if(count == null || count == 0) {
            throw  new UserNotFoundException("Could not find any user(s) with that ID "+id);
        }
        userRepository.deleteById(id);
     }
    public void update(User user) {
        Integer id = user.getId();
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " does not exist");
        }
        userRepository.save(user);
    }
}
