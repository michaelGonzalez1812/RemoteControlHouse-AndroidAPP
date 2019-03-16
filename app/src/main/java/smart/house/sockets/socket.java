package smart.house.sockets;

import android.os.AsyncTask;

import java.net.*;
import java.io.*;

public class socket extends AsyncTask<Integer, Integer, Integer> {
    public void llamada() {

        try {

            Socket echoSocket = new Socket("192.168.100.6", 8080);
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

    @Override
    protected Integer doInBackground(Integer... integers) {
        llamada();
        return 0;
    }
}