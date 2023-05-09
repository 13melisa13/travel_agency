package travel.travel_agency.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

//    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    @ManyToOne//(fetch = FetchType.LAZY)
    private City city;

    @JsonIgnore
    @ToString.Include
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

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", isPresentSea=" + isPresentSea +
                ", typeOfBeach=" + typeOfBeach +
                ", price=" + price +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", city=" + city +
                ", boughtBy=" + boughtBy +
                '}';
    }
}
