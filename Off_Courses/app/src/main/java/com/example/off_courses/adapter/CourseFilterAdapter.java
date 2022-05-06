package com.example.off_courses.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.off_courses.R;
import com.example.off_courses.models.CourseFilter;

import java.util.List;

public class CourseFilterAdapter extends RecyclerView.Adapter<CourseFilterAdapter.CourseFilterViewHolder> {

    Context context;
    List<CourseFilter> filters;

    public CourseFilterAdapter(Context context, List<CourseFilter> filters){
        this.context = context;
        this.filters = filters;
    }

    @NonNull
    @Override
    public CourseFilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cFilterItems = LayoutInflater.from(context).inflate(R.layout.course_filter_item,parent,false);
        return new CourseFilterAdapter.CourseFilterViewHolder(cFilterItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseFilterViewHolder holder, int position) {
        holder.CourseFilterName.setText(filters.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }


    public static final class CourseFilterViewHolder extends RecyclerView.ViewHolder{

        TextView CourseFilterName;

        public CourseFilterViewHolder(@NonNull View itemView) {
            super(itemView);

            CourseFilterName = itemView.findViewById(R.id.CourseFilterName);
        }
    }

}
