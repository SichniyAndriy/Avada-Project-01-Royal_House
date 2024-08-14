package edu.avada.course.model.admindto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class AdminAddressDto implements Serializable {
    private Long id;
    private String city;
    private String street;
    private String houseNumber;

    @Override
    public String toString() {
        return city + ", " + street + ", " + houseNumber;
    }
}
