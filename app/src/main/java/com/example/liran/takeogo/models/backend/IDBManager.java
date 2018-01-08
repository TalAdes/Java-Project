package com.example.liran.takeogo.models.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.liran.takeogo.models.entities.User;

import java.util.List;

/**
 * Created by liran on 08/11/2017.
 */

public interface IDBManager {

    String addClient(ContentValues values);
    boolean removeClient(long id);
    boolean updateClient(ContentValues values);
    boolean searchClient(ContentValues values);

    String addCarModel(ContentValues values);
    boolean removeCarModle(long id);
    boolean updateCarModel(ContentValues values);

    long addCar(ContentValues values) throws Exception;
    boolean removeCar(long id);
    boolean updateCar(ContentValues values);

    String addBranch(ContentValues values);
    boolean removeBranch(long id);
    boolean updateBranch(ContentValues values);

    void addUser(User user) throws Exception;

    Cursor getClients() throws Exception;
    Cursor getCars() throws Exception;
    Cursor getBranches() throws Exception;
    Cursor getCarModels() throws Exception;

    Cursor getCarByModels(String selected) throws Exception;

    List<String> getModelName() throws Exception;
    //List<CarModel> getCarModels();
    //List<Client> getClients();
    //List<Branch> getBranches();
    //List<Car> getCars();







}
