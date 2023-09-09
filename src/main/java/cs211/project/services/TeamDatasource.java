package cs211.project.services;

import cs211.project.models.Staff;
import cs211.project.models.StaffList;
import cs211.project.models.Team;
import cs211.project.models.TeamList;
import javafx.scene.shape.Circle;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TeamDatasource implements Datasource<TeamList>{
    private String directoryName;
    private String fileName;
    public TeamDatasource(String directoryName, String fileName){
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
    public TeamList readData(){
        TeamList teams = new TeamList();
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
                String id = data[0];
                String name = data[1];
                int capacity = Integer.parseInt(data[2]);
                String openDate = data[3];
                String openTime = data[4];
                String closeDate = data[5];
                String closeTime = data[6];

                Datasource<StaffList> datasource = new StaffDatasource(name, "staffs");
                StaffList staffList = datasource.readData();
                teams.addNewTeam(id, name, capacity, openDate, openTime, closeDate, closeTime, staffList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teams;
    }

    @Override
    public void writeData(TeamList data) {
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
            for (Team team : data.getTeams()) {
                String line = team.getTeamName() + "," + team.getCapacity() + "," + team.getOpenDate() + "," + team.getOpenTime() + "," + team.getCloseDate() + "," + team.getCloseTime() + "," + team.getStaffList();
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
