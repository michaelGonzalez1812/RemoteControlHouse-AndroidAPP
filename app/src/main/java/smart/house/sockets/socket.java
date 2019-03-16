package smart.house.sockets;

import java.net.*;
import java.io.*;

public class socket {
    public void llamada() {

        try {

            Socket echoSocket = new Socket("172.19.54.35", 6969);
            //PrintStream out = new PrintStream(echoSocket.getOutputStream());
            //Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            System.out.println("Client Message:");
//            fromUser = stdIn.readLine();
            fromUser = "Michael";
            System.out.println("Client: " + fromUser);
            out.println(fromUser);


            fromServer = in.readLine();
            System.out.println("Server: " + fromServer);

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}