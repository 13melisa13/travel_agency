package travel.travel_agency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_city")
public class City {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @JsonIgnore
    @ToString.Include
    @ManyToOne//(fetch = FetchType.LAZY)
    private Country country;

    @JsonIgnore
    @OneToMany(/*fetch = FetchType.LAZY, */mappedBy = "city")
    private List<Tour> tours;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

//    @Override
//    public String toString() {
//        return "City{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", country=" + country +
//                '}';
//    }

}
