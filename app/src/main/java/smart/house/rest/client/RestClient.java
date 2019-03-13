package smart.house.rest.client;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import smart.house.modelo.Door;
import smart.house.modelo.Light;


import static android.content.ContentValues.TAG;

public class RestClient {

    //TODO: chnge BASE_URL
    private String BASE_URL = "https://jsonplaceholder.typicode.com";
    private List<Light> lights;
    private List<Door>  doors;

    public List<Light> getLights() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestService RestService = retrofit.create(RestService.class);
        Call<List<Light>> call = RestService.getLights();

        call.enqueue(new Callback<List<Light>>() {
            @Override
            public void onResponse(Call<List<Light>> call, Response<List<Light>> response) {
                Log.i(TAG, "onResponse: Success retreaving the lights information.");
                lights = response.body().subList(0, response.body().size());
            }

            @Override
            public void onFailure(Call<List<Light>> call, Throwable t) {
                Log.e(TAG, "onFailure: Error retreaving the lights information.");
            }
        });
        return lights;
    }

    public List<Door> getDoors() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestService RestService = retrofit.create(RestService.class);
        Call<List<Door>> call = RestService.getDoors();

        call.enqueue(new Callback<List<Door>>() {
            @Override
            public void onResponse(Call<List<Door>> call, Response<List<Door>> response) {
                Log.i(TAG, "onResponse: Success retreaving the doors information.");
                doors = response.body().subList(0, response.body().size());
            }

            @Override
            public void onFailure(Call<List<Door>> call, Throwable t) {
                Log.e(TAG, "onFailure: Error retreaving the doors information.");
                Log.e(TAG, "onFailure: Error retreaving the doors information.");
            }
        });
        return doors;
    }
}
