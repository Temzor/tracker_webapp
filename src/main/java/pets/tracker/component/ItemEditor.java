package pets.tracker.component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pets.tracker.domain.Item;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pets.tracker.repo.ItemRepo;



@SpringComponent
@UIScope
public class ItemEditor extends VerticalLayout implements KeyNotifier {
    private final ItemRepo itemRepo;

    private Item item;

    private TextField created = new TextField("", "Company name");
    private TextField companyName = new TextField("", "Company name");
    private TextField status = new TextField("", "Status");
    private IntegerField sla = new IntegerField("", "SLA");
    private TextField description = new TextField("", "Description");

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

        add(created, companyName, status, sla, description, buttons);

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