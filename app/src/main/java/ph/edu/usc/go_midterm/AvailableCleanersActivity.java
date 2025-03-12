package ph.edu.usc.go_midterm;

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

import java.util.ArrayList;
import java.util.List;

public class AvailableCleanersActivity extends AppCompatActivity {

    private TextView headerTextView;
    private RecyclerView listCleaners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_available_cleaners);

        View curView = findViewById(R.id.available_cleaners);
        ViewCompat.setOnApplyWindowInsetsListener(curView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(curView.getPaddingLeft() + systemBars.left, curView.getPaddingTop() + systemBars.top, curView.getPaddingRight() + systemBars.right, curView.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        headerTextView = findViewById(R.id.txt_header);
        listCleaners = findViewById(R.id.list_cleaners);

        String serviceType = getIntent().getStringExtra("serviceType");
        headerTextView.setText("Available Cleaners for " + serviceType);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listCleaners.setLayoutManager(layoutManager);

        List<Cleaner> cleaners = getCleanersForService(serviceType);
        CleanerAdapter cleanerAdapter = new CleanerAdapter(this, cleaners);
        listCleaners.setAdapter(cleanerAdapter);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    private List<Cleaner> getCleanersForService(String serviceType) {
        List<Cleaner> cleaners = new ArrayList<>();

        Cleaner.CapabilityIndex capabilityIndex1 = new Cleaner.CapabilityIndex(4.5f, 4.8f, 4.7f);
        Cleaner.CapabilityIndex capabilityIndex2 = new Cleaner.CapabilityIndex(4.3f, 4.6f, 4.8f);
        Cleaner.CapabilityIndex capabilityIndex3 = new Cleaner.CapabilityIndex(4.6f, 4.9f, 4.5f);

        if (serviceType.equals("Cleaning")) {
            cleaners.add(new Cleaner("John Doe", 30, "123 Main St", "123-456-7890", 4.5f, "Monday - Friday", capabilityIndex1));
            cleaners.add(new Cleaner("Jane Smith", 28, "456 Oak St", "123-456-7891", 4.8f, "Weekends", capabilityIndex2));
        } else if (serviceType.equals("Aircon Service")) {
            cleaners.add(new Cleaner("David Lee", 35, "789 Pine St", "123-456-7892", 4.7f, "Monday - Saturday", capabilityIndex3));
        } else if (serviceType.equals("Car Wash")) {
            cleaners.add(new Cleaner("Mike Johnson", 32, "123 Elm St", "123-456-7893", 4.6f, "Weekdays", capabilityIndex1));
        } else if (serviceType.equals("Babysitting")) {
            cleaners.add(new Cleaner("Emily White", 27, "321 Oak St", "123-456-7894", 4.9f, "Flexible Hours", capabilityIndex2));
        }

        return cleaners;
    }
}
