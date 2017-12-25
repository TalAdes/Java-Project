package com.example.liran.takeogo.models.entities;

import java.util.Date;

/**
 * Created by liran on 08/11/2017.
 */

public class Order {
    private long numCustomer;
    private String statusOrdr;
    private long idCar;
    private Date start;
    private Date finish;
    private int startKilometer;
    private int finishKilometer;
    private boolean oil;
    private int liter;
    private int total;
    private long idOrder;

    public Order(long NumCustomer, String StatusOrdr, long IdCar, Date Start, Date Finish, int StartKilometer,
                 int FinishKilometer, boolean Oil, int Liter, int Total, long IdOrder) {
        this.numCustomer = NumCustomer;
        this.statusOrdr = StatusOrdr;
        this.idCar = IdCar;
        this.start = Start;
        this.finish = Finish;
        this.startKilometer = StartKilometer;
        this.finishKilometer = FinishKilometer;
        this.oil = Oil;
        this.liter = Liter;
        this.total = Total;
        this.idOrder = IdOrder;
    }

    public long getNumCustomer() {return numCustomer;}

    public void setNumCustomer(long numCustomer) {this.numCustomer = numCustomer;}

    public String getStatusOrdr() {return statusOrdr;}

    public void setStatusOrdr(String statusOrdr) {this.statusOrdr = statusOrdr;}

    public long getIdCar() {return idCar;}

    public void setIdCar(long idCar) {this.idCar = idCar;}

    public Date getStart() {return start;}

    public void setStart(Date start) {this.start = start;}

    public Date getFinish() {return finish;}

    public void setFinish(Date finish) {this.finish = finish;}

    public int getStartKilometer() {return startKilometer;}

    public void setStartKilometer(int startKilometer) {this.startKilometer = startKilometer;}

    public int getFinishKilometer() {return finishKilometer;}

    public void setFinishKilometer(int finishKilometer) {this.finishKilometer = finishKilometer;}

    public boolean isOil() {return oil;}

    public void setOil(boolean oil) {this.oil = oil;}

    public int getLiter() {return liter;}

    public void setLiter(int liter) {this.liter = liter;}

    public int getTotal() {return total;}

    public void setTotal(int total) {this.total = total;}

    public long getIdOrder() {return idOrder;}

    public void setIdOrder(long idOrder) {this.idOrder = idOrder;}
}
