package ph.edu.usc.go_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CleanerProfileActivity extends AppCompatActivity {

    private TextView nameTextView, ageTextView, scheduleTextView, addressTextView,
            mobileTextView, attitudeTextView, cleaningQualityTextView, customerSatisfactionTextView;
    ImageButton backButton;
    Button bookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cleaner_profile);

        nameTextView = findViewById(R.id.txt_name);
        ageTextView = findViewById(R.id.txt_age);
        scheduleTextView = findViewById(R.id.txt_schedule_availability);
        addressTextView = findViewById(R.id.txt_address);
        mobileTextView = findViewById(R.id.txt_mobile_number);
        attitudeTextView = findViewById(R.id.txt_attitude);
        cleaningQualityTextView = findViewById(R.id.txt_cleaning_quality);
        customerSatisfactionTextView = findViewById(R.id.txt_customer_satisfaction);

        View cleanerProfileView = findViewById(R.id.cleaner_profile);
        ViewCompat.setOnApplyWindowInsetsListener(cleanerProfileView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(cleanerProfileView.getPaddingLeft() + systemBars.left, cleanerProfileView.getPaddingTop() + systemBars.top, cleanerProfileView.getPaddingRight() + systemBars.right, cleanerProfileView.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        setProfileDetails();

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> {
            finish();
        });

        bookButton = findViewById(R.id.btn_book);
        bookButton.setOnClickListener(v -> {
            Intent intent = new Intent(CleanerProfileActivity.this, BookingActivity.class);
            startActivity(intent);
        });
    }

    private void setProfileDetails() {
        String cleanerName = getIntent().getStringExtra("cleanerName");
        int cleanerAge = getIntent().getIntExtra("cleanerAge", 0);
        String cleanerSchedule = getIntent().getStringExtra("cleanerSchedule");
        String cleanerAddress = getIntent().getStringExtra("cleanerAddress");
        String cleanerMobileNumber = getIntent().getStringExtra("cleanerMobileNumber");
        float cleanerAttitude = getIntent().getFloatExtra("cleanerAttitude", 0f);
        float cleanerCleaningQuality = getIntent().getFloatExtra("cleanerCleaningQuality", 0f);
        float cleanerCustomerSatisfaction = getIntent().getFloatExtra("cleanerCustomerSatisfaction", 0f);

        nameTextView.setText("Name: " + cleanerName);
        ageTextView.setText("Age: " + cleanerAge);
        scheduleTextView.setText("Schedule: " + cleanerSchedule);
        addressTextView.setText("Address: " + cleanerAddress);
        mobileTextView.setText("Mobile: " + cleanerMobileNumber);
        attitudeTextView.setText("Attitude: " + cleanerAttitude);
        cleaningQualityTextView.setText("Cleaning Quality: " + cleanerCleaningQuality);
        customerSatisfactionTextView.setText("Customer Satisfaction: " + cleanerCustomerSatisfaction);
    }
}
