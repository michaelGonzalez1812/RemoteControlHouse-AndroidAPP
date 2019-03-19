package smart.house.sockets;

import android.os.AsyncTask;
import android.util.Log;

import java.net.Socket;

import static android.content.ContentValues.TAG;

public class SocketConnection extends AsyncTask<Void, Void, Void> {

    public static final String IP = "192.168.100.6";
    public static final int PORT = 8080;

    private static SocketConnection socketConnection;
    private Socket socket;

    private SocketConnection() {
        this.execute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (socket == null) {
            //Conecting with server
            try {
                socket = new Socket(IP, PORT);
                Log.i(TAG, socket.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }

    public static SocketConnection getInstance() {
        return socketConnection = (socketConnection == null ? new SocketConnection() : socketConnection);
    }

    public Socket getSocket() {
        return socket;
    }
}

