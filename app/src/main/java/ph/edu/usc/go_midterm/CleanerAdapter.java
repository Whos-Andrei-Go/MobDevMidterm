package ph.edu.usc.go_midterm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CleanerAdapter extends RecyclerView.Adapter<CleanerAdapter.CleanerViewHolder> {
    private List<Cleaner> cleanerList;
    private final Context context;

    public CleanerAdapter(Context context, List<Cleaner> cleanerList) {
        this.context = context;
        this.cleanerList = cleanerList;
    }

    @Override
    public CleanerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cleaner, parent, false);
        return new CleanerViewHolder(view);


    }

    @Override
    public void onBindViewHolder(CleanerViewHolder holder, int position) {
        Cleaner cleaner = cleanerList.get(position);
        holder.age.setText("Age: " + cleaner.getAge());
        holder.schedule.setText("Schedule: " + cleaner.getScheduleAvailability());
        holder.rating.setText("Rating: " + cleaner.getRating());

        holder.itemView.setOnClickListener(v -> openCleanerProfile(cleaner));
    }

    @Override
    public int getItemCount() {
        return cleanerList.size();
    }

    private void openCleanerProfile(Cleaner cleaner) {
        Intent profileIntent = new Intent(context, CleanerProfileActivity.class);

        profileIntent.putExtra("cleanerName", cleaner.getName());
        profileIntent.putExtra("cleanerAge", cleaner.getAge());
        profileIntent.putExtra("cleanerRating", cleaner.getRating());
        profileIntent.putExtra("cleanerSchedule", cleaner.getScheduleAvailability());
        profileIntent.putExtra("cleanerAddress", cleaner.getAddress());
        profileIntent.putExtra("cleanerMobileNumber", cleaner.getMobileNumber());

        Cleaner.CapabilityIndex capability = cleaner.getCapabilityIndex();
        profileIntent.putExtra("cleanerAttitude", capability.getAttitude());
        profileIntent.putExtra("cleanerCleaningQuality", capability.getCleaningQuality());
        profileIntent.putExtra("cleanerCustomerSatisfaction", capability.getCustomerSatisfaction());

        context.startActivity(profileIntent);
    }

    public static class CleanerViewHolder extends RecyclerView.ViewHolder {
        TextView age, schedule, rating;

        public CleanerViewHolder(View itemView) {
            super(itemView);
            age = itemView.findViewById(R.id.txt_age);
            schedule = itemView.findViewById(R.id.txt_schedule_availability);
            rating = itemView.findViewById(R.id.txt_rating);
        }
    }
}

