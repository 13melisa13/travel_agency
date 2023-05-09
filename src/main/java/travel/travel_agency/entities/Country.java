package travel.travel_agency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_country")
public class Country {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
//    @JsonIgnore
//    @ToString.Include
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<City> cities;



    public Country(String name) {
        this.name = name;
    }
//    @Override
//    public String toString() {
//        if (cities != null)
//        return "Country{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", cities=" + cities +
//                '}';
//        else return "Country{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                "}";
//    }
}
