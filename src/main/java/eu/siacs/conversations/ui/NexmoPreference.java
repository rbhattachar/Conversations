package eu.siacs.conversations.ui;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.util.AttributeSet;

import eu.siacs.conversations.R;
import eu.siacs.conversations.utils.PhoneHelper;

public class NexmoPreference extends Preference {
	public NexmoPreference(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
        final String appName = context.getString(R.string.app_name);
        setSummary(appName +' '+ PhoneHelper.getVersionName(context));
        setTitle(context.getString(R.string.title_activity_nexmo_login, appName));
	}

	public NexmoPreference(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		final String appName = context.getString(R.string.app_name);
		setSummary(appName +' '+ PhoneHelper.getVersionName(context));
		setTitle(context.getString(R.string.title_activity_nexmo_login, appName));
	}

    @Override
    protected void onClick() {
        super.onClick();
        final Intent intent = new Intent(getContext(), LoginActivity.class);
        getContext().startActivity(intent);
    }
}

