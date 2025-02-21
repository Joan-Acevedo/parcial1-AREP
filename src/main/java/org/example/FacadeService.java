package org.example;

import java.io.*;
import java.net.*;

public class FacadeService {

    private static FacadeService instance;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://localhost:35000/compreflex?comando";


    public FacadeService(){}

    public FacadeService getInstance(){
        if(instance == null) {
            instance = new FacadeService();
        }
        return instance;
    }

    public void getUri() throws IOException {

        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
    }

}
