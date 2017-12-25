package com.example.liran.takeogo.models.entities;

/**
 * Created by liran on 08/11/2017.
 */

public class Car
{
    private long idBranch;
    private long idTypeModel;
    private int kilometer;
    private long idCar;

    public Car(){}

    public Car(long IdBranch, long IdTypeModel, int Kilometer, long IdCar) {
        this.idBranch = IdBranch;
        this.idTypeModel = IdTypeModel;
        this.kilometer = Kilometer;
        this.idCar = IdCar;
    }

    public long getIdBranch() {return idBranch;}

    public void setIdBranch(long idBranch) {this.idBranch = idBranch;}

    public long getIdTypeModel() {return idTypeModel;}

    public void setIdTypeModel(long IdtypeModel) {this.idTypeModel = IdtypeModel;}

    public int getKilometer() {return kilometer;}

    public void setKilometer(int kilometer) {this.kilometer = kilometer;}

    public long getIdCar() {return idCar;}

    public void setIdCar(long idCar) {this.idCar = idCar;}
}





