package com.example.liran.takeogo.models.backend;

import com.example.liran.takeogo.models.datasources.Lists_DBManager;

/**
 * Created by liran on 08/11/2017.
 */

public class DBManagerFactory {
    static IDBManager manager = null;

    public static IDBManager getMnager(){
        if(manager == null)
            manager = new Lists_DBManager();
        return manager;
    }



}
