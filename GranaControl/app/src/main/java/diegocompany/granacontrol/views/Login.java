package diegocompany.granacontrol.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.utils.ActivityUtil;

public class Login extends ActivityUtil {

    CallbackManager callbackManager;

    private AccessTokenTracker fbTracker;

    private CardView cardEntrar = null;
    private Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(R.string.granaControl);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, facebookCallback());

        cardEntrar = (CardView) findViewById(R.id.cardEntrar);
        btEntrar = (Button) findViewById(R.id.buttonEntrar);
        btEntrar.setOnClickListener(onClickEntrar());

        if (profile == null) {
            cardEntrar.setVisibility(View.GONE);
        }

        fbTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                if (accessToken2 == null) {
                    cardEntrar.setVisibility(View.GONE);
                    alert(R.string.despedidaLogout, Toast.LENGTH_SHORT);
                }
            }
        };
    }

    private FacebookCallback<LoginResult> facebookCallback() {

        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {

                            String name = object.getString("name");
                            String id = object.getString("id");

                            Intent intent = new Intent(getContext(), Diario.class);
                            //Bundle params = new Bundle();
                            //params.putString("id", id);
                            //intent.putExtras(params);
                            startActivity(intent);

                            cardEntrar.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("TAG", "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("TAG", "onError="+error);
            }
        };

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_informacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuInformacao) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.dialog_info, null));

            AlertDialog dialog = builder.create();
            dialog.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener onClickEntrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Diario.class);
                startActivity(intent);
            }
        };
    }
}
