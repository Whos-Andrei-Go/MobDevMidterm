package ph.edu.usc.go_midterm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {

    private EditText serviceAddressEditText, serviceDateEditText;
    private Spinner serviceItemsSpinner;
    private Button submitBookingButton;
    private ImageView cleanerProfileImageView;
    private TextView cleanerNameTextView;

    private CleanerService cleanerService;
    private Cleaner currentCleaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);

        cleanerService = new CleanerService(getApplicationContext());

        View bookingView = findViewById(R.id.booking);
        ViewCompat.setOnApplyWindowInsetsListener(bookingView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bookingView.getPaddingLeft() + systemBars.left,
                    bookingView.getPaddingTop() + systemBars.top,
                    bookingView.getPaddingRight() + systemBars.right,
                    bookingView.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        cleanerProfileImageView = findViewById(R.id.img_cleaner);
        cleanerNameTextView = findViewById(R.id.txt_cleaner_name);
        serviceAddressEditText = findViewById(R.id.edit_service_address);
        serviceItemsSpinner = findViewById(R.id.spinner_service_items);
        serviceDateEditText = findViewById(R.id.edit_service_date);
        submitBookingButton = findViewById(R.id.btn_submit_booking);

        int cleanerId = getIntent().getIntExtra("cleanerId", -1);

        currentCleaner = cleanerService.getCleanerById(cleanerId);

        if (currentCleaner != null) {
            cleanerNameTextView.setText(currentCleaner.getName());
            cleanerProfileImageView.setImageResource(R.drawable.pfp_default);
            ArrayList<String> serviceTypes = new ArrayList<>(List.of(currentCleaner.getServiceTypes()));

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, serviceTypes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            serviceItemsSpinner.setAdapter(adapter);
        }

        serviceDateEditText.setOnClickListener(v -> showDatePickerDialog());

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        submitBookingButton.setOnClickListener(v -> handleSubmitBooking());
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            serviceDateEditText.setText(date);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void handleSubmitBooking() {
        String serviceAddress = serviceAddressEditText.getText().toString().trim();
        String serviceDate = serviceDateEditText.getText().toString().trim();
        String selectedServiceItem = serviceItemsSpinner.getSelectedItem().toString();

        if (serviceAddress.isEmpty()) {
            Toast.makeText(this, "Please enter a service address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (serviceDate.isEmpty()) {
            Toast.makeText(this, "Please select a service date", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean bookingSuccess = bookService(serviceAddress, serviceDate, selectedServiceItem);

        if (bookingSuccess) {
            Toast.makeText(BookingActivity.this, "Booking Complete!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(BookingActivity.this, HomeActivity.class);
            intent.putExtra("message", "Booking complete");
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(BookingActivity.this, "Booking failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean bookService(String address, String date, String serviceItem) {
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("address", address);
        bookingDetails.put("date", date);
        bookingDetails.put("serviceItem", serviceItem);
        return true;
    }
}
