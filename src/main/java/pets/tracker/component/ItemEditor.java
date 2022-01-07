package pets.tracker.component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pets.tracker.domain.Item;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pets.tracker.repo.ItemRepo;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringComponent
@UIScope
public class ItemEditor extends VerticalLayout implements KeyNotifier {
    private final ItemRepo itemRepo;

    private Item item;


    private TextField companyName = new TextField("", "Company name");
    ComboBox<Integer> sla = new ComboBox<>();
    ComboBox<String> force = new ComboBox<>();
    TextField phoneNumber = new TextField("", "Phone number");
    TextArea description = new TextArea("", "Description");


    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");
    private HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);

    private Binder<Item> binder = new Binder<>(Item.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public ItemEditor(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;

        DateTimePicker created = new DateTimePicker();
        created.setLabel("Message received");
        created.setStep(Duration.ofSeconds(1));
        created.setValue(LocalDateTime.now());

       phoneNumber.setPattern("^[+][0-9]{1}?[(]?[0-9]{3}[)]?[0-9]{3}[-s.]?[0-9]{4,6}$");
       phoneNumber.setHelperText("Format: +7(123)456-7890");

        description.setLabel("Description");
        description.setWidthFull();
        description.setMaxLength(900);
        description.setValueChangeMode(ValueChangeMode.EAGER);
        description.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + 900);
        });
        description.setValue("Great job. This is excellent!");


        force.setItems("Trivial", "Normal", "Major", "Critical", "Blocker");
        force.setPlaceholder("Select force");
        force.setClearButtonVisible(false);

        sla.setItems(1, 3, 6, 12, 24, 72);
        sla.setPlaceholder("Select SLA in hours");
        sla.setClearButtonVisible(false);

        add(created, companyName, force, sla, phoneNumber, description, buttons);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());



        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editItem(item));
        setVisible(false);
    }

    private void save() {
        itemRepo.save(item);
        changeHandler.onChange();
    }

    private void delete() {
        itemRepo.delete(item);
        changeHandler.onChange();
    }

    public void editItem(Item item) {
        if (item == null) {
            setVisible(false);
            return;
        }

        if (item.getId() != null) {
            this.item = itemRepo.findById(item.getId()).orElse(item);
        } else {
            this.item = item;
        }

        binder.setBean(this.item);

        setVisible(true);

        companyName.focus();

    }
}