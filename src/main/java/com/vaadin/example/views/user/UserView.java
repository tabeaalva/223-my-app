package com.vaadin.example.views.user;
import com.vaadin.example.data.entity.DBUser;
import com.vaadin.example.data.service.UserService;
import com.vaadin.example.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.example.ApplicationServiceInitListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@PageTitle("user")
@Route(value="user", layout = MainLayout.class)
@RouteAlias(value = "user", layout = MainLayout.class)
@AnonymousAllowed
public class UserView extends VerticalLayout {

    private final UserService userService;
    private Grid<DBUser> grid = new Grid<>(DBUser.class, false);

    Button add = new Button("Add");
    Button delete = new Button("Delete");

    @Autowired
    public UserView(UserService userService) {
        this.userService = userService;
        setSizeFull();

        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        delete.addClickListener(e-> {
            Set<DBUser> selectedItems = grid.getSelectedItems();

            for (DBUser dbUser : selectedItems) {
                // Führen Sie die Löschaktion für jede ausgewählte Zeile durch, z.B. mit einem KundenService
                userService.deleteUser(dbUser.getBenutzerId());
                refreshGrid(grid);
            }
            // Aktualisieren Sie das Grid nach dem Löschen

        });
        HorizontalLayout horizontalLayout = new HorizontalLayout(add, delete);

        setHorizontalComponentAlignment(Alignment.END, horizontalLayout);

        configureGrid();

        add(grid, horizontalLayout);
    }

    private void configureGrid() {

        grid.addClassName("ContactList");
        setSizeFull();
        grid.addColumn(DBUser::getName).setHeader("Firstname").setSortable(true).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(DBUser::getNachname).setHeader("Lastname").setSortable(true);
        grid.addColumn(DBUser::getUsername).setHeader("Username").setSortable(true);
        grid.addColumn(DBUser::getRecht).setHeader("Emails").setSortable(true);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setItems(userService.getUser());
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            Set<DBUser> selected = event.getAllSelectedItems();
            Notification.show(((Set<?>) selected).size() + " items selected");
        });
    }

    public void refreshGrid(Grid<?> grid) {
        // Löschen des bestehenden Inhalts im Grid
        grid.setItems();
    }
}