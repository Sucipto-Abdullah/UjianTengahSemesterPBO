package classes;

import java.util.HashMap;

public class Orang {
    private String nama;
    private double berat;
    public Integer jml_mie_dimiliki;
    public Integer jml_mie_dimakan;
    public HashMap<String, Integer> inventory;

    public Orang(String nama, double berat) {
        this.nama = nama;
        this.berat = berat;
        this.jml_mie_dimakan = 0;
        this.jml_mie_dimiliki = 0;
        inventory = new HashMap<String, Integer>();
    }

    public String getNama() {
        return this.nama;
    }

    public double getBerat() {
        return this.berat;
    }

    public void setName(String newName) {
        this.nama = newName;
    }

    public int getTotalDimiliki() {
        return jml_mie_dimiliki;
    }

    public int getTotalDimakan() {
        return jml_mie_dimakan;
    }

    public void beliMie(Mie mie, Integer jumlah) {
        try {
            int dimiliki = this.inventory.get(mie.getMerek()) + jumlah;
            this.inventory.put(mie.getMerek(), dimiliki);
            this.jml_mie_dimiliki = this.jml_mie_dimiliki + jumlah;
            System.out.println(this.nama.replaceAll("_", " ") + " membeli mie " + mie.getMerek() + " rasa "
                    + mie.getRasa() + " dengan jumlah " + jumlah);
        } catch (Exception e) {
            this.inventory.put(mie.getMerek(), jumlah);
        }
    }

    public void makan(Mie mie, Integer jumlah) {
        this.berat += mie.getBerat() * jumlah;
        int jumlah_mie_sekarang = this.inventory.get(mie.getMerek()).intValue() - jumlah.intValue();

        this.inventory.remove(mie.getMerek());
        this.inventory.put(mie.getMerek(), jumlah_mie_sekarang);

        this.jml_mie_dimiliki = this.jml_mie_dimiliki - jumlah;
        this.jml_mie_dimakan = this.jml_mie_dimakan + jumlah;
        System.out.println(this.nama.replaceAll("_", " ") + " memakan mie " + mie.getMerek() + " rasa " + mie.getRasa()
                + " dengan jumlah " + jumlah);
    }
}