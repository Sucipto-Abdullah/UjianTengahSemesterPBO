package classes;

import java.util.HashMap;

public class Orang 
{
    private String nama;
    private double berat;
    private int jml_mie_dimiliki;
    private int jml_mie_dimakan;
    public HashMap <String, Integer> inventory;

    public Orang( String nama, double berat )
    {
        this.nama = nama;
        this.berat = berat;
        this.jml_mie_dimakan = 0;
        this.jml_mie_dimiliki = 0;
        inventory = new HashMap<String, Integer>();
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

    public void beliMie( Mie mie, Integer jumlah )
    {
        try {
            int dimiliki = this.inventory.get(mie.getMerek()) + jumlah;
            this.inventory.put(mie.getMerek(), dimiliki);
            
        } catch (Exception e) {
            this.inventory.put(mie.getMerek(), jumlah);
        }
    }
}