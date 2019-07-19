package io.kuzzle.sdk.responses;


import java.util.List;

import io.kuzzle.sdk.security.AbstractSecurityDocument;
import io.kuzzle.sdk.util.Scroll;

public class SecurityDocumentList implements KuzzleList<AbstractSecurityDocument> {
  private List<AbstractSecurityDocument> documents;
  private long total;
  private Scroll scroll;

  public SecurityDocumentList(List<AbstractSecurityDocument> roles, long total, Scroll scroll) {
    this.documents = roles;
    this.total = total;
    this.scroll = scroll;
  }

  public SecurityDocumentList(List<AbstractSecurityDocument> roles, long total) {
    this(roles, total, new Scroll());
  }

  public List<AbstractSecurityDocument> getDocuments() {
    return documents;
  }

  public long getTotal() {
    return total;
  }

  public Scroll getScroll() { return scroll; }
}
