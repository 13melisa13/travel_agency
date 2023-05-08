package travel.travel_agency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_country")
public class Country {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<City> cities;

    public Country(String name) {
        this.name = name;
    }
}
