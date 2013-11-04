package wad.timetables.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.timetables.domain.User;
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByName(String name); 
}
