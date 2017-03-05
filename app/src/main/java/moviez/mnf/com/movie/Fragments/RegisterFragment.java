package moviez.mnf.com.movie.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;

import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.UI.PaperButton;
import moviez.mnf.com.movie.tools.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
EditText emailEdt,passwordEdt;
    PaperButton regBtn;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        emailEdt = (EditText) v.findViewById(R.id.sid_uname);
        passwordEdt = (EditText) v.findViewById(R.id.sid_password);
        regBtn = (PaperButton) v.findViewById(R.id.sid_register);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                registerUser(email,password);

            }
        });

        return v;
    }

    public void registerUser(String email, String password){

    }



}
