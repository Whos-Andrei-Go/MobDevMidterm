package ph.edu.usc.go_midterm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceTypeAdapter extends RecyclerView.Adapter<ServiceTypeAdapter.ServiceTypeViewHolder> {

    private List<ServiceType> serviceTypes;

    public ServiceTypeAdapter(List<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    @Override
    public ServiceTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_service_type, parent, false);
        return new ServiceTypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServiceTypeViewHolder holder, int position) {
        ServiceType serviceType = serviceTypes.get(position);

        holder.serviceTypeTextView.setText("Service: " + serviceType.getServiceName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AvailableCleanersActivity.class);
            intent.putExtra("serviceType", serviceType.getServiceName());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return serviceTypes.size();
    }

    public static class ServiceTypeViewHolder extends RecyclerView.ViewHolder {
        public TextView serviceTypeTextView;

        public ServiceTypeViewHolder(View itemView) {
            super(itemView);
            serviceTypeTextView = itemView.findViewById(R.id.txt_service_type);
        }
    }
}
