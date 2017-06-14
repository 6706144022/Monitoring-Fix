package com.example.blacksmith.monitoring;

import java.util.Date;

/**
 * Created by Nurul on 5/9/2017.
 */

public class Model {

    public int id;
    public  Double nilai_kelembapan;
    public String status_kelembapan;
    public String tanggal;
    public String jam;
    public Model(int id, Double kelembaban, String status, String jam){
        this.id = id;
        this.nilai_kelembapan = kelembaban;
        this.status_kelembapan = status;
        this.jam = jam;
    }
}
