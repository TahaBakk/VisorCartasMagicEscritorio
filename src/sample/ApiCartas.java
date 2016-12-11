package sample;

import com.sun.jndi.toolkit.url.Uri;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Taha on 12/11/2016.
 */
public class ApiCartas {

    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    public ArrayList<Cartas> getCartes() throws JSONException, IOException {

        ArrayList<Cartas> cartas = new ArrayList<>();

        String JsonResponse = HTTPUtils.get(BASE_URL);
        JSONObject data = new JSONObject(JsonResponse);
        JSONArray jsonCartas = data.getJSONArray("cards");

        for (int i = 0; i < jsonCartas.length(); i++) {

            JSONObject object;

            Cartas carta = new Cartas();
            object = jsonCartas.getJSONObject(i);

            if (object.has("name")) {
                carta.setName(object.getString("name"));
            }

            if (object.has("text")) {
                carta.setText(object.getString("text"));
            }

            if (object.has("rarity")) {
                carta.setRarity(object.getString("rarity"));
            }

            if (object.has("imageUrl")) {
                carta.setImageUrl(object.getString("imageUrl"));
            }

            if (object.has("colors")) {
                carta.setColors(object.getString("colors"));
            }

            cartas.add(carta);

        }

        return cartas;
    }


}