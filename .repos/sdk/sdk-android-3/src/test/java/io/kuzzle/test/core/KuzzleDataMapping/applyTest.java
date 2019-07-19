package io.kuzzle.test.core.KuzzleDataMapping;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.CollectionMapping;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class applyTest {
  private Kuzzle k;
  private Collection dataCollection;
  private CollectionMapping dataMapping;

  @Before
  public void setUp() {
    k = mock(Kuzzle.class);
    when(k.getDefaultIndex()).thenReturn("index");
    when(k.getHeaders()).thenReturn(new JSONObject());
    dataCollection = new Collection(k, "test", "index");
    dataMapping = new CollectionMapping(dataCollection);
  }

  @Test
  public void checkSignaturesVariants() {
    dataMapping = spy(dataMapping);
    dataMapping.apply();
    dataMapping.apply(mock(Options.class));
    dataMapping.apply(mock(ResponseListener.class));
    verify(dataMapping, times(3)).apply(any(Options.class), any(ResponseListener.class));
  }

  @Test(expected = RuntimeException.class)
  public void testApplyQueryException() throws JSONException {
    doThrow(JSONException.class).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    dataMapping.apply();
  }

  @Test(expected = RuntimeException.class)
  public void testApplyException() throws JSONException {
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(mock(JSONObject.class));
        return null;
      }
    }).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    ResponseListener mockListener = mock(ResponseListener.class);
    doThrow(JSONException.class).when(mockListener).onSuccess(any(CollectionMapping.class));
    dataMapping.apply(mockListener);
  }

  @Test
  public void testApply() throws JSONException {
    final JSONObject mockResponse = new JSONObject("{\n" +
      "    properties: {\n" +
      "      field1: {type: \"field type\"},\n" +
      "      field2: {type: \"field type\"},\n" +
      "      fieldn: {type: \"field type\"}\n" +
      "    }\n" +
      "  }");
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(mockResponse);
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(null);
        return null;
      }
    }).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    dataMapping.apply();
    dataMapping.apply(new Options());
    dataMapping.apply(mock(ResponseListener.class));
    dataMapping.apply(new Options(), mock(ResponseListener.class));
    ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
    verify(k, times(4)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "collection");
    assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "updateMapping");
  }

}
