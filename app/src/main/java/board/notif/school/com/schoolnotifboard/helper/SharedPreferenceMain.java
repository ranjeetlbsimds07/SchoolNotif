package board.notif.school.com.schoolnotifboard.helper;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Guest User on 1/30/2018.
 */

public class SharedPreferenceMain {

    private Context context;
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "prefRead";

    private final String EMAIL = "email";
    private final String FIRSTNAME = "firstName";
    private final static String LOGIN_RESPONSE_CUSTOMER_DATA = "LOGIN_RESPONSE_CUSTOMER_DATA";
    private final String SELECTLANGUAGE = "selectLanguage";

    public SharedPreferenceMain(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.commit();
    }

  /*  public void setLoginCustomerData(Context context, LoginUserResponseModel customerData) {
        Gson gson = new Gson();
        String data = gson.toJson(customerData);
        editor.putString(LOGIN_RESPONSE_CUSTOMER_DATA, data);
        editor.commit();
    }

    public LoginUserResponseModel getLoginCustomerData(Context context) {
        Gson gson = new Gson();
        String data = pref.getString(LOGIN_RESPONSE_CUSTOMER_DATA, "");
        if (data.length() > 0) {
            LoginUserResponseModel customerData = gson.fromJson(data, LoginUserResponseModel.class);
            return customerData;
        }
        return new LoginUserResponseModel();
    }
*/
    // Email
    public void setEmail(String email) {
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public String getEmail() {
        String email;
        email = pref.getString(EMAIL, "");
        return email;
    }

    // first name
    public void setFirstName(String firstName) {
        editor.putString(FIRSTNAME, firstName);
        editor.commit();
    }

    public String getFirstName() {
        String firstName;
        firstName = pref.getString(FIRSTNAME, "");
        return firstName;
    }
    // select Language
    public void setSelectLanguage(String selectLanguage) {
        editor.putString(SELECTLANGUAGE, selectLanguage);
        editor.commit();
    }

    public String getSelectLanguage() {
        String selectLanguage;
        selectLanguage = pref.getString(SELECTLANGUAGE, "");
        return selectLanguage;
    }
}