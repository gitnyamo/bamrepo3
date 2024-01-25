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
}
