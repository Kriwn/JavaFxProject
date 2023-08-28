package cs211.project.services;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.NormalUserList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class EventDatasource implements Datasource<EventList>{
    private String directoryName;
    private String fileName;

    public EventDatasource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
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
    public EventList readData() {
        EventList events = new EventList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader buffer = new BufferedReader(inputStreamReader);

        String line = "";
        try {
            while ( (line = buffer.readLine()) != null ){
                if (line.equals("")) continue;

                String[] data = line.split(",");

                String name = data[0].trim();
                String details = data[1].trim();
                String dateStart = data[2].trim();
                String dateEnd = data[3].trim();
                String timeStart = data[4].trim();
                String timeEnd = data[5].trim();
                String capacity = data[6];
                //อาจจะมีตัวเพิ่ม

                events.addNewEvent(name,details,dateStart,dateEnd,timeStart,timeEnd,capacity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public void writeData(EventList data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการเขียนไฟล์
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
            // สร้าง csv ของ Student และเขียนลงในไฟล์ทีละบรรทัด
            for (Event event : data.getEvents()) {
                String line = event.getName() + "," + event.getDetail() + "," + event.getDateStart() + "," + event.getDateEnd() + "," + event.getTimeStart() + "," + event.getTimeEnd() + "," + event.getCountMember() + "," + event.getMaxMember();
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
