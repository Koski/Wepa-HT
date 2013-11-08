package wad.timetables.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.timetables.domain.BusStop;
import wad.timetables.domain.Departure;
import wad.timetables.domain.User;
import wad.timetables.repository.UserRepository;

@Service
public class JPAUserService implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RESTStopService stopService;

    @Override
    @Transactional(readOnly = false)
    public User create(User user) {
        if(userRepo.findUserByName(user.getName())!=null) {
            return null;
        }
        User u = new User();
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        u.setCodesOfFavStops(new ArrayList<Long>());
        return userRepo.save(u);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        userRepo.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> listStopCodes(Integer userId) {
        return userRepo.findOne(userId).getCodesOfFavStops();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        if (id != null) {
            return userRepo.findOne(id);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String name) {
        return userRepo.findUserByName(name);
    }

    @Override
    public User addFavStop(User user, Long stopCode) {
        User u = user;
        if (!u.getCodesOfFavStops().contains(stopCode)) {
            u.getCodesOfFavStops().add(stopCode);
            return userRepo.save(u);
        }
        return u;
    }

    @Override
    public boolean authenticateUser(User user) {
        User u = getUserByName(user.getName());
        if (u != null && user.getPassword().equals(u.getPassword())) {
            return true;
        }
        return false;
    }

    public List<BusStop> listStops(User user) {
        List<BusStop> busStopList = new ArrayList<BusStop>();
        for (Long code : user.getCodesOfFavStops()) {
            busStopList.add(stopService.getStop(code));
        }
        return busStopList;
    }

    @Override
    public List<BusStop> getCurrentStopInfo(User user) {
        List<BusStop> stops = new ArrayList<BusStop>();
        for (Long code : user.getCodesOfFavStops()) {
            BusStop stop =stopService.getStop(code);
            stop.parseAllDepartures();
            stops.add(stop);
        }
        return stops;
    }
}
