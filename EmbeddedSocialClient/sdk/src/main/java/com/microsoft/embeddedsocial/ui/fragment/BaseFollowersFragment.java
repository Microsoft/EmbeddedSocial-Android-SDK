/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package com.microsoft.embeddedsocial.ui.fragment;

import com.microsoft.embeddedsocial.account.UserAccount;
import com.microsoft.embeddedsocial.service.IntentExtras;
import com.microsoft.embeddedsocial.ui.adapter.renderer.UserRenderer;
import com.microsoft.embeddedsocial.ui.fragment.base.BaseUsersListFragment;

import android.text.TextUtils;

/**
 * Base functionality for following/followers fragments.
 */
public abstract class BaseFollowersFragment extends BaseUsersListFragment {

    @Override
    protected UserRenderer createRenderer() {
        return new UserRenderer(this);
    }

    protected String getUserHandleArgument() {
        String userHandle = getActivity().getIntent().getStringExtra(IntentExtras.USER_HANDLE);
        return TextUtils.isEmpty(userHandle) ? UserAccount.getInstance().getUserHandle() : userHandle;
    }
}
