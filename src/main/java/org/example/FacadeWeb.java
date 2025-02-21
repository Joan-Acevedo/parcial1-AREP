package org.example;

import netscape.javascript.JSObject;

import java.io.*;
import java.net.*;

public class FacadeWeb {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {

            try {
                serverSocket = new ServerSocket(35000);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 35000.");
                System.exit(1);
            }

            boolean running = true;
            while(running){
                Socket clientSocket = null;
                try {
                    System.out.println("Listo para recibir ...");
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                }
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                String inputLine, outputLine;

                boolean fisrtLine = true;

                while ((inputLine = in.readLine()) != null) {
                    if(fisrtLine){
                        System.out.println("Recibí: " + inputLine);
                        fisrtLine = false;
                        continue;
                    }
                    if (!in.ready()) {
                        break;
                    }
                }

                //FacadeService service = null;
                //service.getInstance();

                //if(){
                    //outputLine = getClientResponse();
                //} else if () {
                    //outputLine = getConsultResponse();
                //} else if () {
                    //outputLine = getErrorPage();
                //}
                outputLine = getErrorPage();


                out.println(outputLine);
                out.close();
                in.close();
                clientSocket.close();
            }
        } catch (Exception e) {

        }
        serverSocket.close();
    }

    public static String getClientResponse (URI rURI) {
        return "HTTP/1.1 200 OK\\r\\n\"\n"
                + " \"Content-Type: text/html\\r\\n\"\n"
                + " \"\\r\\n"
                + "<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "    <head>\r\n" +
                "        <title>Parcial 1 AREP</title>\r\n" +
                "        <meta charset=\"UTF-8\">\r\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" +
                "    </head>\r\n" +
                "    <body>\r\n" +
                "        <h1>Form with GET</h1>\r\n" +
                "        <form action=\"/consult\">\r\n" +
                "            <label for=\"name\">Command:</label><br>\r\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\r\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\r\n" +
                "        </form> \r\n" +
                "        <div id=\"getrespmsg\"></div>\r\n" +
                "\r\n" +
                "        <script>\r\n" +
                "            function loadGetMsg() {\r\n" +
                "                let nameVar = document.getElementById(\"name\").value;\r\n" +
                "                const xhttp = new XMLHttpRequest();\r\n" +
                "                xhttp.onload = function() {\r\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\r\n" +
                "                    this.responseText;\r\n" +
                "                }\r\n" +
                "                xhttp.open(\"GET\", \"/consult?name=\"+nameVar);\r\n" +
                "                xhttp.send();\r\n" +
                "            }\r\n" +
                "        </script>\r\n" +
                "    </body>\r\n" +
                "</html>";
    }

    private static String getConsultResponse(URI rURI){


        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Facade</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Mi propio mensaje</h1>\n" // revisar
                + "</body>\n"
                + "</html>\n";
    }

    private static String getErrorPage() {
        return "HTTP/1.1 404 Not Found\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Error 404 Not Found</h1>\n"
                + "</body>\n"
                + "</html>\n";
    }

}
