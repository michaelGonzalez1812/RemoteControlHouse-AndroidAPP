package smart.house;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

import smart.house.sockets.SocketConnection;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar myToolbar = findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        myToolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().popBackStack();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment, ControlFragment.newInstance())
                .commit();

        SocketConnection.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    SocketConnection.getInstance().getSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        switch (id) {
            case R.id.toolbar_menu_item_information: {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, InfoFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            } break;

            case R.id.toolbar_menu_item_camera: {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, PictureFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            } break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        }
    }
}
