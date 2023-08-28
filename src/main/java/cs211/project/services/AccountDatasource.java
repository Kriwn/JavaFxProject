package cs211.project.services;

import cs211.project.models.NormalUserList;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class AccountDatasource implements Datasource<NormalUserList> {
    private String directoryName;
    private String fileName;

    public AccountDatasource(String directoryName, String fileName){
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
    public NormalUserList readData() {
        NormalUserList users = new NormalUserList();
        String filePath = directoryName = File.separator + fileName;
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

                String username = data[0].trim();
                String name = data[1].trim();
                String password = data[2].trim();
                //อาจจะมีตัวเพิ่ม

                users.addNewUser(username,name,password);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void writeData(NormalUserList data) {

    }
}
