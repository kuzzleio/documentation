package io.kuzzle.test.core.Kuzzle;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class updateSelfTest {

  Kuzzle kuzzle;
  ArgumentCaptor argument;
  ResponseListener<JSONObject> listener;

  @Before
  public void setUp() throws URISyntaxException {
    kuzzle = spy(new Kuzzle("localhost"));
    argument = ArgumentCaptor.forClass(Kuzzle.QueryArgs.class);
    listener = spy(new ResponseListener<JSONObject>() {
      @Override
      public void onSuccess(JSONObject response) {

      }

      @Override
      public void onError(JSONObject error) {

      }
    });
  }

  @Test(expected = RuntimeException.class)
  public void testUpdateSelfException() throws JSONException {
    doThrow(JSONException.class).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.updateSelf(mock(JSONObject.class));
  }

  @Test
  public void testUpdateSelf() throws JSONException {
    JSONObject content = new JSONObject();
    content.put("foo", "bar");
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("_id", "id")));
        ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
        return null;
      }
    }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    kuzzle.updateSelf(content, listener);
    kuzzle.updateSelf(content, mock(Options.class));
    kuzzle.updateSelf(content, mock(Options.class), listener);
    kuzzle.updateSelf(content);
    verify(kuzzle, times(4)).query((Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
    verify(listener, times(2)).onSuccess(any(JSONObject.class));
    verify(listener, times(2)).onError(any(JSONObject.class));
    assertEquals("auth", ((Kuzzle.QueryArgs)argument.getValue()).controller);
    assertEquals("updateSelf", ((Kuzzle.QueryArgs)argument.getValue()).action);
  }

}
