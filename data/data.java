package data;

import classes.Mie;
import classes.Orang;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class data {

    public static ArrayList<Orang> orang = new ArrayList<Orang>();
    public static ArrayList<Mie> mie = new ArrayList<Mie>();
    public static HashMap<String, Mie> mie_map = new HashMap<String, Mie>();

    public static void saveData() {
        try {
            String orangstr = "";
            String miestr = "";

            for (int i = 0; i < orang.size(); i++) {
                orangstr = orangstr.concat(
                    "{\"nama\":\"" + orang.get(i).getNama() + "\",\"berat\":" + orang.get(i).getBerat()
                    + ",\"jml_dimiliki\":" + orang.get(i).getTotalDimiliki() + ",\"jml_dimakan\":"
                    + orang.get(i).getTotalDimakan() +  ",\"inventory\":{");
                for(int j=0; j<mie.size(); j++){
                    orangstr = orangstr.concat(
                        "\"" + mie.get(j).getMerek() + "\":" + orang.get(i).inventory.get(mie.get(j).getMerek()) + ","
                    );
                }
                orangstr = orangstr.concat("}}\n");
            }
            for (int i = 0; i < mie.size(); i++) {
                miestr = miestr.concat(
                        "{\"merek\":'" + mie.get(i).getMerek() + "\",\"berat\":" + mie.get(i).getBerat() + ",\"rasa\":'"
                                + mie.get(i).getRasa() + "\"}\n");
            }

            Files.writeString(Path.of("data/orang.json"), orangstr);
            Files.writeString(Path.of("data/mie.json"), miestr);

        } catch (Exception e) {
            System.out.println("gagal menyimpan data");
        }
    }

    public static void loadData() throws IOException, InterruptedException {
        // try{
            String mieDataRaw = Files.readString(Paths.get("data/mie.json"));
            mieDataRaw = mieDataRaw.replaceAll("[{}\"']", "");
            String[] mieDataResult = mieDataRaw.split("\n");

            for (int i = 0; i < mieDataResult.length; i++) {
                String[] data = mieDataResult[i].split(",");
                String merek = data[0].split(":")[1];
                double berat = Double.parseDouble(data[1].split(":")[1]);
                String rasa = data[2].split(":")[1].trim();
                Mie newMie = new Mie(merek, berat, rasa);
                mie_map.put(merek, newMie);
                mie.add(newMie);
            }

            String orangDataRaw = Files.readString(Paths.get("data/orang.json"));
            orangDataRaw = orangDataRaw.replaceAll("[{}'\"]", "");
            String[] orangDataResult = orangDataRaw.split("\n");

            for (int i = 0; i < orangDataResult.length; i++) {
                String[] data = orangDataResult[i].split(",");
                // System.out.println(data[0].split(":")[1]);
                String nama = data[0].split(":")[1];
                double berat = Double.parseDouble(data[1].split(":")[1]);
                int jml_dimiliki = Integer.parseInt(data[2].split(":")[1]);
                int jml_dimakan = Integer.parseInt(data[3].split(":")[1]);

                Orang newOrang = new Orang(nama.trim(), berat);

                for(int j=0; j<mie.size(); j++){
                    String merek = "";
                    int jumlah = 0;
                    if(j == 0){
                        merek = data[4+j].trim().split(":")[1];
                        jumlah = Integer.parseInt(data[4+j].trim().split(":")[2]);
                    }
                    else{
                        merek = data[4+j].trim().split(":")[0];
                        jumlah = Integer.parseInt(data[4+j].trim().split(":")[1]);
                    }
                    newOrang.inventory.put(merek, jumlah);
                }
                orang.add(newOrang);
            }
        // } catch(Exception e){
        //     System.out.println("Gagal memuat");
        // }
    }
}
