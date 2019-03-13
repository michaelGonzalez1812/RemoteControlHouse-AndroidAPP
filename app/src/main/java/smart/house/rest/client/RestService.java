package smart.house.rest.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import smart.house.modelo.Door;
import smart.house.modelo.Light;

public interface RestService {
    //TODO: Change Route
    String DOORS_ROUTE = "/posts";
    String LIGHTS_ROUTE = "/lights";

    @GET(DOORS_ROUTE)
    Call<List<Door>> getDoors();

    @GET(LIGHTS_ROUTE)
    Call<List<Light>> getLights();
}
