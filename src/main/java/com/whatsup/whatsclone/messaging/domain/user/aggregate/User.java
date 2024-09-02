package com.whatsup.whatsclone.messaging.domain.user.aggregate;

import com.whatsup.whatsclone.messaging.domain.user.aggregate.AuthorityBuilder;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.UserBuilder;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.*;
import com.whatsup.whatsclone.shared.error.domain.Assert;
import org.jilt.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public class User {

    private UserLastName lastName;
    private UserFirstName firstName;
    private UserEmail email;
    private UserPublicId userPublicId;
    private UserImageUrl imageUrl;
    private Instant lastModifiedDate;
    private Instant createdDate;
    private Instant lastSeen;
    private Set<Authority> authorities;
    private Long dbId;

    public User(UserLastName lastName, UserFirstName firstName, UserEmail email,
                UserPublicId userPublicId, UserImageUrl imageUrl, Instant lastModifiedDate,
                Instant createdDate, Instant lastSeen, Set<Authority> authorities, Long dbId) {
        assertMandatoryFields(lastName,firstName,email,authorities);
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.userPublicId = userPublicId;
        this.imageUrl = imageUrl;
        this.lastModifiedDate = lastModifiedDate;
        this.createdDate = createdDate;
        this.lastSeen = lastSeen;
        this.authorities = authorities;
        this.dbId = dbId;
    }

    private void assertMandatoryFields(UserLastName lastName, UserFirstName firstName, UserEmail email,
                                       Set<Authority> authorities) {
        Assert.notNull("lastName", lastName);
        Assert.notNull("firstName", firstName);
        Assert.notNull("email", email);
        Assert.notNull("authorities", authorities);
    }

    public void updateFromUser(User user){
        this.email = user.email;
        this.lastName = user.lastName;
        this.firstName = user.firstName;
        this.imageUrl = user.imageUrl;
    }

    // extract informacije unutar token-a i vrati user-a
    public static User fromTokenAttributes(Map<String, Object> attributes, List<String> rolesFromAccessToken) {
        UserBuilder userBuilder = UserBuilder.user();
        String sub = String.valueOf(attributes.get("sub"));
        String userName = null;
        if(attributes.containsKey("preferred_username")) {
            userName = attributes.get("preferred_username").toString().toLowerCase();
        }
        if(attributes.containsKey("given_name")) {
            userBuilder.firstName(new UserFirstName(attributes.get("given_name").toString()));
        }else if(attributes.containsKey("nickname")) {
            userBuilder.firstName(new UserFirstName(attributes.get("nickname").toString()));
        }

        if(attributes.containsKey("family_name")) {
            userBuilder.lastName(new UserLastName( attributes.get("family_name").toString()));
        }

        if(attributes.containsKey("email")) {
            userBuilder.email(new UserEmail(attributes.get("email").toString()));
        }else if(sub.contains("|") && (userName != null && userName.contains("@"))) {
            userBuilder.email(new UserEmail(userName));
        }else {
            userBuilder.email(new UserEmail(sub));
        }

        if(attributes.containsKey("image_url")) {
            userBuilder.imageUrl(new UserImageUrl(attributes.get("image_url").toString()));
        }

        Set<Authority> authorities = rolesFromAccessToken.stream().map(
                        authority -> AuthorityBuilder.authority().name(new AuthorityName(authority)).build())
                .collect(Collectors.toSet());

        userBuilder.authorities(authorities);
        return userBuilder.build();
    }

    public void initFieldsForSignUp(){
        this.lastSeen = Instant.now();
    }

    public UserLastName getLastName() {
        return lastName;
    }

    public UserFirstName getFirstName() {
        return firstName;
    }

    public UserEmail getEmail() {
        return email;
    }

    public UserPublicId getUserPublicId() {
        return userPublicId;
    }

    public UserImageUrl getImageUrl() {
        return imageUrl;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getLastSeen() {
        return lastSeen;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public Long getDbId() {
        return dbId;
    }
}
