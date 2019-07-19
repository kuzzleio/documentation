package io.kuzzle.test.testUtils;

public class QueryArgsHelper {
  public static io.kuzzle.sdk.core.Kuzzle.QueryArgs  makeQueryArgs(final String controller, final String action) {
    io.kuzzle.sdk.core.Kuzzle.QueryArgs args = new io.kuzzle.sdk.core.Kuzzle.QueryArgs();
    args.controller = controller;
    args.action = action;
    return args;
  }
}
