package moviez.mnf.com.movie.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Muneef on 02/11/15.
 */
public class PreferensHandler {
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context c;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "settings_pref";
    final String user_login = "usrLogin";

    @SuppressLint("CommitPrefEdits")
    public PreferensHandler(Context context) {
        this.c = context;
        pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setUserLoginOrNot(Boolean var){
        editor.putBoolean(user_login, var);
        editor.commit();
    }

    public Boolean getUserLoginOrNot(){
        return pref.getBoolean(user_login, false);
    }



}
