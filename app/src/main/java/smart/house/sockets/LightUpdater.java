package smart.house.sockets;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import smart.house.Model.Light;

import static android.content.ContentValues.TAG;

public class LightUpdater extends AsyncTask <Light,Integer, Integer> {

    private Socket socket;
    private Light light;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private void updateLightState() {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String msg = (light.isOn() ? "1" : "0") + light.getName();
            dataOutputStream.write(msg.getBytes());

            byte[] fromServer = new byte[1024];
            dataInputStream.read(fromServer);

            Log.i(TAG, "updateLightState: " +  new String(fromServer));
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
