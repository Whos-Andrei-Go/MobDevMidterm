package ph.edu.usc.go_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CleanerProfileActivity extends AppCompatActivity {

    private TextView nameTextView, ageTextView, scheduleTextView, addressTextView,
            mobileTextView, attitudeTextView, cleaningQualityTextView, customerSatisfactionTextView;

    private ProgressBar attitudeProgressBar, cleaningQualityProgressBar, customerSatisfactionProgressBar;
    private ImageButton backButton;
    private Button bookButton;
    CleanerService cleanerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cleaner_profile);

        cleanerService = new CleanerService(getApplicationContext());

        nameTextView = findViewById(R.id.txt_name);
        ageTextView = findViewById(R.id.txt_age);
        scheduleTextView = findViewById(R.id.txt_schedule_availability);
        addressTextView = findViewById(R.id.txt_address);
        mobileTextView = findViewById(R.id.txt_mobile_number);
        attitudeTextView = findViewById(R.id.txt_attitude);
        cleaningQualityTextView = findViewById(R.id.txt_cleaning_quality);
        customerSatisfactionTextView = findViewById(R.id.txt_customer_satisfaction);

        attitudeProgressBar = findViewById(R.id.progress_attitude);
        cleaningQualityProgressBar = findViewById(R.id.progress_cleaning_quality);
        customerSatisfactionProgressBar = findViewById(R.id.progress_customer_satisfaction);

        View cleanerProfileView = findViewById(R.id.cleaner_profile);
        ViewCompat.setOnApplyWindowInsetsListener(cleanerProfileView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(cleanerProfileView.getPaddingLeft() + systemBars.left,
                    cleanerProfileView.getPaddingTop() + systemBars.top,
                    cleanerProfileView.getPaddingRight() + systemBars.right,
                    cleanerProfileView.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        setProfileDetails();

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish());

        bookButton = findViewById(R.id.btn_book);
        bookButton.setOnClickListener(v -> {
            openBookingPage();
        });
    }

    private void openBookingPage() {
        int cleanerId = getIntent().getIntExtra("cleanerId", -1);

        Intent intent = new Intent(CleanerProfileActivity.this, BookingActivity.class);
        intent.putExtra("cleanerId", cleanerId);

        startActivity(intent);
    }


    private void setProfileDetails() {
        int cleanerId = getIntent().getIntExtra("cleanerId", -1);
        if (cleanerId != -1) {
            Cleaner cleaner = cleanerService.getCleanerById(cleanerId);

            if (cleaner != null) {
                nameTextView.setText("Name: " + cleaner.getName());
                ageTextView.setText("Age: " + cleaner.getAge());
                scheduleTextView.setText("Schedule: " + cleaner.getScheduleAvailability());
                addressTextView.setText("Address: " + cleaner.getAddress());
                mobileTextView.setText("Mobile: " + cleaner.getMobileNumber());

                Cleaner.CapabilityIndex capability = cleaner.getCapabilityIndex();
                attitudeTextView.setText("Attitude: " + capability.getAttitude() + " participation");
                cleaningQualityTextView.setText("Cleaning Quality: " + capability.getCleaningQuality() + " participation");
                customerSatisfactionTextView.setText("Customer Satisfaction: " + capability.getCustomerSatisfaction() + " participation");

                attitudeProgressBar.setProgress(capability.getAttitude());
                cleaningQualityProgressBar.setProgress(capability.getCleaningQuality());
                customerSatisfactionProgressBar.setProgress(capability.getCustomerSatisfaction());
            }
        }
    }
}
