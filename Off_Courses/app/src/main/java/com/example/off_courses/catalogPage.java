package com.example.off_courses;

import static com.example.off_courses.catalogPage.Global.courseRecycler;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment   ;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.off_courses.adapter.CourseAdapter;
import com.example.off_courses.adapter.CourseAdapterOld;
import com.example.off_courses.adapter.CourseFilterAdapter;
import com.example.off_courses.models.Course;
import com.example.off_courses.models.CourseFilter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link catalogPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class catalogPage extends Fragment {

    Course course;
    DatabaseReference coursesRef;
    Context context;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public catalogPage() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment catalogPage.
     */
    // TODO: Rename and change types and number of parameters
    public static catalogPage newInstance(String param1, String param2) {
        catalogPage fragment = new catalogPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static class Global{
        public static RecyclerView courseRecycler;
        public static RecyclerView filterRecycler;
        public static CourseAdapterOld courseAdapter;
        public static CourseFilterAdapter courseFilterAdapter;


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        coursesRef = FirebaseDatabase.getInstance().getReference().child("courses");
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),RecyclerView.VERTICAL,false);
//
//
//        Global.courseRecycler.setLayoutManager(layoutManager);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_catalog_page, container, false);

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(0,"java","Профессия Java\nразработчика", "9 Марта","Intermediate","Our educational center is aimed at teaching the Java programming language. The course is designed to teach Java in different areas of development"));
        courseList.add(new Course(1,"python","Профессия Python\nразработчика", "31 Декабря","Beginner","Our educational center is aimed at teaching the Python programming language. The course is designed to teach Python in different areas of development"));
        courseList.add(new Course(2,"unity","Профессия Unity\nразработчика", "30 Февраля","Advanced","Our educational center is aimed at teaching the Unity programming language. The course is designed to teach Unity in different areas of development"));
        courseList.add(new Course(3,"node_js","Профессия Node-js\nразработчика", "3 лАпреля","Beginner","Our educational center is aimed at teaching the Node-js programming language. The course is designed to teach Node-js in different areas of development"));

        List<CourseFilter> cFilterList = new ArrayList<>();
        cFilterList.add(new CourseFilter(1,"Дата начала"));
        cFilterList.add(new CourseFilter(2,"Уровень"));

//        setCourseRecycler(courseList,view);


//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),RecyclerView.VERTICAL,false);
//
//        Global.courseRecycler = view.findViewById(R.id.courseRecyclerXml);
//        courseRecycler.setLayoutManager(layoutManager);


        filterButtonAction(view,cFilterList);
        setFilterRecycler(cFilterList,view);


        initSearchWidgets(courseList,view);

        return view;


    }



    private void filterButtonAction(View view, List<CourseFilter> cFilterList){

        Button filterB = view.findViewById(R.id.filterBig);
        Button filterS = view.findViewById(R.id.filterSmall);
        ConstraintLayout filterMenu = view.findViewById(R.id.filterMenu);
        SearchView filterSearchView = view.findViewById(R.id.filterSearcher);
        filterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                filterMenu.setVisibility(View.VISIBLE);
                filterSearchView.setVisibility(View.VISIBLE);
                filterB.setVisibility(View.GONE);
            }
        });
        filterS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                filterMenu.setVisibility(View.GONE);
                filterSearchView.setVisibility(View.GONE);
                filterB.setVisibility(View.VISIBLE);
            }
        });

    }

    private void setCourseRecycler (List<Course> courseList,View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),RecyclerView.VERTICAL,false);

        Global.courseRecycler = view.findViewById(R.id.courseRecyclerXml);
        Global.courseRecycler.setLayoutManager(layoutManager);

        Global.courseAdapter = new CourseAdapterOld(this.getActivity(),courseList);
//        Global.courseAdapter = new CourseAdapter(this.getActivity());
        Global.courseRecycler.setAdapter(Global.courseAdapter);

    }

    private void setFilterRecycler(List<CourseFilter> cFilerList, View view){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),RecyclerView.HORIZONTAL,false);
        Global.filterRecycler = view.findViewById(R.id.filterRecycler);
        Global.filterRecycler.setLayoutManager(layoutManager);

        Global.courseFilterAdapter = new CourseFilterAdapter(this.getActivity(),cFilerList);
        Global.filterRecycler.setAdapter(Global.courseFilterAdapter);

    }

    private void initSearchWidgets(List<Course> courseList,View view){
        SearchView courseSearchView = view.findViewById(R.id.courseSearcher);

        courseSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Course> filteredCourses = new ArrayList<Course>();

                for (Course course: courseList){
                    if (course.getTitle().toLowerCase(Locale.ROOT).contains(s.toLowerCase(Locale.ROOT))){
                        filteredCourses.add(course);
                    }
                }
                setCourseRecycler(filteredCourses,view);
                return false;
            }
        });
        setCourseRecycler(courseList,view);
    }





}