/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package com.microsoft.embeddedsocial.ui.activity;

import android.support.v7.app.ActionBar;

import com.microsoft.embeddedsocial.account.UserAccount;
import com.microsoft.embeddedsocial.sdk.R;
import com.microsoft.embeddedsocial.ui.activity.base.BaseProfileActivity;
import com.microsoft.embeddedsocial.ui.fragment.MyProfileFragment;

/**
 * Activity shoving current user's feeds.
 */
public class MyProfileActivity extends BaseProfileActivity {

	public MyProfileActivity() {
		super(R.id.es_navigationProfile);
	}

	@Override
	protected void setupFragments() {
		setActivityContent(new MyProfileFragment());
		super.setupFragments();
	}

	@Override
	protected void setupActionBar(ActionBar actionBar) {
		super.setupActionBar(actionBar);
		actionBar.setTitle(UserAccount.getInstance().getAccountDetails().getFullName());
	}

	@Override
	protected boolean isAuthorizationRequired() {
		return true;
	}
}
