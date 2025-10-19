package classes;

import java.util.HashMap;

public class Mie {
    static int jml_mie;
    static HashMap<String, Mie> map_Mie = new HashMap<String, Mie>();

    private String merek;
    private String rasa;
    private int berat;

    public Mie( String merek, int berat, String rasa )
    {
        this.merek = merek;
        this.berat = berat;
        this.rasa = rasa;

        jml_mie += 1;
    }

    public String getMerek(){
        return this.merek;
    }

    static Mie get( String merek )
    {
        return map_Mie.get(merek);
    }

}
