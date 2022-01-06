package pets.tracker.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    Date created = java.sql.Date.valueOf(LocalDate.now());
    private String companyName;
    private String status;
    private Integer sla = null;
    private String description;
}
