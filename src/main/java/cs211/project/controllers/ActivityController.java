package cs211.project.controllers;

import cs211.project.models.Activity;
import cs211.project.models.ActivityList;
//import cs211.project.services.ActivityDatasource;
import cs211.project.services.Datasource;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ActivityController {

    @FXML private TableView<Activity> activityTableView;
    private ActivityList activityList;

    private Datasource<ActivityList> datasource;

    @FXML
    public void initialize() {
//        datasource = new ActivityDatasource("data", "activity.csv");
        activityList = datasource.readData();
        showTable(activityList);

    }

    private void showTable(ActivityList activityList) {
        // กำหนด column ให้มี title ว่า ID และใช้ค่าจาก attribute id ของ object Student
        TableColumn<Activity, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก attribute name ของ object Student
        TableColumn<Activity, String> detailColumn = new TableColumn<>("Detail");
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));


        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        activityTableView.getColumns().clear();
        activityTableView.getColumns().add(nameColumn);
        activityTableView.getColumns().add(detailColumn);

        activityTableView.getItems().clear();

        // ใส่ข้อมูล Student ทั้งหมดจาก studentList ไปแสดงใน TableView
        for (Activity activity: activityList.getActivity()) {
            activityTableView.getItems().add(activity);
        }
    }
}
