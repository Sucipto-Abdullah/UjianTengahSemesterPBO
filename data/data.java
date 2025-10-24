package data;

import classes.Mie;
import classes.Orang;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class data {

    public static ArrayList<Orang> orang = new ArrayList<Orang>();
    public static HashMap<String, Integer> orang_map = new HashMap<String, Integer>();
    public static ArrayList<Mie> mie = new ArrayList<Mie>();
    public static HashMap<String, Integer> mie_map = new HashMap<String, Integer>();

    public static void add_Orang(String nama, double berat) {
        Orang newOrang = new Orang(nama, berat);
        int index = orang.size();
        for (int i = 0; i < mie.size(); i++) {
            newOrang.inventory.put(mie.get(i).getMerek(), 0);
        }
        orang.add(newOrang);
        orang_map.put(nama, index);
    }

    public static void del_Orang(String nama) {
        int index = orang_map.get(nama);
        orang.remove(index);
        orang_map.remove(nama);

        for (int i = index; i < orang.size(); i++) {
            orang_map.put(nama, i);
        }
    }

    public static int getRatarataMakan() {
        int n = orang.size();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += orang.get(i).getTotalDimakan();
        }
        return sum / n;
    }

    public static int getRatarataPunya() {
        int n = orang.size();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += orang.get(i).getTotalDimiliki();
        }
        return sum / n;
    }

    public static void add_Mie(String merek, String rasa, double berat) {
        Mie newMie = new Mie(merek, berat, rasa);
        int index = mie.size();
        for (int i = 0; i < orang.size(); i++) {
            orang.get(i).inventory.put(merek, 0);
        }
        mie.add(newMie);
        mie_map.put(merek, index);
    }

    public static void del_Mie(String merek) {
        int index = mie_map.get(merek);
        mie.remove(index);
        mie_map.remove(merek);
        for (int i = index; i < mie.size(); i++) {
            String targetMerek = mie.get(i).getMerek();
            mie_map.put(targetMerek, i);
        }
        for (int i = 0; i < orang.size(); i++) {
            String nama = orang.get(i).getNama();
            orang.get(index).inventory.remove(nama);
        }
    }

    public static void saveData() {
        try {
            String orangstr = "";
            String miestr = "";

            for (int i = 0; i < orang.size(); i++) {
                orangstr = orangstr.concat(
                        "{\"nama\":\"" + orang.get(i).getNama() + "\",\"berat\":" + orang.get(i).getBerat()
                                + ",\"jml_dimiliki\":" + orang.get(i).getTotalDimiliki() + ",\"jml_dimakan\":"
                                + orang.get(i).getTotalDimakan() + ",\"inventory\":{");
                for (int j = 0; j < mie.size(); j++) {
                    if (j != mie.size() - 1) {
                        orangstr = orangstr.concat(
                                "\"" + mie.get(j).getMerek() + "\":" + orang.get(i).inventory.get(mie.get(j).getMerek())
                                        + ",");
                    } else {
                        orangstr = orangstr.concat(
                                "\"" + mie.get(j).getMerek() + "\":"
                                        + orang.get(i).inventory.get(mie.get(j).getMerek()));
                    }
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
            mie_map.put(merek, i);
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
            newOrang.jml_mie_dimakan = jml_dimakan;
            newOrang.jml_mie_dimiliki = jml_dimiliki;

            for (int j = 0; j < mie.size(); j++) {
                String merek = "";
                int jumlah = 0;
                if (j == 0) {
                    merek = data[4 + j].trim().split(":")[1];
                    try {
                        jumlah = Integer.parseInt(data[4 + j].trim().split(":")[2]);
                    } catch (Exception e) {
                        jumlah = 0;
                    }
                } else {
                    merek = data[4 + j].trim().split(":")[0];
                    try {
                        jumlah = Integer.parseInt(data[4 + j].trim().split(":")[1]);
                    } catch (Exception e) {
                        jumlah = 0;
                    }
                }
                newOrang.inventory.put(merek, jumlah);
            }
            orang_map.put(nama, i);
            orang.add(newOrang);
        }
        // } catch(Exception e){
        // System.out.println("Gagal memuat");
        // }
    }
}
