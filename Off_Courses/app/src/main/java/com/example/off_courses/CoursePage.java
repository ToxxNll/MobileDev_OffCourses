package com.example.off_courses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.off_courses.adapter.CourseAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursePage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static class Global {
        public static RecyclerView courseRecycler;
        public static CourseAdapter courseAdapter;
    }

    public CoursePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoursePage.
     */


    // TODO: Rename and change types and number of parameters
    public static CoursePage newInstance(String param1, String param2) {
        CoursePage fragment = new CoursePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_page, container, false);

        ImageView thumbnailCourseImg = view.findViewById(R.id.thumbnailCourseImg);
        TextView courseTitle = view.findViewById(R.id.thumbnailCourseName);
        TextView courseDate = view.findViewById(R.id.courseDate);
        TextView courseLevel = view.findViewById(R.id.courseLevel);
        //Description
        //Head photos


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int img_pos = bundle.getInt("courseImg", 0);
            thumbnailCourseImg.setImageResource(img_pos);
            courseTitle.setText(bundle.getString("courseTitle"));
            courseDate.setText(bundle.getString("courseDate"));
            courseLevel.setText(bundle.getString("courseLevel"));
        }
        return view;

    }
}