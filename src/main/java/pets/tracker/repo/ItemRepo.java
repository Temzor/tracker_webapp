package pets.tracker.repo;

import pets.tracker.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
    @Query("from Item i "
            + " where concat(i.created, ' ', i.companyNa" +
            "me, ' ', i.status, ' ',i.sla , ' ', i.description) "
            +   "like concat('%', :name, '%') ")

    List<Item> findByName(@Param("name") String name);

}
