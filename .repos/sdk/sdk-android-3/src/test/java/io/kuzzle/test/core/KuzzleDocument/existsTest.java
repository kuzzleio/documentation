package io.kuzzle.test.core.KuzzleDocument;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Document;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class existsTest {
    private Kuzzle k;
    private Document doc;
    private ResponseListener mockListener;

    @Before
    public void setUp() throws URISyntaxException, JSONException {
        Options opts = new Options();
        opts.setConnect(Mode.MANUAL);
        KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
        extended.setState(States.CONNECTED);
        extended.setSocket(mock(WebSocketClient.class));
        k = spy(extended);
        mockListener = mock(ResponseListener.class);
        doc = new Document(new Collection(k, "test", "index"));
    }

    @Test
    public void checkSignaturesVariants() {
        doc.setId("foo");
        doc = spy(doc);
        doc.exists(mockListener);
        verify(doc).exists(eq((Options)null), eq(mockListener));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExistsNullListenerException() throws IllegalArgumentException {
        doc.setId("42");
        doc.exists(null);
    }

    @Test(expected = RuntimeException.class)
    public void testExistsQueryException() throws JSONException {
        doc.setId("42");
        doThrow(JSONException.class).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doc.exists(mockListener);
    }

    @Test(expected = RuntimeException.class)
    public void testExistsException() throws JSONException {
        doc.setId("42");
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                JSONObject result = new JSONObject()
                    .put("result", true);
                ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(result);
                return null;
            }
        }).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doThrow(JSONException.class).when(mockListener).onSuccess(any(JSONObject.class));
        doc.exists(mockListener);
    }

    @Test(expected = IllegalStateException.class)
    public void testExistsWithoutId() {
        doc.exists(null, null);
    }

    @Test
    public void testExists() throws JSONException {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                JSONObject response = new JSONObject()
                    .put("result", true);
                ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
                ((OnQueryDoneListener) invocation.getArguments()[3]).onError(null);
                return null;
            }
        }).when(k).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doc.setId("42");
        doc.setContent("foo", "baz");
        doc.exists(new ResponseListener<Boolean>() {
            @Override
            public void onSuccess(Boolean exists) {
                assertEquals(exists, true);
            }

            @Override
            public void onError(JSONObject error) {

            }
        });
        doc.exists(mockListener);
        doc.exists(mock(Options.class), mockListener);
        ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
        verify(k, times(3)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "document");
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "exists");
    }
}
