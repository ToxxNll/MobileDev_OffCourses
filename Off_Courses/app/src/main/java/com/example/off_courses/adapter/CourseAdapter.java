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

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    DatabaseReference coursesRef;
    Context context;
    List<Course> courses;

    public CourseAdapter(Context context) {
        this.context = context;
        coursesRef = FirebaseDatabase.getInstance().getReference().child("courses");
        FirebaseRecyclerOptions<Course> options = new FirebaseRecyclerOptions.Builder<Course>()
                .setQuery(coursesRef,Course.class).build();
        FirebaseRecyclerAdapter<Course, CourseViewHolder> adapter = new FirebaseRecyclerAdapter<Course, CourseAdapter.CourseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position, @NonNull Course course) {
                int imageId = context.getResources().getIdentifier("ic_"+course.getImg(),"drawable",context.getPackageName());
                holder.courseImg.setImageResource(imageId);
                holder.courseTitle.setText(course.getTitle());
                holder.courseDate.setText(course.getDate());
                holder.courseLevel.setText(course.getLevel());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment fragment = new Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("courseImg", imageId);
                        bundle.putString("courseTitle",course.getTitle());
                        bundle.putString("courseDate",course.getDate());
                        bundle.putString("courseLevel",course.getLevel());
                        fragment.setArguments(bundle);

                        Intent intent = new Intent(context, coursePageActivity.class);

                        intent.putExtra("courseImg",imageId);
                        intent.putExtra("courseTitle",course.getTitle());
                        intent.putExtra("courseDate",course.getDate());
                        intent.putExtra("courseLevel",course.getLevel());
                        intent.putExtra("courseDescriptionText",course.getDescription_text());

                        context.startActivity(intent);

//                Navigation.findNavController(view).navigate(R.id.action_catalogPage_to_coursePage);
                    }
                });
            }

            @NonNull
            @Override
            public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item,parent,false);
                return new CourseAdapter.CourseViewHolder(courseItems);
            }
        };

    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
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
