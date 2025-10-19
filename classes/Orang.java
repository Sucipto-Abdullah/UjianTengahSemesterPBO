package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Orang 
{
    static int jml_orang = 0;
    static ArrayList<Orang> list_Orang = new ArrayList<Orang>();
    static HashMap<String, Orang> map_Orang = new HashMap<String, Orang>();

    private String nama;
    private double berat;
    private int jml_mie_dimiliki;
    private int jml_mie_dimakan;
    public HashMap <Mie, Integer> inventory;

    public Orang( String nama, int berat )
    {
        this.nama = nama;
        this.berat = berat;
        this.jml_mie_dimakan = 0;
        this.jml_mie_dimiliki = 0;
        inventory = new HashMap<Mie, Integer>();

        jml_orang += 1;
    }

    public String getNama(){
        return this.nama;
    }

    public double  getBerat(){
        return this.berat;
    }

    public int getTotalDimiliki(){
        return jml_mie_dimiliki;
    }

    public int getTotalDimakan(){
        return jml_mie_dimakan;
    }

    public void beliMie( Mie mie, int jumlah )
    {
        try {
            int dimiliki = this.inventory.get(mie.getMerek()) + jumlah;
            this.inventory.put(mie, dimiliki);
            
        } catch (Exception e) {
            this.inventory.put(mie, jumlah);
        }
    }

    static double getRatarata(){
        int sum = 0;
        for (int i=0; i<list_Orang.size(); i++){
            sum += list_Orang.get(i).jml_mie_dimakan;
        }
        return sum/list_Orang.size();
    }

    static Orang get(String nama){
        return map_Orang.get(nama);
    }
}