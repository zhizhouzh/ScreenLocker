package com.zzz.screenlocker;

import android.app.Activity;
import android.app.admin.*;
import android.content.*;
import android.os.Bundle;

public class FullscreenActivity extends Activity {
	// Interaction with the DevicePolicyManager
	DevicePolicyManager mDPM;
	ComponentName mDeviceAdmin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Prepare to work with the DPM
		mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		mDeviceAdmin = new ComponentName(this, DeviceAdmin.class);

		if (!mDPM.isAdminActive(mDeviceAdmin)) {
			// Launch the activity to have the user enable our admin.
			Intent intent = new Intent(
					DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
					mDeviceAdmin);
			startActivity(intent);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		if (mDPM.isAdminActive(mDeviceAdmin)) {
			mDPM.lockNow();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		super.finish();
	}

}
