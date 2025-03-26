package ph.edu.usc.go_midterm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategorySmallAdapter extends RecyclerView.Adapter<CategorySmallAdapter.CategorySmallViewHolder> {

    private List<Category> categoryList;
    private List<Category> filteredCategoryList;
    private Context context;

    public CategorySmallAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.filteredCategoryList = new ArrayList<>(categoryList);
        this.context = context;
    }

    @NonNull
    @Override
    public CategorySmallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category_small, parent, false);
        return new CategorySmallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategorySmallViewHolder holder, int position) {
        Category category = filteredCategoryList.get(position);
        holder.categoryName.setText(category.getName());

        holder.itemView.setOnClickListener(v -> openServiceSelection(category));
    }

    @Override
    public int getItemCount() {
        return filteredCategoryList.size();
    }

    private void openServiceSelection(Category category) {
        Intent serviceIntent = new Intent(context, ServiceSelectionActivity.class);
        serviceIntent.putExtra("categoryName", category.getName());
        serviceIntent.putStringArrayListExtra("services", new ArrayList<>(category.getServices()));

        context.startActivity(serviceIntent);
    }


    public void filter(String query) {
        filteredCategoryList.clear();

        if (query.isEmpty()) {
            filteredCategoryList.addAll(categoryList);
        } else {
            for (Category category : categoryList) {
                if (category.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredCategoryList.add(category);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class CategorySmallViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;

        public CategorySmallViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.txt_category_name);
        }
    }
}
