package cs211.project.services;

import cs211.project.models.NormalUserList;

import java.io.File;
import java.io.IOException;

public class NormalUserDatasource implements Datasource<NormalUserList> {
    private String directoryName;
    private String fileName;

    public NormalUserDatasource(String directoryName,String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
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
        return null;
    }

    @Override
    public void writeData(NormalUserList data) {

    }
}
