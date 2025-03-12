package ph.edu.usc.go_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BookingActivity extends AppCompatActivity {

    private EditText serviceAddressEditText, serviceDurationEditText, couponEditText;
    private Spinner serviceItemsSpinner;
    private Button submitBookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);

        View bookingView = findViewById(R.id.booking);
        ViewCompat.setOnApplyWindowInsetsListener(bookingView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bookingView.getPaddingLeft() + systemBars.left, bookingView.getPaddingTop() + systemBars.top, bookingView.getPaddingRight() + systemBars.right, bookingView.getPaddingBottom() + systemBars.bottom);
            return insets;
        });

        serviceAddressEditText = findViewById(R.id.edit_service_address);
        serviceItemsSpinner = findViewById(R.id.spinner_service_items);
        serviceDurationEditText = findViewById(R.id.edit_service_duration);
        couponEditText = findViewById(R.id.edit_coupon);
        submitBookingButton = findViewById(R.id.btn_submit_booking);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        submitBookingButton.setOnClickListener(v -> handleSubmitBooking());
    }

    private void handleSubmitBooking() {
        Toast.makeText(BookingActivity.this, "Booking Complete!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(BookingActivity.this, HomeActivity.class);
        intent.putExtra("message", "Booking complete");
        startActivity(intent);

        finish();
    }
}
