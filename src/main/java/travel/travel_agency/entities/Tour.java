package travel.travel_agency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_tour")
public class Tour {
    @Id
    @GeneratedValue
    private Integer id;
    private boolean isPresentSea;
    private TypeOfBeach typeOfBeach;
    private Integer price;
    private Date dateFrom;
    private Date dateTo;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User boughtBy;

    public Tour(boolean isPresentSea, TypeOfBeach typeOfBeach, Integer price, Date dateFrom, Date dateTo, City city) {
        this.isPresentSea = isPresentSea;
        this.typeOfBeach = typeOfBeach;
        this.price = price;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.city = city;
    }
}
