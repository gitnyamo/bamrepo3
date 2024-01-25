package crud.bam.simplecrudoperationspringproject.service;

import crud.bam.simplecrudoperationspringproject.model.User;
import crud.bam.simplecrudoperationspringproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
