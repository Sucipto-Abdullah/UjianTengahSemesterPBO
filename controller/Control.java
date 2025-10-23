package controller;

import data.data;

public class Control {

    public static boolean run = true;



    public static void exit(){
        System.out.println("Goodbye :D");
        run = false;
    }

    public static void runCommand(String cmd){

        if (cmd.toLowerCase().equals("exit")){
            exit();
        }
        if (cmd.toLowerCase().equals("orang list")){
            for(int i=0; i<data.orang.size(); i++){
                System.out.println(i+1 + ". " + data.orang.get(i).getNama());
            }
        }

    }

}