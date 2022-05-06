package com.example.off_courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class coursePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_course_page);

        ImageView courseImg1 = findViewById(R.id.courseImg1);
        TextView courseDate = findViewById(R.id.courseDate);
        TextView courseLevel = findViewById(R.id.courseLevel);
        TextView coursePageInfo = findViewById(R.id.coursePageInfo);
        ImageView thumbnailCourseImg = findViewById(R.id.thumbnailCourseImg);
        TextView thumbnailCourseName = findViewById(R.id.thumbnailCourseName);

        courseImg1.setImageResource(getIntent().getIntExtra("courseImg",0));
        courseDate.setText(getIntent().getStringExtra("courseDate"));
        courseLevel.setText(getIntent().getStringExtra("courseLevel"));
        coursePageInfo.setText(getIntent().getStringExtra("courseDescriptionText"));
        thumbnailCourseImg.setImageResource(getIntent().getIntExtra("courseImg",0));
        thumbnailCourseName.setText(getIntent().getStringExtra("courseTitle"));


    }
}