package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.repository.EventRepository;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML private MFXTableView<Event> table1;
    @FXML private MFXTableView<Event> table2;
    private EventList eventList;
    private EventRepository eventRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        setTable(eventList,table1);
        setTable(eventList,table2);
    }
    private void setTable(EventList eventList, MFXTableView<Event> tableView) {
        MFXTableColumn<Event> nameColumn = new MFXTableColumn<>("Name", false, Comparator.comparing(Event::getName));
        MFXTableColumn<Event> countMemberColumn = new MFXTableColumn<>("Count member", false, Comparator.comparing(Event::getCountMember));
        MFXTableColumn<Event> dateStartColumn = new MFXTableColumn<>("Date Start", false, Comparator.comparing(Event::getDateStart));
        MFXTableColumn<Event> dateEndColumn = new MFXTableColumn<>("Date End", false, Comparator.comparing(Event::getDateEnd));

        nameColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getName));
        countMemberColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getCountMember));
        dateStartColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getDateStart));
        dateEndColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getDateEnd));
        tableView.getTableColumns().clear();
        tableView.getTableColumns().addAll(nameColumn, countMemberColumn , dateStartColumn , dateEndColumn);

        for(Event event: eventList.getEvents()){
            tableView.getItems().add(event);
        }
    }
}
