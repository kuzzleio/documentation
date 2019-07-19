package io.kuzzle.sdk.util;


public class Scroll {
    private String scrollId;

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public boolean hasScrollId() { return !(scrollId == null); }

    public String getScrollId() { return scrollId; }
}
