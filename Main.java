import data.data;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        
        try {
            String Json = "{\n\tnama : "+ data.orang1.getNama() +",\n\tberat : "+ data.orang1.getBerat() +"\n}";

            Files.writeString( Path.of("data.json"), Json);
            System.out.println("Telah Berhasil dibuat");
            
        } catch (Exception e) {
            System.out.println("Telah gagal");
        }

    }
}
