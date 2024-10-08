package com.whatsup.whatsclone.infrastructure.primary.user;

import com.whatsup.whatsclone.messaging.application.UserApplicationService;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserApplicationService userApplicationService;

    public UserResource(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping("/get-authenticated-user")
    ResponseEntity<RestUser> getAuthenticatedUser(@AuthenticationPrincipal Jwt user, @RequestParam boolean forceResync){
        User authenticatedUser = userApplicationService.getAuthenticatedUserWithSync(user, forceResync);
        RestUser restUser = RestUser.from(authenticatedUser);
        return ResponseEntity.ok(restUser);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestSearchUser>> search(Pageable pageable, @RequestParam String query) {
        List<RestSearchUser> searchResults = userApplicationService.search(pageable, query)
                .stream().map(RestSearchUser::from)
                .toList();
        return ResponseEntity.ok(searchResults);
    }

}
