package io.kuzzle.sdk.responses;

import java.util.List;

public interface KuzzleList<T> {
  public List<T> getDocuments();
  public long getTotal();
}
