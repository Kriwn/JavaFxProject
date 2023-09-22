package cs211.project.repository;

import cs211.project.models.AccountList;
import cs211.project.models.User;
import cs211.project.services.AccountDatasource;
import cs211.project.services.Datasource;

public class AccountRepository {
    private AccountList accounts;
    private Datasource<AccountList> data;

    public AccountRepository(){
        data = new AccountDatasource("data","account.csv");
        accounts = data.readData();
    }

    public User findUserByUsername(String username){
        return (User)accounts.findUserByUsername(username);
    }

    public void save(AccountList accounts){
        data.writeData(accounts);
    }

    public void signUp(String username, String name, String password){
        accounts.signUp(username,name,password);
        data.writeData(accounts);
    }

    public void update(User user){

    }

    public void delete(String username){

    }

    public AccountList getAccounts(){
        return accounts;
    }
}
