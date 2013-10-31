package wad.timetables.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    @NotBlank
    @Length(min = 4, max = 15)
    @Column(unique = true, name="name")
    private String name;
    
    @NotBlank
    @Length(min = 5, max = 25)
    @Column(name="password")
    private String password;
    
    @Column(name = "CodesOfFavStops")
    private List<String> CodesOfFavStops;

    public User() {
    }

    public User(Integer id, String name, String password, List<String> CodesOfFavStops) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.CodesOfFavStops = CodesOfFavStops;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getCodesOfFavStops() {
        return CodesOfFavStops;
    }

    public void setCodesOfFavStops(List<String> CodesOfFavStops) {
        this.CodesOfFavStops = CodesOfFavStops;
    }

    
    
    
}
