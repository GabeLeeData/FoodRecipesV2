package gabriellee.project.foodrecipesv2.repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import gabriellee.project.foodrecipesv2.models.Recipe;
import gabriellee.project.foodrecipesv2.persistence.RecipeDao;
import gabriellee.project.foodrecipesv2.persistence.RecipeDatabase;
import gabriellee.project.foodrecipesv2.requests.ServiceGenerator;
import gabriellee.project.foodrecipesv2.requests.responses.ApiResponse;
import gabriellee.project.foodrecipesv2.requests.responses.RecipeSearchResponse;
import gabriellee.project.foodrecipesv2.util.Constants;
import gabriellee.project.foodrecipesv2.util.Network.AppExecutors;
import gabriellee.project.foodrecipesv2.util.Network.NetworkBoundResource;
import gabriellee.project.foodrecipesv2.util.Network.Resource;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeDao recipeDao;

    public static RecipeRepository getInstance(Context context) {
        if(instance == null) {
            instance = new RecipeRepository(context);
        }
        return instance;

    }

    public RecipeRepository(Context context) {
        recipeDao = RecipeDatabase.getInstance(context).getRecipeDao();
    }

    public LiveData<Resource<List<Recipe>>> searchRecipesApi(final String query, final int pageNumber) {
        return new NetworkBoundResource<List<Recipe>, RecipeSearchResponse>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull RecipeSearchResponse item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<Recipe> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Recipe>> loadFromDb() {
                return recipeDao.searchRecipes(query, pageNumber);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<RecipeSearchResponse>> createCall() {
                return ServiceGenerator.getRecipeApi()
                        .searchRecipe(
                                Constants.API_KEY,
                                query,
                                String.valueOf(pageNumber)
                        );

            }
        }.getAsLiveData();
    }

}
