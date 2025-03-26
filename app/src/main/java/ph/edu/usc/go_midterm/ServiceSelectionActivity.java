package ph.edu.usc.go_midterm;

import android.app.Service;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServiceSelectionActivity extends AppCompatActivity {

    ImageButton backButton;
    RecyclerView recyclerView;
    ServiceTypeAdapter serviceTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_selection);

        View curView = findViewById(R.id.service_selection);
        ViewCompat.setOnApplyWindowInsetsListener(curView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(curView.getPaddingLeft() + systemBars.left, curView.getPaddingTop() + systemBars.top, curView.getPaddingRight() + systemBars.right, curView.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> {
            finish();
        });

        recyclerView = findViewById(R.id.list_service_types);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> serviceNames = getIntent().getStringArrayListExtra("services");

        List<ServiceType> serviceList = new ArrayList<>();
        if (serviceNames != null) {
            for (String serviceName : serviceNames) {
                serviceList.add(new ServiceType(serviceName));
            }
        }

        serviceTypeAdapter = new ServiceTypeAdapter(serviceList);
        recyclerView.setAdapter(serviceTypeAdapter);
    }
}
