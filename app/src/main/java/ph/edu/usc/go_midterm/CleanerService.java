package ph.edu.usc.go_midterm;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CleanerService {

    private List<Cleaner> cleaners;
    private int currentId;

    public CleanerService(Context context) {
        cleaners = new ArrayList<>();

        try{
            JSONObject json = new JSONObject(Utils.loadJSONFromAsset(context, "cleaners.json"));
            JSONArray cleanerArr = json.getJSONArray("cleaners");
            int x;

            for(x = 0; x < cleanerArr.length(); x++){
                JSONObject cleaner = cleanerArr.getJSONObject(x);

                JSONArray serviceTypesJsonArray = cleaner.getJSONArray("serviceTypes");
                String[] serviceTypes = new String[serviceTypesJsonArray.length()];
                for (int j = 0; j < serviceTypesJsonArray.length(); j++) {
                    serviceTypes[j] = serviceTypesJsonArray.getString(j);
                }

                JSONObject capabilityIndexJson = cleaner.getJSONObject("capabilityIndex");
                Cleaner.CapabilityIndex capabilityIndex = new Cleaner.CapabilityIndex(
                        capabilityIndexJson.getInt("attitude"),
                        capabilityIndexJson.getInt("quality"),
                        capabilityIndexJson.getInt("satisfaction")
                );

                cleaners.add(new Cleaner(
                        cleaner.getInt("id"),
                        cleaner.getString("name"),
                        cleaner.getInt("age"),
                        cleaner.getString("address"),
                        cleaner.getString("phone"),
                        BigDecimal.valueOf(cleaner.getDouble("rating")).floatValue(),
                        cleaner.getString("availability"),
                        serviceTypes,
                        capabilityIndex
                ));
            }


        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public List<Cleaner> getCleaners() {
        return cleaners;
    }

    public Cleaner getCleanerById(int id) {
        for (Cleaner cleaner : cleaners) {
            if (cleaner.getId() == id) {
                return cleaner;
            }
        }
        return null;
    }
}
