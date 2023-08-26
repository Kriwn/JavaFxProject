package cs211.project.services;

import cs211.project.models.AccountList;

public interface Datasource<T> {

    T readData();
    void writeData(T data);

    void writeData(AccountList data);
}
