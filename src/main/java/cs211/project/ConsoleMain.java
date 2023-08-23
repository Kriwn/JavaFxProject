package cs211.project;

import cs211.project.models.Account;
import cs211.project.models.NormalUser;
import cs211.project.models.NormalUserList;
import cs211.project.services.AccountDatasource;
import cs211.project.services.Datasource;

public class ConsoleMain {
    public static void main(String[] args) {
        Datasource<NormalUserList> datasource;
        datasource = new AccountDatasource("data","account.csv");
        NormalUserList list = datasource.readData();
        NormalUser user = list.findUserByUsername("Taro0806");
        System.out.println(user);
    }
}
