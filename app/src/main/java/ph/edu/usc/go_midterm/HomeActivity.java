package ph.edu.usc.go_midterm;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

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

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView listCategories = findViewById(R.id.list_categories);
        listCategories.setLayoutManager(layoutManager);

        RecyclerView listCleaners = findViewById(R.id.list_cleaners);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Cleaning Service"));
        categories.add(new Category("Cleaning Appliance"));
        categories.add(new Category("Babysitter"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        listCategories.setAdapter(categoryAdapter);

        List<Cleaner> cleaners = new ArrayList<>();

        Cleaner.CapabilityIndex capabilityIndex1 = new Cleaner.CapabilityIndex(4.5f, 4.8f, 4.7f);
        Cleaner.CapabilityIndex capabilityIndex2 = new Cleaner.CapabilityIndex(4.3f, 4.6f, 4.8f);
        Cleaner.CapabilityIndex capabilityIndex3 = new Cleaner.CapabilityIndex(4.6f, 4.9f, 4.5f);

        cleaners.add(new Cleaner("John Doe", 30, "123 Main St", "123-456-7890", 4.5f, "Monday - Friday", capabilityIndex1));
        cleaners.add(new Cleaner("Jane Smith", 28, "456 Oak St", "123-456-7891", 4.8f, "Weekends", capabilityIndex2));
        cleaners.add(new Cleaner("David Lee", 35, "789 Pine St", "123-456-7892", 4.7f, "Monday - Saturday", capabilityIndex3));

        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        CleanerAdapter cleanerAdapter = new CleanerAdapter(this, cleaners);
        listCleaners.setLayoutManager(layoutManager2);
        listCleaners.setAdapter(cleanerAdapter);
    }
}