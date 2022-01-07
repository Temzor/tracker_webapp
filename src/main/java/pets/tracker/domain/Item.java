package pets.tracker.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    Timestamp created = Timestamp.valueOf(LocalDateTime.now());
    private String companyName;
    private String status;
    private Integer sla = null;
    private String phoneNumber;
    private String description;
}
