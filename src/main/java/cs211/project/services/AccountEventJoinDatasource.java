package cs211.project.services;

import cs211.project.models.Account;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountEventJoinDatasource implements Datasource<HashMap<Integer, ArrayList<Integer>>>{

    private String directoryName;
    private String fileName;

    public AccountEventJoinDatasource(String directoryName, String fileName){
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
    public HashMap<Integer, ArrayList<Integer>> readData() {
        HashMap<Integer,ArrayList<Integer>> account_event = new HashMap<>();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการอ่านไฟล์
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
            // ใช้ while loop เพื่ออ่านข้อมูลรอบละบรรทัด
            while ( (line = buffer.readLine()) != null ){
                // ถ้าเป็นบรรทัดว่าง ให้ข้าม
                if (line.equals("")) continue;

                // แยกสตริงด้วย ,
                String[] data = line.split(",");

                // อ่านข้อมูลตาม index แล้วจัดการประเภทของข้อมูลให้เหมาะสม
                Integer account_Id = Integer.parseInt(data[0]);
                ArrayList<Integer> all_event_Id = new ArrayList<>();
                String[] event = data[1].split("\\|");
                for(String events: event){
                    all_event_Id.add(Integer.parseInt(events));
                }
                account_event.put(account_Id,all_event_Id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return account_event;
    }

    @Override
    public void writeData(HashMap<Integer, ArrayList<Integer>> data) {
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
            // สร้าง csv ของ user และเขียนลงในไฟล์ทีละบรรทัด
            data.forEach((key,value) ->{
                String event = "";
                for(Integer a :value){
                    event += a+"|";
                }
                String line = key + ","+ event;
                try {
                    buffer.append(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    buffer.append("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
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
