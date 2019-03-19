package smart.house.sockets;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Switch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static android.content.ContentValues.TAG;

public class DoorsUpdater extends AsyncTask <Switch, Integer, String> {

    private Switch[] switches;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public String getDoorsState() {
        String state = "";

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.write("OK".getBytes());

            byte[] fromServer = new byte[1024];
            int size = dataInputStream.read(fromServer);
            state = new String(fromServer);
            Log.i(TAG, "Tamaño de bytes = " + size);
            Log.i(TAG, "getDoorsState: " +  state);

            return state;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
    }

    @Override
    protected void onPostExecute(String state) {
        super.onPostExecute(state);

        for (int i = 0; i < switches.length; i++)
            switches[i].setChecked(state.charAt(i) == '1' ? true : false);
    }

    @Override
    protected String doInBackground(Switch... switches) {
        this.switches = switches;
        socket = SocketConnection.getInstance().getSocket();
        return getDoorsState();
    }
}
