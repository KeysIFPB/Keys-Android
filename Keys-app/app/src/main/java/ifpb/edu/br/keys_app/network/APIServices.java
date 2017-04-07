package ifpb.edu.br.keys_app.network;

import java.util.List;

import ifpb.edu.br.keys_app.models.Chave;
import ifpb.edu.br.keys_app.models.Usuario;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Mateus on 28/03/2017.
 */

public interface APIServices {

    @GET("chave/listar")
    Call<List<Chave>> getAll();


    @GET("chave/listar/sala/{sala}")
    Call<List<Chave>> getChaveBySala(@Path("sala") String sala);


    @POST("chave/cadastrar")
    Call<Usuario> insert(@Body Usuario usuario);





    /*@POST("/cadastrar")
    Call<List<Sine>> getSinesGPS(
            @Path("lat") Double lat,
            @Path("long") Double longitude,
            @Path("raio") String raio
    );*/
}
