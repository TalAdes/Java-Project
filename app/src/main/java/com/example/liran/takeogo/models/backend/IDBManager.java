package com.example.liran.takeogo.models.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.liran.takeogo.models.entities.User;

/**
 * Created by liran on 08/11/2017.
 */

public interface IDBManager {

    long addClient(ContentValues values) throws Exception;
    boolean removeClient(long id);
    boolean updateClient(ContentValues values);
    boolean searchClient(ContentValues values);

    long addCarModel(ContentValues values) throws Exception;
    boolean removeCarModle(long id);
    boolean updateCarModel(ContentValues values);

    long addCar(ContentValues values) throws Exception;
    boolean removeCar(long id);
    boolean updateCar(ContentValues values);

    long addBranch(ContentValues values) throws Exception;
    boolean removeBranch(long id);
    boolean updateBranch(ContentValues values);

    void addUser(User user) throws Exception;

    Cursor getClients() throws Exception;
    Cursor getCars();
    Cursor getBranches() throws Exception;
    Cursor getCarModels() throws Exception;
    //List<CarModel> getCarModels();
    //List<Client> getClients();
    //List<Branch> getBranches();
    //List<Car> getCars();







}
