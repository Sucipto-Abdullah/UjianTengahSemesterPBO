package controller;

import classes.Mie;
import classes.Orang;
import data.data;
import java.io.IOException;

public class Control {

    public static boolean run = true;

    public static void exit() {
        System.out.println("Goodbye :D");
        run = false;
    }

    public static void help() {
        System.out.println("\nberikut beberapa perintah yang dapat digunakan:");
        System.out.println("help\t:menampilkan semua perintah beserta penjelasannya");
        System.out.println("list\t:menampilkan daftar seperti daftar orang maupun mie yang terdaftar");
        System.out.println(
                "orang\t:dapat mengelola objek dari orang yang dipilih, seperti melihat, mengedit, menambahkan, dan bahkan menghapusnya");
        System.out.println(
                "mie\t:dapat mengelola objek dari mie yang dipilih, seperti melihat, mengedit, menambahkan, dan bahkan menghapusnya");
        System.out.println("cls\t:membersihkan text yang telah tertulis di terminal");
        System.out.println("die\t:mematikan sistem");
        System.out.println("\n");
    }

    public static void list(String cmd) {
        if (cmd.equals("orang")) {
            System.out.println("Berikut list orang yang terdaftar:");
            for (int i = 0; i < data.orang.size(); i++) {
                System.out.println(i + 1 + ". " + data.orang.get(i).getNama());
            }
        }
        if (cmd.equals("mie")) {
            System.out.println("Berikut list mie yang terdaftar:");
            for (int i = 0; i < data.mie.size(); i++) {
                System.out.println(i + 1 + ". " + data.mie.get(i).getMerek());
            }
        }
        if (cmd.equals("help") || cmd.equals("")) {
            System.out.println(
                    "untuk menggunakan perintah ini, kamu harus menuliskannya sesuai struktur berikut :\nlist <daftar>\nseperti contohnya : \nlist orang\natau\nlist mie");
        }
    }

    public static void orang(String subjectName, String act, String targetAct, String amount) {
        if (act.equals("info")) {
            try {
                Orang orang = data.orang.get(data.orang_map.get(subjectName));
                int total_dimiliki = 0;
                System.out.println("\nberikut Informasi dari " + subjectName);
                System.out.println("nama : " + orang.getNama().replaceAll("_", " "));
                System.out.println("berat : " + orang.getBerat() + "kg");
                System.out.println("jumlah mie dimakan : " + orang.getTotalDimakan());
                System.out.println("Inventory : ");
                for (int i = 0; i < data.mie.size(); i++) {
                    System.out.println("\t" + data.mie.get(i).getMerek() + " : "
                            + orang.inventory.get(data.mie.get(i).getMerek()));
                    total_dimiliki = total_dimiliki + orang.inventory.get(data.mie.get(i).getMerek());
                }
                System.out.println("Total mie dimiliki : " + total_dimiliki);

            } catch (Exception e) {
                System.out.println("gagal menemukan " + subjectName + " yang anda cari");
            }
        }
        if (act.equals("makan")) {
            try {
                Orang orang = data.orang.get(data.orang_map.get(subjectName));
                int dimiliki = orang.inventory.get(targetAct);

                if (dimiliki < Integer.parseInt(amount)) {
                    System.out.println(
                            orang.getNama().replace("_", " ") + " tidak memiliki mie " + targetAct + " yang cukup");
                } else {
                    orang.makan(data.mie.get(data.mie_map.get(targetAct)), Integer.parseInt(amount));
                }
                data.saveData();
            } catch (Exception e) {
                System.out.println("gagal menjalankan perintah, pastikan perintah yang anda tulis benar ");
            }
        }
        if (act.equals("beli")) {
            try {
                Orang orang = data.orang.get(data.orang_map.get(subjectName));
                Mie mie = data.mie.get(data.mie_map.get(targetAct));
                orang.beliMie(mie, Integer.parseInt(amount));
                data.saveData();
            } catch (Exception e) {
                System.out.println("gagal menjalankan perintah, pastikan perintah yang anda tulis benar ");
            }
        }
        if (subjectName.equals("add")) {
            String nama = act;
            double berat = Double.parseDouble(targetAct);
            data.add_Orang(nama, berat);
            System.out.println("Orang berhasil terdaftar");
            data.saveData();
        }
        if (act.equals("setName")) {
            String newName = targetAct;
            int index = data.orang_map.get(subjectName);
            Orang orang = data.orang.get(index);

            data.orang_map.put(newName, index);
            data.orang_map.remove(subjectName);

            orang.setName(newName);
            data.saveData();
            System.out.println("Orang beranama " + subjectName + " berhasil diganti menjadi " + newName);
        }
        if (act.equals("delete")) {
            try {
                String nama = data.orang.get(data.orang_map.get(subjectName)).getNama();
                data.del_Orang(nama);
                System.out.println(nama + " telah berhasil dihapus");
                data.saveData();
            } catch (Exception e) {
                System.out.println("Perintah gagal dijalankan, pastikan anda mengetiknya dengan benar");
            }
        }
        if (subjectName.equals("rata-rata")) {
            int rataRataMakan = data.getRatarataMakan();
            int rataRataPunya = data.getRatarataPunya();

            System.out.println("rata-rata orang makan : " + rataRataMakan);
            System.out.println("rata-rata orang punya : " + rataRataPunya);
        }
    }

    public static void mie(String subjecName, String act, String targetAct, String amount) {
        if (act.equals("info")) {
            Mie mie = data.mie.get(data.mie_map.get(subjecName));
            System.out.println("Informasi dari mie " + subjecName);
            System.out.println("merek: " + mie.getMerek());
            System.out.println("berat: " + mie.getBerat());
            System.out.println("rasa: " + mie.getRasa());
        }
        if (subjecName.equals("add")) {
            String merek = act;
            double berat = Double.parseDouble(targetAct);
            String rasa = amount;
            data.add_Mie(merek, rasa, berat);
            System.out.println("Mie " + subjecName + " berhasil ditambahkan");
            data.saveData();
        }
        if (act.equals("delete")) {
            String merek = subjecName;
            data.del_Mie(merek);
            System.out.println("mie " + subjecName + " berhasil dihapus");
            data.saveData();
        }
    }

    // run command
    public static void runCommand(String cmd) throws IOException, InterruptedException {
        String[] commandArr = { "", "", "", "", "", "", "", "", "", "" };
        String[] commandStringsArr = cmd.trim().split(" ");

        for (int i = 0; i < commandStringsArr.length; i++) {
            commandArr[i] = commandStringsArr[i];
        }

        if (commandArr[0].toLowerCase().equals("exit")) {
            exit();
        }
        if (commandArr[0].equals("help")) {
            help();
        }
        if (commandArr[0].equals("die")) {
            new ProcessBuilder("cmd", "/c", "shutdown /s").inheritIO().start().waitFor();
        }
        if (commandArr[0].equals("list")) {
            list(commandArr[1].toLowerCase().trim());
        }
        if (commandArr[0].equals("cls")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }

        if (commandArr[0].equals("orang")) {
            orang(commandArr[1], commandArr[2], commandArr[3], commandArr[4]);
        }
        if (commandArr[0].equals("mie")) {
            mie(commandArr[1], commandArr[2], commandArr[3], commandArr[4]);
        }
    }

}