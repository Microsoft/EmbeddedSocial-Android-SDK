/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package com.microsoft.embeddedsocial.ui.adapter.viewholder;

import com.microsoft.embeddedsocial.account.AuthorizationCause;
import com.microsoft.embeddedsocial.account.UserAccount;
import com.microsoft.embeddedsocial.autorest.models.ContentType;
import com.microsoft.embeddedsocial.base.event.EventBus;
import com.microsoft.embeddedsocial.event.ScrollPositionEvent;
import com.microsoft.embeddedsocial.event.click.ViewCoverImageEvent;
import com.microsoft.embeddedsocial.sdk.R;
import com.microsoft.embeddedsocial.server.model.view.TopicView;
import com.microsoft.embeddedsocial.ui.util.ContentUpdateHelper;
import com.microsoft.embeddedsocial.ui.util.menu.TopicContextMenu;

import android.view.View;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

/**
 * Click listener for all buttons in the single topic
 */
public class FlatTopicButtonsListener extends TopicButtonsListener {

    private final TopicRenderOptions options = TopicRenderOptions.getDefault();
    private final Fragment fragment;

    public FlatTopicButtonsListener(Fragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    @Override
    public void onClickContextMenu(View view) {
        PopupMenu menu = new PopupMenu(context, view);
        TopicView topic = (TopicView) view.getTag(R.id.es_keyTopic);
        TopicContextMenu.inflateContextMenu(fragment, menu, topic, options);
        menu.show();
    }

    @Override
    public void onClickCommentsCount(View view) {
        EventBus.post(new ScrollPositionEvent((Integer) view.getTag(R.id.es_keyPosition)));
    }

    @Override
    public void onClickComment(View view) {
        if (UserAccount.getInstance().checkAuthorization(fragment, AuthorizationCause.COMMENT)) {
            EventBus.post(new ScrollPositionEvent(ScrollPositionEvent.EDIT_POSITION));
        }
    }

    @Override
    public void onClickPin(View view) {
        ContentUpdateHelper.launchPin(
            fragment,
            (String) view.getTag(R.id.es_keyHandle),
            (boolean) view.getTag(R.id.es_keyIsAdd)
        );
    }

    @Override
    public void onClickContent(View view) {
        // Not used
    }

    @Override
    public void onClickCover(View view) {
        EventBus.post(new ViewCoverImageEvent(fragment, (TopicView) view.getTag(R.id.es_keyTopic)));
    }

    @Override
    public void onClickLike(View view) {
        ContentUpdateHelper.launchLike(
            fragment,
            (String) view.getTag(R.id.es_keyHandle),
            ContentType.TOPIC,
            (boolean) view.getTag(R.id.es_keyIsAdd)
        );
    }
}
