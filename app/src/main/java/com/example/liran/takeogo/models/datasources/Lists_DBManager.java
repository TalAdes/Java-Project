package com.example.liran.takeogo.models.datasources;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.liran.takeogo.models.backend.IDBManager;
import com.example.liran.takeogo.models.backend.TakeGoConst;
import com.example.liran.takeogo.models.entities.Branch;
import com.example.liran.takeogo.models.entities.Car;
import com.example.liran.takeogo.models.entities.CarModel;
import com.example.liran.takeogo.models.entities.Client;
import com.example.liran.takeogo.models.entities.User;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by liran on 08/11/2017.
 */

public class Lists_DBManager implements IDBManager {

    static List<Car> Cars;
    static List<Branch> Branchs;
    static List<CarModel> Models;
    static List<Client> Clients;
    static List<User> Users;


    static{
        Cars = new ArrayList<>();
        Branchs = new ArrayList<>();
        Models = new ArrayList<>();
        Clients = new ArrayList<>();
        Users = new ArrayList<>();
    }

    @Override    public String addClient(ContentValues values) {
        String str = TakeGoConst.httpPost("http://tades.vlab.jct.ac.il/setClients.php?",values.valueSet());
        return str;
    }
    @Override    public boolean removeClient(long id) {
        Client clientToremove = null;
        for (Client item : Clients){
            if( item.getId() == id){
                clientToremove = item;
                break;
            }
        }
        return Clients.remove(clientToremove);
    }
    @Override    public boolean updateClient(ContentValues values) {
        Client client = TakeGoConst.ContentValuesToClient(values);
        for (int i = 0; i < Clients.size(); i++) {
            if (Clients.get(i).getId() == client.getId()) {
                Clients.get(i).setFname(client.getFname());
                Clients.get(i).setLname(client.getLname());
                Clients.get(i).setPhoneNum(client.getPhoneNum());
                Clients.get(i).setEmail(client.getEmail());
                Clients.get(i).setNumCredit(client.getNumCredit());
                return true;
            }
        }
        return false;


    }
    @Override    public boolean searchClient(ContentValues values) {
        Client client = TakeGoConst.ContentValuesToClient(values);
        for (Client item : Clients) {
            if (item.getId() == client.getId())
                return true;
        }
        return false;


    }

    @Override    public String addCarModel(ContentValues values) {
        String str = TakeGoConst.httpPost("http://tades.vlab.jct.ac.il/setCarModels.php?",values.valueSet());
        return str;
    }
    @Override    public boolean removeCarModle(long id) {
        CarModel modelToremove = null;
        for (CarModel item : Models) {
            if (item.getIdModel() == id) {
                modelToremove = item;
                break;
            }
        }
        return Models.remove(modelToremove);
    }
    @Override    public boolean updateCarModel(ContentValues values) {
        CarModel model = TakeGoConst.ContentValuesToCarModel(values);
        for (int i = 0; i < Models.size(); i++){
            if (Models.get(i).getIdModel() == model.getIdModel()) {
                Models.get(i).setNameModel(model.getNameModel());
                return true;
            }
        }
        return false;
    }

    @Override    public long addCar(ContentValues values) throws Exception {
        Car car = TakeGoConst.ContentValuesToCar(values);
        for (Car item : Cars) {
            if(item.getIdCar() == car.getIdCar())
                throw new Exception("This car already exists!.");
        }
        Cars.add(car);
        return car.getIdCar();
    }
    @Override    public boolean removeCar(long id) {
        Car carToremove = null;
        for(Car item : Cars){
            if(item.getIdCar() == id){
                carToremove = item;
                break;
            }
        }
        return Cars.remove(carToremove);
    }
    @Override    public boolean updateCar(ContentValues values) {
        Car car = TakeGoConst.ContentValuesToCar(values);
        for(int i=0; i < Cars.size(); i++){
            if(Cars.get(i).getIdCar() == car.getIdCar()){
                Cars.get(i).setKilometer(car.getKilometer());
                Cars.get(i).setIdBranch(car.getIdBranch());
                return true;
            }
        }
        return false;
    }

    @Override    public String addBranch(ContentValues values) {
        String str = TakeGoConst.httpPost("http://tades.vlab.jct.ac.il/setBranches.php?",values.valueSet());
        return str;
    }
    @Override    public boolean removeBranch(long id) {
        Branch branchToremove = null;
        for(Branch item : Branchs){
            if(item.getIdBranch() == id){
                branchToremove = item;
                break;
            }
        }
        return Branchs.remove(branchToremove);
    }
    @Override    public boolean updateBranch(ContentValues values) {
        Branch branch = TakeGoConst.ContentValuesToBranch(values);
        for(int i=0; i < Branchs.size(); i++){
            if(Branchs.get(i).getIdBranch() == branch.getIdBranch()){
                Branchs.get(i).setNumParking(branch.getNumParking()) ;
                Branchs.get(i).setStreet(branch.getStreet());
                Branchs.get(i).setCity(branch.getCity());
                Branchs.get(i).setNumApart(branch.getNumApart());
                return true;
            }
        }
        return false;
    }
    @Override    public void addUser(User user) throws Exception {
        for(User item : Users){
            if(item.getName() == user.getName())
                throw new Exception("This user already exist!.");
        }
        Users.add(user);
    }


    //@Override public List<Branch> getBranches() { return Branchs;}
    //@Override public List<Car> getCars() { return Cars;}
    //@Override public List<CarModel> getCarModels() { return Models;}
    //@Override public List<Client> getClients() { return Clients;}

    @Override    public Cursor getCarModels() throws Exception {return TakeGoConst.CarModelListToCursor();}
    @Override    public Cursor getClients() throws Exception {return TakeGoConst.ClientListToCursor();}
    @Override    public Cursor getBranches() throws Exception {return TakeGoConst.BranchListToCursor();}
    @Override    public Cursor getCars() {
        return null;

    }
    //@Override    public Cursor getCars() {return TakeGoConst.CarListToCursor(Cars);}
}
