package com.whatsup.whatsclone.messaging.domain.message.aggregate;

import com.whatsup.whatsclone.messaging.domain.message.vo.*;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import com.whatsup.whatsclone.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public class Message {
    private final MessageSentTime sentTime;
    private final MessageContent content;
    private final MessageSentState sentState;
    private final MessagePublicId publicId;
    private final UserPublicId sender;
    private final ConversationPublicId conversationPublicId;

    public Message(MessageSentTime sentTime, MessageContent content, MessageSentState sentState,
                   MessagePublicId publicId, UserPublicId sender, ConversationPublicId conversationPublicId) {
        assertMandatoryFields(sentTime, content, sentState, publicId, sender, conversationPublicId);
        this.sentTime = sentTime;
        this.content = content;
        this.sentState = sentState;
        this.publicId = publicId;
        this.sender = sender;
        this.conversationPublicId = conversationPublicId;
    }

    private void assertMandatoryFields(MessageSentTime sentTime,
                                      MessageContent content,
                                      MessageSentState sentState,
                                      MessagePublicId publicId,
                                      UserPublicId sender,
                                      ConversationPublicId conversationPublicId) {
        Assert.notNull("sentTime", sentTime);
        Assert.notNull("content", content);
        Assert.notNull("sentState", sentState);
        Assert.notNull("publicId", publicId);
        Assert.notNull("sender", sender);
        Assert.notNull("conversationPublicId", conversationPublicId);
    }

    public MessageSentTime getSentTime() {
        return sentTime;
    }

    public MessageContent getContent() {
        return content;
    }

    public MessageSentState getSentState() {
        return sentState;
    }

    public MessagePublicId getPublicId() {
        return publicId;
    }

    public UserPublicId getSender() {
        return sender;
    }

    public ConversationPublicId getConversationPublicId() {
        return conversationPublicId;
    }
}
