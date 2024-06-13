package com.mymsa.core.context;

import org.apache.commons.lang.RandomStringUtils;

public class MSAContext {

    private final String sessionID;

    public MSAContext() {
        this.sessionID = RandomStringUtils.randomAlphabetic(8);
    }

    public MSAContext(String sessionID) {
        this.sessionID = sessionID != null ? sessionID : RandomStringUtils.randomAlphabetic(8);
    }

    @Override
    public String toString() {
        return "MSAContext [sessionID=" + sessionID + "]";
    }
}
