package com.example.liran.takeogo.models.datasources;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

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

    @Override    public String addCar(ContentValues values) {
        String str = TakeGoConst.httpPost("http://tades.vlab.jct.ac.il/setCars.php?",values.valueSet());
        return str;
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



    @Override public Cursor getCarByModels(String selected) throws Exception {
        Cursor allCars = getCars();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]
                {
                        TakeGoConst.CarConst.ID_BRANCH,
                        TakeGoConst.CarConst.ID_TYPE_MODEL,
                        TakeGoConst.CarConst.KILLOMETER,
                        TakeGoConst.CarConst.ID_CAR,
                        TakeGoConst.CarConst.MODEL_NAME,
                });

        allCars.moveToFirst();
        while (!allCars.isAfterLast())
        {
            if(selected.equals(allCars.getString(allCars.getColumnIndexOrThrow(TakeGoConst.CarConst.MODEL_NAME))))
                matrixCursor.addRow(new Object[]
                        {
                                allCars.getString(allCars.getColumnIndexOrThrow(TakeGoConst.CarConst.ID_BRANCH)),
                                allCars.getString(allCars.getColumnIndexOrThrow(TakeGoConst.CarConst.ID_TYPE_MODEL)),
                                allCars.getString(allCars.getColumnIndexOrThrow(TakeGoConst.CarConst.KILLOMETER)),
                                allCars.getString(allCars.getColumnIndexOrThrow(TakeGoConst.CarConst.ID_CAR)),
                                allCars.getString(allCars.getColumnIndexOrThrow(TakeGoConst.CarConst.MODEL_NAME))
                        });
            allCars.moveToNext();
        }
        return matrixCursor;
    }
    @Override public List<String> getModelName() throws Exception {
        ArrayList<String> modelNames = new ArrayList<>();
        Cursor models = getCarModels();

        models.moveToFirst();
        while (!models.isAfterLast())
        {
            String name = models.getString(models.getColumnIndex(TakeGoConst.CarModelConst.NAME));
            if(!modelNames.contains(name))
                modelNames.add(name);
            models.moveToNext();
        }
        return modelNames;
    }
    @Override public ArrayList<String> getAllCompanies() throws Exception {return TakeGoConst.getAllCompanies();}
    @Override public ArrayList<String> getModelsByCompany(String s) throws Exception {return TakeGoConst.getModelsByCompany(s);}

    @Override public ArrayList<String> getBranchesCodes() throws Exception {return TakeGoConst.getBranchesCodes();}

    @Override public ArrayList<String> getcodeByModel(String s) throws Exception {return TakeGoConst.getcodeByModel(s);}

    @Override
    public void dummyOperation() {
        int[] a = new int[4];

        /*
        this op made to help debug AsyncTasks
        */

    }


    @Override    public Cursor getClients()     throws Exception    {return TakeGoConst.ClientListToCursor();}
    @Override    public Cursor getBranches()    throws Exception   {return TakeGoConst.BranchListToCursor();}
    @Override    public Cursor getCars()        throws Exception       {return TakeGoConst.CarListToCursor();}
    @Override    public Cursor getCarModels()   throws Exception  {return TakeGoConst.CarModelListToCursor();}
}
