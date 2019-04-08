package gabriellee.project.foodrecipesv2.util;

import android.util.Log;

import gabriellee.project.foodrecipesv2.models.Recipe;

import java.util.List;

public class Testing {

    public static void printRecipes(List<Recipe>list, String tag){
        for(Recipe recipe: list){
            Log.d(tag, "onChanged: " + recipe.getTitle());
        }
    }
}
