package com.example.liran.takeogo.models.backend;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.example.liran.takeogo.models.entities.Branch;
import com.example.liran.takeogo.models.entities.Car;
import com.example.liran.takeogo.models.entities.CarModel;
import com.example.liran.takeogo.models.entities.Client;
import com.example.liran.takeogo.models.entities.EnumList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


/**
 * Created by liran on 17/11/2017.
 */

public class TakeGoConst {

    private static String httpGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        if (con.getResponseCode()==HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            return response.toString();
        }
        else return "";
    }
    public static String httpPost(String url, Set<Map.Entry<String, Object>> params) {
        try{
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params)
            {
                if(postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
                postData.append("=\"");
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
                postData.append("\"");
            }

            String f= url.concat(String.valueOf(postData));


            URL obj = new URL(f);
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(postData.toString().getBytes("UTF-8"));
            os.flush();
            os.close();



            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (con.getResponseCode()==HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null)
                    response.append(inputLine);
                in.close();
                return response.toString();
            }
            else return "";
        }
        catch (Exception ex)
        {}
        return "";
    }

    public static final String AUTHORITY = "com.lirantal.takego";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static ArrayList<String> getModelsByCompany(String s) throws Exception {
        ArrayList<String> relevantModels = new ArrayList<String>();

        JSONArray array = new JSONObject(httpGet("http://tades.vlab.jct.ac.il/getModels.php?company="+"\"" + s +"\"")).getJSONArray("models");
        for(int i=0;i<array.length();i++)
        {
            JSONObject obj = array.getJSONObject(i);
            relevantModels.add(obj.getString(CarModelConst.NAME));
        }
        return relevantModels;
    }
    public static ArrayList<String> getAllCompanies() throws Exception {
        ArrayList<String> allCompanies = new ArrayList<String>();

        JSONArray array = new JSONObject(httpGet("http://tades.vlab.jct.ac.il/getCompanies.php?")).getJSONArray("companies");
        for(int i=0;i<array.length();i++)
        {
            JSONObject obj = array.getJSONObject(i);
            allCompanies.add(obj.getString(CarModelConst.NAM_COMP));
        }
        return allCompanies;
    }
    public static ArrayList<String> getcodeByModel(String s) throws Exception {
        ArrayList<String> relevantCodes = new ArrayList<String>();

        JSONArray array = new JSONObject(httpGet("http://tades.vlab.jct.ac.il/getModelCode.php?modelName="+"\"" + s +"\"")).getJSONArray("code");
        for(int i=0;i<array.length();i++)
        {
            JSONObject obj = array.getJSONObject(i);
            relevantCodes.add(obj.getString(CarConst.ID_TYPE_MODEL));
        }
        return relevantCodes;
    }

    public static ArrayList<String> getBranchesCodes() throws Exception {
        ArrayList<String> relevantCodes = new ArrayList<String>();

        JSONArray array = new JSONObject(httpGet("http://tades.vlab.jct.ac.il/getBranches.php?")).getJSONArray("branches");
        for(int i=0;i<array.length();i++)
        {
            JSONObject obj = array.getJSONObject(i);
            relevantCodes.add(obj.getString(BranchConst.ID));
        }
        return relevantCodes;
    }


    public static class BranchConst{
        public static final String ID = "_id";
        public static final String NUMBER_PARKING = "numParking";
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String NUM_APARTMENT = "numApart";
        public static final String IMAGE = "image";


        public static final Uri BranchUri = Uri.withAppendedPath(AUTHORITY_URI,"branches");
        public static final String TABLE_BRANCHES = "branches";
    }
    public static class CarConst{
        public static final String ID_CAR = "_ID";
        public static final String ID_TYPE_MODEL = "modelID";
        public static final String MODEL_NAME = "modelName";
        public static final String KILLOMETER = "kilometer";
        public static final String ID_BRANCH = "branchID";

        public static final Uri CarUri = Uri.withAppendedPath(AUTHORITY_URI,"cars");
        public static final String TABLE_CARS = "cars";
    }
    public static class CarModelConst{
        public static final String ID = "_id";
        public static final String NAM_COMP ="company";
        public static final String NAME = "model";
        public static final String ENGINE_CAP = "engine";
        public static final String GEERBOX = "gear";
        public static final String NUMBER_OF_SEATS ="seats";

        public static final Uri CarModelsUri = Uri.withAppendedPath(AUTHORITY_URI,"carModels");
        public static  final String TABLE_CARSMODEL = "carModels";
    }
    public static class ClientConst{
        public static final String FIRST_NAME = "Fname";
        public static final String LAST_NAME = "Lname";
        public static final String ID = "_id";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String EMAIL = "email";
        public static final String NUM_CREDIT = "numCredit";

        public static final Uri ClientsUri = Uri.withAppendedPath(AUTHORITY_URI,"clients");
        public static final String TABLE_CLIENTS = "clients";
    }

    public static Branch ContentValuesToBranch(ContentValues contentValues) {
        Branch branch = new Branch();
        branch.setIdBranch(contentValues.getAsLong(BranchConst.ID));
        branch.setNumParking(contentValues.getAsInteger(BranchConst.NUMBER_PARKING));
        branch.setCity(contentValues.getAsString(BranchConst.CITY));
        branch.setStreet(contentValues.getAsString(BranchConst.STREET));
        branch.setNumApart(contentValues.getAsInteger(BranchConst.NUM_APARTMENT));


        return branch;
    }
    public static Car ContentValuesToCar(ContentValues contentValues){
        Car car = new Car();
        car.setIdBranch(contentValues.getAsLong(CarConst.ID_BRANCH));
        car.setIdCar(contentValues.getAsLong(CarConst.ID_CAR));
        car.setKilometer(contentValues.getAsInteger(CarConst.KILLOMETER));
        car.setIdTypeModel(contentValues.getAsLong(CarConst.ID_TYPE_MODEL));

        return car;
    }
    public static CarModel ContentValuesToCarModel(ContentValues contentValues) {
        CarModel carModel = new CarModel();
        carModel.setIdModel(contentValues.getAsLong(CarModelConst.ID));
        carModel.setNameModel(contentValues.getAsString(CarModelConst.NAME));
        carModel.setNameComp(contentValues.getAsString(CarModelConst.NAM_COMP));
        carModel.setEngineCap(contentValues.getAsInteger(CarModelConst.ENGINE_CAP));
        carModel.setNumberOfSeats(contentValues.getAsInteger(CarModelConst.NUMBER_OF_SEATS));
        carModel.setGeerbox(EnumList.GearBox.valueOf(contentValues.getAsString(CarModelConst.GEERBOX)));

        return carModel;

    }
    public static Client ContentValuesToClient(ContentValues contentValues){
        Client client = new Client();
        client.setId(contentValues.getAsLong(ClientConst.ID));
        client.setFname(contentValues.getAsString(ClientConst.FIRST_NAME));
        client.setLname(contentValues.getAsString(ClientConst.LAST_NAME));
        client.setPhoneNum(contentValues.getAsString(ClientConst.PHONE_NUMBER));
        client.setEmail(contentValues.getAsString(ClientConst.EMAIL));
        client.setNumCredit(contentValues.getAsLong(ClientConst.NUM_CREDIT));

        return client;

    }

    public static ContentValues BranchToContentValues(Branch branch)    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BranchConst.ID,branch.getIdBranch());
        contentValues.put(BranchConst.NUMBER_PARKING,branch.getNumParking());
        contentValues.put(BranchConst.CITY,branch.getCity());
        contentValues.put(BranchConst.STREET,branch.getStreet());
        contentValues.put(BranchConst.NUM_APARTMENT,branch.getNumApart());

        return contentValues;
    }
    public static ContentValues CarToContentValues(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarConst.ID_CAR,car.getIdCar());
        contentValues.put(CarConst.ID_BRANCH,car.getIdBranch());
        contentValues.put(CarConst.ID_TYPE_MODEL,car.getIdTypeModel());
        contentValues.put(CarConst.KILLOMETER,car.getKilometer());

        return contentValues;
    }
    public static ContentValues CarModelToContentValues(CarModel model) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarModelConst.ID,model.getIdModel());
        contentValues.put(CarModelConst.NAME,model.getNameModel());
        contentValues.put(CarModelConst.NAM_COMP,model.getNameComp());
        contentValues.put(CarModelConst.ENGINE_CAP,model.getEngineCap());
        contentValues.put(CarModelConst.NUMBER_OF_SEATS,model.getNumberOfSeats());
        contentValues.put(CarModelConst.GEERBOX,model.getGeerbox().toString());

        return contentValues;
    }
    public static ContentValues ClientToContentValues(Client client) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClientConst.ID,client.getId());
        contentValues.put(ClientConst.FIRST_NAME,client.getFname());
        contentValues.put(ClientConst.LAST_NAME,client.getLname());
        contentValues.put(ClientConst.EMAIL,client.getEmail());
        contentValues.put(ClientConst.PHONE_NUMBER,client.getPhoneNum());
        contentValues.put(ClientConst.NUM_CREDIT,client.getNumCredit());

        return contentValues;

    }

    public static Cursor BranchListToCursor() throws Exception {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]
                {
                        BranchConst.ID,
                        BranchConst.NUMBER_PARKING,
                        BranchConst.CITY,
                        BranchConst.STREET,
                        BranchConst.NUM_APARTMENT,
                        BranchConst.IMAGE
                });
        String temp = httpGet("http://tades.vlab.jct.ac.il/getBranches.php?");
        JSONObject jsnobject = new JSONObject(temp);
        JSONArray array = jsnobject.getJSONArray("branches");
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            matrixCursor.addRow(new Object[]
                    {
                            obj.getInt(BranchConst.ID),
                            obj.getString(BranchConst.NUMBER_PARKING),
                            obj.getString(BranchConst.CITY),
                            obj.getString(BranchConst.STREET),
                            obj.getString(BranchConst.NUM_APARTMENT),
                            obj.getString(BranchConst.IMAGE)
                    });
        }
        return matrixCursor;
    }
    public static Cursor CarModelListToCursor() throws Exception {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]
                {
                        CarModelConst.ID,
                        CarModelConst.NAM_COMP,
                        CarModelConst.NAME,
                        CarModelConst.ENGINE_CAP,
                        CarModelConst.GEERBOX,
                        CarModelConst.NUMBER_OF_SEATS
                });
        String temp = httpGet("http://tades.vlab.jct.ac.il/getCarModels.php?");
        JSONObject jsnobject = new JSONObject(temp);
        JSONArray array = jsnobject.getJSONArray("carModels");
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            matrixCursor.addRow(new Object[]
                    {
                            obj.getInt(CarModelConst.ID),
                            obj.getString(CarModelConst.NAM_COMP),
                            obj.getString(CarModelConst.NAME),
                            obj.getInt(CarModelConst.ENGINE_CAP),
                            obj.getString(CarModelConst.GEERBOX),
                            obj.getInt(CarModelConst.NUMBER_OF_SEATS)
                    });
        }
        return matrixCursor;
    }
    public static Cursor ClientListToCursor() throws Exception {

        MatrixCursor matrixCursor = new MatrixCursor(new String[]
                {
                        ClientConst.FIRST_NAME,
                        ClientConst.LAST_NAME,
                        ClientConst.ID,
                        ClientConst.PHONE_NUMBER,
                        ClientConst.EMAIL,
                        ClientConst.NUM_CREDIT
                });

        String temp = httpGet("http://tades.vlab.jct.ac.il/getClients.php?");
        JSONObject jsnobject = new JSONObject(temp);
        JSONArray array = jsnobject.getJSONArray("clients");
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            matrixCursor.addRow(new Object[]
                    {
                            obj.getString(ClientConst.FIRST_NAME),
                            obj.getString(ClientConst.LAST_NAME),
                            obj.getInt(ClientConst.ID),
                            obj.getString(ClientConst.PHONE_NUMBER),
                            obj.getString(ClientConst.EMAIL),
                            obj.getLong(ClientConst.NUM_CREDIT)
                    });
        }
        return matrixCursor;
    }
    public static Cursor CarListToCursor() throws Exception {
        MatrixCursor carsCursor= new MatrixCursor(new String[]
                {
                        CarConst.ID_BRANCH,
                        CarConst.ID_TYPE_MODEL,
                        CarConst.KILLOMETER,
                        CarConst.ID_CAR,
                        CarConst.MODEL_NAME,
                });
        JSONArray array = new JSONObject(httpGet("http://tades.vlab.jct.ac.il/getCars.php?")).getJSONArray("cars");
        for(int i=0;i<array.length();i++)
        {
            JSONObject obj = array.getJSONObject(i);
            carsCursor .addRow(new Object[]{
                    obj.getInt(TakeGoConst.CarConst.ID_BRANCH),
                    obj.getInt(CarConst.ID_TYPE_MODEL),
                    obj.getInt(CarConst.KILLOMETER),
                    obj.getInt(CarConst.ID_CAR),
                    obj.getString(CarConst.MODEL_NAME)
            });
        }
        return carsCursor;
    }



}

