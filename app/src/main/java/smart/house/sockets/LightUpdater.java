package smart.house.sockets;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import smart.house.Model.Light;

public class LightUpdater extends AsyncTask <Light,Integer, Integer> {

    private Socket socket;
    private Light light;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private void updateLightState() {
        String fromServer = "";
        String fromUser = "";

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            //TODO: Agregar aqui el protocolo para acatualizar el valor de las luces
            fromServer = dataInputStream.readUTF();
            dataOutputStream.writeUTF(fromUser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer doInBackground(Light... lights) {
        socket = SocketConnection.getInstance().getSocket();
        light = lights[0];
        updateLightState();

        return null;
    }
}
