package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.repository.EventRepository;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableRow;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.EnumFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML private MFXPaginatedTableView<Event> paginated1;
    @FXML private MFXPaginatedTableView<Event> paginated2;
    private EventList eventList;
    private EventRepository eventRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        setupPaginated(eventList,paginated1);
        setupPaginated(eventList,paginated2);
    }
    private void setupPaginated(EventList eventList, MFXPaginatedTableView<Event> paginated) {
        MFXTableColumn<Event> nameColumn = new MFXTableColumn<>("Name", false, Comparator.comparing(Event::getName));
        MFXTableColumn<Event> detailsColumn = new MFXTableColumn<>("Details", false, Comparator.comparing(Event::getDetail));
        MFXTableColumn<Event> countMemberColumn = new MFXTableColumn<>("Count member", false, Comparator.comparing(Event::getCountMember));
        MFXTableColumn<Event> dateStartColumn = new MFXTableColumn<>("Date Start", false, Comparator.comparing(Event::getDateStart));
        MFXTableColumn<Event> dateEndColumn = new MFXTableColumn<>("Date End", false, Comparator.comparing(Event::getDateEnd));

        nameColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getName));
        detailsColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getDetail));
        countMemberColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getCountMember));
        dateStartColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getDateStart));
        dateEndColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getDateEnd));
        paginated.getTableColumns().clear();
        paginated.getTableColumns().addAll(nameColumn, detailsColumn, countMemberColumn, dateStartColumn, dateEndColumn);

        for(Event event: eventList.getEvents()){
            paginated.getItems().add(event);
        }
    }
}
