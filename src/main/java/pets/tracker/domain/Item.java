package pets.tracker.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private com.vaadin.flow.component.datepicker.DatePicker created = new com.vaadin.flow.component.datepicker.DatePicker();

//    private Timestamp created;
    private String companyName;
    private String status;
    private Integer sla = null;
    private String description;
}
