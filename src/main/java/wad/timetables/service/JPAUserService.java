package wad.timetables.service;

import java.util.ArrayList;
import java.util.List;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.timetables.domain.User;
import wad.timetables.repository.UserRepository;

@Service
public class JPAUserService implements UserService{
    
    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional(readOnly=false)
    public User create(User user) {
        User u = new User();
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        u.setCodesOfFavStops(new ArrayList<String>());
        return userRepo.save(u);
    }

    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id) {
        userRepo.delete(id);
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<String> listStopCodes(Integer userId) {
        return userRepo.findOne(userId).getCodesOfFavStops();
    }
    
    @Override
    @Transactional(readOnly=true)
    public User getUserById(Integer id) {
        return userRepo.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String name) {
        return userRepo.findUserByName(name);
    }

    @Override
    public User addFavStop(User user, String stopCode) {
        User u = user;
        u.getCodesOfFavStops().add(stopCode);
        return userRepo.save(u);
    }

    @Override
    public boolean authenticateUser(User user) {
        User u = getUserByName(user.getName());
        if(u!=null && user.getPassword().equals(u.getPassword())){
            return true;
        }
        return false;
    }
    
}
