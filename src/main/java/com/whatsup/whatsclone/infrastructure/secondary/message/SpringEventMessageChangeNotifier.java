package com.whatsup.whatsclone.infrastructure.secondary.message;

import com.whatsup.whatsclone.messaging.domain.message.aggregate.Message;
import com.whatsup.whatsclone.messaging.domain.message.service.MessageChangeNotifier;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import com.whatsup.whatsclone.shared.service.State;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpringEventMessageChangeNotifier implements MessageChangeNotifier {

    @Override
    public State<Void, String> send(Message message, List<UserPublicId> userToNotify) {
        return null;
    }

    @Override
    public State<Void, String> delete(ConversationPublicId conversationPublicId, List<UserPublicId> userToNotify) {
        return null;
    }
}
