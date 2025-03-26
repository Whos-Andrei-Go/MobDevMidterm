package ph.edu.usc.go_midterm;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategorySelectionActivity extends AppCompatActivity {

    private TextView headerTextView;
    private RecyclerView listCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_selection);

        View curView = findViewById(R.id.category_selection);
        ViewCompat.setOnApplyWindowInsetsListener(curView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(curView.getPaddingLeft() + systemBars.left, curView.getPaddingTop() + systemBars.top, curView.getPaddingRight() + systemBars.right, v.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        headerTextView = findViewById(R.id.txt_header);
        listCategories = findViewById(R.id.list_categories);

        String categoryType = getIntent().getStringExtra("cat");

        if (categoryType.equals("All")) {
            headerTextView.setText("Available Categories");
        } else {
            headerTextView.setText("Categories for " + categoryType);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listCategories.setLayoutManager(layoutManager);

        List<Category> categories = getGeneralCategories(getApplicationContext());
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        listCategories.setAdapter(categoryAdapter);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    private List<Category> getGeneralCategories(Context context) {
        List<Category> categories = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(Utils.loadJSONFromAsset(context, "categories.json"));
            JSONArray categoryArray = jsonObject.getJSONArray("categories");

            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject categoryJson = categoryArray.getJSONObject(i);
                String name = categoryJson.getString("name");
                String type = categoryJson.getString("type");

                JSONArray servicesJsonArray = categoryJson.getJSONArray("services");
                List<String> services = new ArrayList<>();
                for (int j = 0; j < servicesJsonArray.length(); j++) {
                    services.add(servicesJsonArray.getString(j));
                }

                categories.add(new Category(name, type, services));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
