package com.example.off_courses.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.off_courses.CoursePage;
import com.example.off_courses.R;
import com.example.off_courses.coursePageActivity;
import com.example.off_courses.models.Course;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CourseAdapterOld extends RecyclerView.Adapter<CourseAdapterOld.CourseViewHolder> {
    Context context;
    List<Course> courses;

    public CourseAdapterOld(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }



    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item,parent,false);
        return new CourseAdapterOld.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int imageId = context.getResources().getIdentifier("ic_"+courses.get(position).getImg(),"drawable",context.getPackageName());
        holder.courseImg.setImageResource(imageId);

        holder.courseTitle.setText(courses.get(position).getTitle());
        holder.courseDate.setText(courses.get(position).getDate());
        holder.courseLevel.setText(courses.get(position).getLevel());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putInt("courseImg", imageId);
                bundle.putString("courseTitle",courses.get(position).getTitle());
                bundle.putString("courseDate",courses.get(position).getDate());
                bundle.putString("courseLevel",courses.get(position).getLevel());
                fragment.setArguments(bundle);

                Intent intent = new Intent(context, coursePageActivity.class);

                intent.putExtra("courseImg",imageId);
                intent.putExtra("courseTitle",courses.get(position).getTitle());
                intent.putExtra("courseDate",courses.get(position).getDate());
                intent.putExtra("courseLevel",courses.get(position).getLevel());
                intent.putExtra("courseDescriptionText",courses.get(position).getDescription_text());

                context.startActivity(intent);

//                Navigation.findNavController(view).navigate(R.id.action_catalogPage_to_coursePage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder{

        public ImageView courseImg;
        public TextView courseTitle,courseDate,courseLevel,courseDescription;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseImg = itemView.findViewById(R.id.courseImg);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDate = itemView.findViewById(R.id.courseDate);
            courseLevel = itemView.findViewById(R.id.courseLevel);
        }
    }



}
