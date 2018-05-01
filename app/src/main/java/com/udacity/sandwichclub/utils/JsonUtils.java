package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        final JSONObject obj = new JSONObject(json);
        final JSONObject innerObj = obj.getJSONObject("name");

        final String name = innerObj.getString("mainName");
        final String description = obj.getString("description");
        final String image = obj.getString("image");
        final JSONArray ingredients = obj.getJSONArray("ingredients");

        final String placeOfOrigin;
        ArrayList<String> listKnownAs = new ArrayList<String>();
        JSONArray alsoKnownAs = null;
        Sandwich sandwich = new Sandwich();


        if(innerObj.has("alsoKnownAs")) {
            alsoKnownAs = innerObj.getJSONArray("alsoKnownAs");
            int len = alsoKnownAs.length();
            for (int i = 0; i < len; i++) {
                listKnownAs.add(alsoKnownAs.get(i).toString());
            }
            sandwich.setAlsoKnownAs(listKnownAs);
        }
        else
            sandwich.setAlsoKnownAs(listKnownAs);


        placeOfOrigin = obj.getString("placeOfOrigin");
        if(placeOfOrigin.equals("")) {
            sandwich.setPlaceOfOrigin("Unknown");
        }
        else
            sandwich.setPlaceOfOrigin(placeOfOrigin);

        ArrayList<String> listIngredients = new ArrayList<String>();
        if (ingredients != null) {
            int len = ingredients.length();
            for (int i=0;i<len;i++){
                listIngredients.add(ingredients.get(i).toString());
            }
        }

        sandwich.setMainName(name);
        sandwich.setDescription(description);
        sandwich.setImage(image);
        sandwich.setIngredients(listIngredients);

        return sandwich;
    }
}
