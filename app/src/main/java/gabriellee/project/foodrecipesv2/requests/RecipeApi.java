package gabriellee.project.foodrecipesv2.requests;

import android.arch.lifecycle.LiveData;

import gabriellee.project.foodrecipesv2.requests.responses.ApiResponse;
import gabriellee.project.foodrecipesv2.requests.responses.RecipeResponse;
import gabriellee.project.foodrecipesv2.requests.responses.RecipeSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

    // SEARCH
    @GET("api/search")
    LiveData<ApiResponse<RecipeSearchResponse>> searchRecipe(
            @Query("key") String key,
            @Query("q") String query,
            @Query("page") String page
    );

    // GET RECIPE REQUEST
    @GET("api/get")
    LiveData<ApiResponse<RecipeResponse>> getRecipe(
            @Query("key") String key,
            @Query("rId") String recipe_id
    );
}
