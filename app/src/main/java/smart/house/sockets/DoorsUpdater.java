package smart.house.sockets;

import android.os.AsyncTask;
import android.widget.Switch;

public class DoorsUpdater extends AsyncTask <Switch, Integer, String> {

    private Switch[] switches;

    public String getDoorsState() {
        String state = "";

        return state;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(Switch... switches) {
        this.switches = switches;

        return getDoorsState();
    }
}
