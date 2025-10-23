package classes;

public class Mie {

    private String merek;
    private String rasa;
    private double berat;

    public Mie( String merek, double  berat, String rasa )
    {
        this.merek = merek;
        this.berat = berat;
        this.rasa = rasa;
    }

    public String getMerek(){
        return this.merek;
    }

    public double getBerat(){
        return this.berat;
    }

    public String getRasa(){
        return this.rasa;
    }

}
