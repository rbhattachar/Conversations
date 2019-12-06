package eu.siacs.conversations.ui;

import android.content.Context;
import android.util.Log;

import com.nexmo.client.NexmoCall;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoConnectionState;
import com.nexmo.client.request_listener.NexmoConnectionListener;

import java.lang.ref.WeakReference;

class NexmoHelper {

    public static final String TAG = "Nexmo-get-started";

    public static Features[] enabledFeatures = {Features.IN_APP_to_IN_APP, Features.PHONE_to_IN_APP, Features.IN_APP_to_PHONE};

    enum Features {IN_APP_to_IN_APP, PHONE_to_IN_APP, IN_APP_to_PHONE}

    static final String USER_NAME_JANE = "JWT_JANE";
    private static final String USER_NAME_JOE = "Joe";
    private static final String USER_ID_JANE = "USR-6189b014-b06e-4dc9-9e94-d95e32840a3b"; //TODO: swap with the UserId you generated for Jane
    private static final String USER_ID_JOE = "USR-XXX"; //TODO: swap with the UserId you generated for Joe
    static final String JWT_JANE = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1NzA3NjE3MjEsImp0aSI6ImIzZTE0MjgwLWViZDAtMTFlOS1hMjFhLTRiYmI0OTQ4YjQ2NCIsInN1YiI6ImNsX3VzZXIxIiwiZXhwIjoxNTcwODQ4MTIyLCJhY2wiOnsicGF0aHMiOnsiLyovdXNlcnMvKioiOnt9LCIvKi9jb252ZXJzYXRpb25zLyoqIjp7fSwiLyovc2Vzc2lvbnMvKioiOnt9LCIvKi9kZXZpY2VzLyoqIjp7fSwiLyovaW1hZ2UvKioiOnt9LCIvKi9tZWRpYS8qKiI6e30sIi8qL2FwcGxpY2F0aW9ucy8qKiI6e30sIi8qL3B1c2gvKioiOnt9LCIvKi9rbm9ja2luZy8qKiI6e319fX0.QqUZLe30SrQ09FSQdEhCxBvfeA5D5hqF4HVu9rruYwNnr7UiLbKIDbsc0KQ6GJg6Fdhbz6ac3f8BVqxF06LwH-or0x92osGb58Thh0_bKT1dznib5vyJ-cVQQHk74cwFPLDmGvnAOO6X5rszE88TOR0DOgPZhtE8b8A38pY_boNQ-hxZWhb4IY4ZMcG_AR_Vft0foxHSzK7ZJw4X97TQXZoCTyuaRAR8Pny01nymvuvbHOj8BPQb_Zo5f6Cm5feycEQwkM6AoYQ-n4Mz5QawTs0y30vTdYqY4pk-R_hv_kvZVnys0HkAYjwGUZFg7PNJm-WZFLlkDkPX9yZImDxw4g";//TODO: swap with the JWT you generated for Jane
    static final String JWT_JOE = "PLACEHOLDER"; //TODO: swap with the JWT you generated for Joe

    public static NexmoCall currentCall;
    private static WeakReference<Context> contextRef;
    private static boolean didInit;

/*
    static NexmoLoginListener loginListener = new NexmoLoginListener() {
        @Override
        public void onLoginStateChange(NexmoLoginListener.ELoginState eLoginState, NexmoLoginListener.ELoginStateReason eLoginStateReason) {
            Log.d(TAG, "NexmoLoginListener.onLoginStateChange "+ eLoginState +":"+ eLoginStateReason);
        }

        @Override
        public void onAvailabilityChange(NexmoLoginListener.EAvailability eAvailability, NexmoConnectionState nexmoConnectionState) {
            Log.d(TAG, "NexmoLoginListener.onAvailabilityChange "+ eAvailability +":"+ nexmoConnectionState);
        }
    };
*/
    public static void init(Context appContext) {
        if (didInit) {
            return;
        }
        didInit = true;
        new NexmoClient.Builder()
                .build(appContext)
                .setConnectionListener(new NexmoConnectionListener() {
                    @Override
                    public void onConnectionStatusChange(ConnectionStatus status, ConnectionStatusReason reason) {
                        Log.d(TAG, "NexmoConnectionListener.onConnectionStatusChange : $status : $reason");
                    }
                });
    }
    public static String getUserName() {
        return NexmoClient.get().getUser().getName();
    }

    public static String getOtherUserName() {
        return NexmoClient.get().getUser().getName().equals(USER_NAME_JANE) ? USER_NAME_JOE : USER_NAME_JANE;
    }

    public static String getOtherUserId() {
        return NexmoClient.get().getUser().getName().equals(USER_NAME_JANE) ? USER_ID_JOE : USER_ID_JANE;
    }
}
