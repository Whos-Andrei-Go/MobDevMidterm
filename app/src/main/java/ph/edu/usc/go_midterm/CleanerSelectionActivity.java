package ph.edu.usc.go_midterm;

import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class CleanerSelectionActivity extends AppCompatActivity {

    TextView headerTextView;
    RecyclerView listCleaners;
    SearchView searchCleaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cleaner_selection);

        View curView = findViewById(R.id.cleaner_selection);
        ViewCompat.setOnApplyWindowInsetsListener(curView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(curView.getPaddingLeft() + systemBars.left, curView.getPaddingTop() + systemBars.top, curView.getPaddingRight() + systemBars.right, curView.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        headerTextView = findViewById(R.id.txt_header);
        listCleaners = findViewById(R.id.list_cleaners);

        String serviceType = getIntent().getStringExtra("serviceType");

        if (serviceType == null || serviceType.equals("none")) {
            serviceType = "";
            headerTextView.setText("Available Cleaners for All Services");
        } else {
            headerTextView.setText("Available Cleaners for " + serviceType);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listCleaners.setLayoutManager(layoutManager);

        CleanerService cleanerService = new CleanerService(getApplicationContext());
        List<Cleaner> cleaners = cleanerService.getCleaners();
        CleanerAdapter cleanerAdapter = new CleanerAdapter(this, cleaners);
        cleanerAdapter.filterByServiceType(serviceType);
        listCleaners.setAdapter(cleanerAdapter);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        searchCleaner = findViewById(R.id.search_cleaner);
        String finalServiceType = serviceType;
        searchCleaner.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cleanerAdapter.filterByServiceType(finalServiceType);
                cleanerAdapter.filter(newText);

                return false;
            }
        });
    }
}
