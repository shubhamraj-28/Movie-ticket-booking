package com.bookmyshow.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    private String name;
    private String location;
    private int capacity;
    private String screenType;

    @OneToMany(mappedBy = "theatre",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Show> show;

    public void setTheatreName(Object o) {
    }

    public void setTheatreLocation(Object o) {

    }

    public void setTheatreCapacity(Object theatreCapacity) {
    }

    public void setTheatreScreenType(Object theatreScreenType) {
    }

    public int getTheatreCapacity() {
        return capacity;
    }
}
