package moviez.mnf.com.movie.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import moviez.mnf.com.movie.R;
import moviez.mnf.com.movie.UI.PaperButton;
import moviez.mnf.com.movie.tools.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

EditText emailEdt,passwordEdt;
    PaperButton login;
    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment getInstance() {
        String TAG = "logging";

        LoginFragment fragmentInstance = new LoginFragment();
        Bundle bundle = new Bundle();
        //Log.e(TAG,"value of pos is "+pos);

        return  fragmentInstance;

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        emailEdt = (EditText) v.findViewById(R.id.id_uname);
        passwordEdt = (EditText) v.findViewById(R.id.id_password);
        login = (PaperButton) v.findViewById(R.id.id_login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                Log.e("tag","email = "+email+" password = "+password);
            loginUser(email,password);

            }
        });


        return v;
    }

    public void loginUser(String email,String pass){

    }



}
