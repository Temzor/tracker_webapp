package pets.tracker.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import pets.tracker.domain.Item;
import pets.tracker.repo.ItemRepo;
import pets.tracker.component.ItemEditor;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
class ItemList extends VerticalLayout {
    private final ItemRepo itemRepo;

    private final ItemEditor itemEditor;

    private Grid<Item> itemGrid= new Grid<>(Item.class);
    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("New application", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewButton);

    @Autowired
    public ItemList(ItemRepo itemRepo, ItemEditor itemEditor) {
        this.itemRepo = itemRepo;
        this.itemEditor = itemEditor;

        filter.setPlaceholder("Type to filter");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));

        add(toolbar, itemGrid, itemEditor);

        itemGrid
                .asSingleSelect()
                .addValueChangeListener(e -> itemEditor.editItem(e.getValue()));


        addNewButton.addClickListener(e -> itemEditor.editItem(new Item()));

        itemEditor.setChangeHandler(() -> {
            itemEditor.setVisible(false);
            fillList(filter.getValue());
        });

        fillList("");
    }

    private void fillList(String name) {
        if (name.isEmpty()) {
            itemGrid.setItems(this.itemRepo.findAll());
        } else {
            itemGrid.setItems(this.itemRepo.findByName(name));
        }
    }
}