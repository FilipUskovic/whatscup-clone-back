package com.whatsup.whatsclone.messaging.domain.message.service;

import com.whatsup.whatsclone.messaging.domain.message.aggregate.Message;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import com.whatsup.whatsclone.shared.service.State;

import java.util.List;

public interface MessageChangeNotifier {

    // biti ce za real time kada usr npr posalje pporuku zelimo dobiti notifikaciju

    State<Void, String> send(Message message, List<UserPublicId> userToNotify);

    State<Void, String> delete(ConversationPublicId conversationPublicId, List<UserPublicId> userToNotify);

   // State<Void, String> view(ConversationViewedForNotification conversationViewedForNotification);
}
