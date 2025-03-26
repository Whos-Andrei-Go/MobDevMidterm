package ph.edu.usc.go_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    SearchView searchCategory;
    TextView btnSelectCategoryMore, btnCleanersMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        View homeView = findViewById(R.id.home);
        ViewCompat.setOnApplyWindowInsetsListener(homeView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(homeView.getPaddingLeft() + systemBars.left, homeView.getPaddingTop() + systemBars.top, homeView.getPaddingRight() + systemBars.right, homeView.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView listCategories = findViewById(R.id.list_categories);
        listCategories.setLayoutManager(layoutManager);

        RecyclerView listCleaners = findViewById(R.id.list_cleaners);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Cleaning Service", "Cleaning", Arrays.asList("Home Cleaning", "Office Cleaning", "Deep Cleaning")));
        categories.add(new Category("Cleaning Appliance", "Appliance", Arrays.asList("Air Conditioner Cleaning", "Washing Machine Cleaning")));
        categories.add(new Category("Babysitter", "Babysitting", Arrays.asList("Infant Care", "Toddler Care", "Preschooler Care")));

        CategorySmallAdapter categoryAdapter = new CategorySmallAdapter(this, categories);
        listCategories.setAdapter(categoryAdapter);

        CleanerService cleanerService = new CleanerService(getApplicationContext());
        List<Cleaner> cleaners = cleanerService.getCleaners();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        CleanerAdapter cleanerAdapter = new CleanerAdapter(this, cleaners);
        listCleaners.setLayoutManager(layoutManager2);
        listCleaners.setAdapter(cleanerAdapter);

        searchCategory = findViewById(R.id.search_category);
        searchCategory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                categoryAdapter.filter(newText);
                return false;
            }
        });

        btnSelectCategoryMore = findViewById(R.id.btn_categories_more);
        btnCleanersMore = findViewById(R.id.btn_cleaners_more);

        btnSelectCategoryMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CategorySelectionActivity.class);
                intent.putExtra("cat", "All");
                startActivity(intent);
            }
        });

        btnCleanersMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CleanerSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
