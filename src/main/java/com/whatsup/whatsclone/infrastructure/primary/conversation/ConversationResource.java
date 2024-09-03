package com.whatsup.whatsclone.infrastructure.primary.conversation;

import com.whatsup.whatsclone.messaging.application.ConversationApplicationService;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.Conversation;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.ConversationToCreate;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.shared.service.State;
import com.whatsup.whatsclone.shared.service.StatusNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.SequencedCollection;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversation")
public class ConversationResource {
    private final ConversationApplicationService conversationApplicationService;

    public ConversationResource(ConversationApplicationService conversationApplicationService) {
        this.conversationApplicationService = conversationApplicationService;
    }

    @GetMapping
    ResponseEntity<List<RestConversation>> getAll(Pageable pageable){
        List<RestConversation> conversations = conversationApplicationService.getAllByConnectedUser(pageable)
                .stream().map(RestConversation::from).toList();
        return ResponseEntity.ok(conversations);
    }

    @PostMapping
    ResponseEntity<RestConversation> create(@RequestBody
                                            RestConversationToCreate restConversationToCreate) {
        ConversationToCreate newConversation = RestConversationToCreate.toDomain(restConversationToCreate);
        State<Conversation, String> conversationState = conversationApplicationService.create(newConversation);
        if (conversationState.getStatus().equals(StatusNotification.OK)) {
            RestConversation restConversations = RestConversation.from(conversationState.getValue());
            return ResponseEntity.ok(restConversations);
        } else {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Not allowed to create conversation");
            return ResponseEntity.of(problemDetail).build();
        }
    }

    @DeleteMapping
    ResponseEntity<UUID> delete(@RequestParam UUID publicId) {
        State<ConversationPublicId, String> restConversation = conversationApplicationService.delete(new ConversationPublicId(publicId));
        if (restConversation.getStatus().equals(StatusNotification.OK)) {
            return ResponseEntity.ok(publicId);
        } else {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Not allowed to delete conversation");
            return ResponseEntity.of(problemDetail).build();
        }
    }

    @GetMapping("/get-one-by-public-id")
    ResponseEntity<RestConversation> getOneByPublicId(@RequestParam UUID conversationId) {
        Optional<RestConversation> restConversation = conversationApplicationService.getOneByConversationId(new ConversationPublicId(conversationId))
                .map(RestConversation::from);
        if (restConversation.isPresent()) {
            return ResponseEntity.ok(restConversation.get());
        } else {
            ProblemDetail problemDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Not able to find this conversation");
            return ResponseEntity.of(problemDetail).build();
        }
    }


}
