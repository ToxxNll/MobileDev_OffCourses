package com.example.off_courses;

import static android.content.ContentValues.TAG;
import static androidx.navigation.fragment.FragmentKt.findNavController;
import com.example.off_courses.models.Users;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contactPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contactPage extends Fragment {

    Context context;
    Button loginButton,registerButton;
    ConstraintLayout root;

    private Menu menu;
    private DatabaseReference database;
    private FirebaseDatabase db;
    public FirebaseAuth auth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public contactPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contactPage.
     */
    // TODO: Rename and change types and number of parameters
    public static contactPage newInstance(String param1, String param2) {
        contactPage fragment = new contactPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem contactPageMenu = menu.findItem(R.id.contactPage);
        contactPageMenu.setVisible(false);
        MenuItem userPageMenu = menu.findItem(R.id.userPage);
        userPageMenu.setVisible(true);
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

        View view = inflater.inflate(R.layout.fragment_contact_page, container, false);
        root = view.findViewById(R.id.authRoot);


        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference("users");

        
        loginButton = (Button) view.findViewById(R.id.loginButton);
        registerButton = (Button) view.findViewById(R.id.registerButton);


        registerButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterWindow();
            }
        }));


        MaterialEditText loginEdit = view.findViewById(R.id.loginEditer);
        MaterialEditText passwordEdit = view.findViewById(R.id.passwordEditer);
        loginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(loginEdit.getText().toString())){
                    Snackbar.make(root,"Введите вашу почту",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordEdit.getText().toString())){
                    Snackbar.make(root,"Введите пароль",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                //user Registration
                auth.signInWithEmailAndPassword(loginEdit.getText().toString(),passwordEdit.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Navigation.findNavController(view).navigate(R.id.action_contactPage_to_catalogPage);
                                    getActivity().invalidateOptionsMenu();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root,"Ошибка авторизации." + e.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void showRegisterWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите свои данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View registerWindow = inflater.inflate(R.layout.registration_window,null);
        dialog.setView(registerWindow);

        MaterialEditText name = registerWindow.findViewById(R.id.nameField);
        MaterialEditText email = registerWindow.findViewById(R.id.emailField);
        MaterialEditText password = registerWindow.findViewById(R.id.passwordField);
        MaterialEditText passwordConfirm = registerWindow.findViewById(R.id.passConfirmField);
        MaterialEditText phone = registerWindow.findViewById(R.id.phoneField);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(root,"Введите ваше имя",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root,"Введите вашу почту",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())){
                    Snackbar.make(root,"Создайте пароль",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordConfirm.getText().toString())){
                    Snackbar.make(root,"Подтвердите пароль",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(root,"Введите номер сотового",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString() == passwordConfirm.getText().toString()){
                    Snackbar.make(root,"Пароли не совпадают",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //user Registration
                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Users user = new Users();
                                user.setName(name.getText().toString());
                                user.setEmail(email.getText().toString());
                                user.setPassword(password.getText().toString());
                                user.setPhone(phone.getText().toString());

                                database.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Snackbar.make(root,"Пользователь зарегистрирован",Snackbar.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(root,"Ошибка регистрации." + e.getMessage(),Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
            }
        });
    dialog.show();
    }
}