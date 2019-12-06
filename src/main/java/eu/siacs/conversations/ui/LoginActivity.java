package eu.siacs.conversations.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.webrtc.voiceengine.BuildInfo;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoUser;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoRequestListener;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import eu.siacs.conversations.R;
import nexmo.webrtcinit;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUiAccordingToEnabledFeatures();

//        org.webrtc.voiceengine.BuildInfo b = new  org.webrtc.voiceengine.BuildInfo();

        //NexmoClient.Builder().build(getApplicationContext());
        //new webrtcinit().init();
        NexmoHelper.init(getApplicationContext());
    }

    public void onLoginJaneClick(View view) {
        if (GetJWTForUser("JWT_JANE")) return;

        loginToSdk(NexmoHelper.JWT_JANE);

    }

    private boolean GetJWTForUser(String username) {
        //Some url endpoint that you may have
        String myUrl = "http://aurorascienceexploration.com:8000/generateJwt?user=" + username;
        //String to place our result in
        String result;
        //Instantiate new instance of our class
        HttpGetRequest getRequest = new HttpGetRequest();

        //Perform the doInBackground method, passing in our url
        try {
            result = getRequest.execute(myUrl).get();

            Log.d(NexmoHelper.TAG, "NexmoLoginListener.Login remote web request "+ result);

            loginToSdk(result);

            return true;
        }catch (Exception eex)
        {
            Log.e(NexmoHelper.TAG, "NexmoLoginListener.Login remote web request "+ eex.toString());
        }
        return false;
    }

    public void onLoginJoeClick(View view) {
        if (GetJWTForUser("JWT_JOE")) return;
        loginToSdk(NexmoHelper.JWT_JOE);
    }

    private void loginToSdk(String token) {
        NexmoClient.get().login(token, new NexmoRequestListener<NexmoUser>() {

            @Override
            public void onError(NexmoApiError nexmoApiError) {
                notifyError(nexmoApiError);
            }

            @Override
            public void onSuccess(NexmoUser user) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void setUiAccordingToEnabledFeatures() {
        Button btnLoginJoe = findViewById(R.id.btnLoginJoe);
        List<NexmoHelper.Features> featuresList = Arrays.asList(NexmoHelper.enabledFeatures);
        btnLoginJoe.setVisibility(featuresList.contains(NexmoHelper.Features.IN_APP_to_IN_APP)? View.VISIBLE:View.GONE);
    }

}
