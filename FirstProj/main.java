import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by M.H.GH.K on 10/5/2018.
 */
public class main {
    public static void main(String[] args){
        Lexer my=new Lexer();

            String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get("C:\\Users\\M.H.GH.K\\Desktop\\hes.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //


        //System.out.print(data);
           // my.lexerAnalyzer(data);
            my.lexerAnalyzer("@if  @123.545 2.  . / @   .565 ( ( ( ) ) ) ( ( ( . . . . a= == == ==  * ** / // /\n \n \n ) ( ., ;");
            System.out.println();

            my.Print();

            System.out.print(my.allOfTokens.size());
            System.out.println();



    }
}
