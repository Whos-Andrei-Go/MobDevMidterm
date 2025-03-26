package ph.edu.usc.go_midterm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CleanerAdapter extends RecyclerView.Adapter<CleanerAdapter.CleanerViewHolder> {
    private List<Cleaner> cleanerList;
    private List<Cleaner> filteredCleanerList;
    private final Context context;

    public CleanerAdapter(Context context, List<Cleaner> cleanerList) {
        this.context = context;
        this.cleanerList = cleanerList;
        this.filteredCleanerList = new ArrayList<>(cleanerList);
    }

    @Override
    public CleanerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cleaner, parent, false);
        return new CleanerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CleanerViewHolder holder, int position) {
        Cleaner cleaner = filteredCleanerList.get(position);
        holder.name.setText(cleaner.getName());
        holder.age.setText("Age: " + cleaner.getAge());
        holder.schedule.setText("Schedule: " + cleaner.getScheduleAvailability());
        holder.rating.setText("Rating: " + cleaner.getRating());

        holder.itemView.setOnClickListener(v -> openCleanerProfile(cleaner.getId()));
    }

    @Override
    public int getItemCount() {
        return filteredCleanerList.size();
    }

    private void openCleanerProfile(int cleanerId) {
        Intent profileIntent = new Intent(context, CleanerProfileActivity.class);
        profileIntent.putExtra("cleanerId", cleanerId);
        context.startActivity(profileIntent);
    }


    public void filter(String query) {
        List<Cleaner> tempList = new ArrayList<>(filteredCleanerList);
        filteredCleanerList.clear();

        if (query.isEmpty()) {
            filteredCleanerList.addAll(tempList);
        } else {
            for (Cleaner cleaner : tempList) {
                if (cleaner.getName().toLowerCase().contains(query.toLowerCase()) ||
                        cleaner.getScheduleAvailability().toLowerCase().contains(query.toLowerCase()) ||
                        String.valueOf(cleaner.getRating()).contains(query)) {
                    filteredCleanerList.add(cleaner);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filterByServiceType(String query) {
        filteredCleanerList.clear();

        if (query.isEmpty()) {
            filteredCleanerList.addAll(cleanerList);
        } else {
            for (Cleaner cleaner : cleanerList) {
                for (String serviceType : cleaner.getServiceTypes()) {
                    if (serviceType.toLowerCase().contains(query.toLowerCase())) {
                        filteredCleanerList.add(cleaner);
                        break;
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class CleanerViewHolder extends RecyclerView.ViewHolder {
        TextView age, schedule, rating, name;

        public CleanerViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            age = itemView.findViewById(R.id.txt_age);
            schedule = itemView.findViewById(R.id.txt_schedule_availability);
            rating = itemView.findViewById(R.id.txt_rating);
        }
    }
}
