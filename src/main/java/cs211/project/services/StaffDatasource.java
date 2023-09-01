package cs211.project.services;


import cs211.project.models.Staff;
import cs211.project.models.StaffList;
import javafx.scene.shape.Circle;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StaffDatasource implements Datasource<StaffList>{
    private String directoryName;
    private String fileName;
    public StaffDatasource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public StaffList readData(){
        StaffList staffs = new StaffList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InputStreamReader inputStreamReader = new InputStreamReader(
                fileInputStream,
                StandardCharsets.UTF_8
        );
        BufferedReader buffer = new BufferedReader(inputStreamReader);

        String line = "";
        try {
            while ( (line = buffer.readLine()) != null ){
                if (line.equals("")) continue;

                String[] data = line.split(",");

                String username = data[0];
                String role = data[1];
                String status = data[2];
                Circle circle = new Circle(100);
                String imagePath = data[3];

                staffs.addStaff(username,role,status,circle,imagePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return staffs;
    }
    @Override
    public void writeData(StaffList data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream,
                StandardCharsets.UTF_8
        );
        BufferedWriter buffer = new BufferedWriter(outputStreamWriter);

        try {
            for (Staff staff : data.getStaffs()) {
                String line = staff.getUsername() + "," + staff.getRole() + "," + staff.getStatus() + "," + staff.getImagePath();
                buffer.append(line);
                buffer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.flush();
                buffer.close();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
