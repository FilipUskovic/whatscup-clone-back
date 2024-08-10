package com.whatsup.whatsclone.messaging.domain.user.aggregate;

import com.whatsup.whatsclone.messaging.domain.user.valueobject.AuthorityName;
import com.whatsup.whatsclone.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public class Authority {

    private AuthorityName name;

    public Authority(AuthorityName name) {
        Assert.notNull("name", name);
        this.name = name;
    }

    public AuthorityName getName() {
        return name;
    }
}
