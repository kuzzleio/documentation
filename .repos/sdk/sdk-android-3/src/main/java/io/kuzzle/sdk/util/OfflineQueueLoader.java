package io.kuzzle.sdk.util;

import io.kuzzle.sdk.state.KuzzleQueue;

public interface OfflineQueueLoader {
  KuzzleQueue<QueryObject> load();
}
