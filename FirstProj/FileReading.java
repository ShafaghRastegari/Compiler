import java.io.*;

/**
 * Created by M.H.GH.K on 10/5/2018.
 */
public class FileReading {
    public String FileReading(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\0");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
