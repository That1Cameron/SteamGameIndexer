import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SteamListFetcher {
    StringBuilder response;

    public SteamListFetcher() {

    }

    public void fetchList(){
        try {
            response = new StringBuilder();
            // fetch list
            URL listEndpoint = new URL("https://api.steampowered.com/ISteamApps/GetAppList/v2/");
            HttpURLConnection request = (HttpURLConnection) listEndpoint.openConnection();
            request.setRequestMethod("GET");
            InputStream in = request.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("womp womp");
        }
    }

    @Override
    public String toString() {
        return response.toString();
    }

    public void toFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(response.toString());
            System.out.println("StringBuilder content written to output.txt successfully using BufferedWriter.");
        }catch (Exception e){
            System.out.println("oh no the file didn't write, hope it does next time.");
        }
    }
}
