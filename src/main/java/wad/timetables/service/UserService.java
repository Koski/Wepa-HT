package wad.timetables.service;

import java.util.List;
import wad.timetables.domain.BusStop;
import wad.timetables.domain.User;

public interface UserService {
    User create(User user);
    User getUserById(Integer id);
    User getUserByName(String name);
    void delete(Integer id);
    User addFavStop(User user, Long stopCode);
    List<Long> listStopCodes(Integer userId);
    boolean authenticateUser(User user);
    List<BusStop> getCurrentStopInfo(User user);
}
