package com.vaadin.example.views.user;
import com.vaadin.example.data.entity.User;
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


/**
 * A simple Vaadin View class that shows all Movies in a database.
 * <p>
 * See {@link UserService} for details on the database, and
 * {@link ApplicationServiceInitListener} for adding more demo data.
 */

@PageTitle("")
@Route(value="", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class UserView extends VerticalLayout {

    private final UserService userService;
    private Grid<User> grid = new Grid<>(User.class, false);

    Button add = new Button("Add");
    Button delete = new Button("Delete");

    @Autowired
    public UserView(UserService userService) {
        this.userService = userService;
        setSizeFull();

       // add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        HorizontalLayout horizontalLayout = new HorizontalLayout(add, delete);

        setHorizontalComponentAlignment(Alignment.END, horizontalLayout);

        configureGrid();

        add(grid, horizontalLayout);
    }

    private void configureGrid() {

        grid.addClassName("ContactList");
        setSizeFull();
        grid.addColumn(User::getName).setHeader("Firstname").setSortable(true).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getNachname).setHeader("Lastname").setSortable(true);
        grid.addColumn(User::getUsername).setHeader("Emails").setSortable(true);
        grid.addColumn(User::getRecht).setHeader("Emails").setSortable(true);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setItems(userService.getUser());
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            Set<User> selected = event.getAllSelectedItems();
            Notification.show(((Set<?>) selected).size() + " items selected");
        });
    }

}