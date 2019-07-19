package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONArray;
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
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleDataCollectionExtend;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class mDeleteDocumentTest {
    private Kuzzle kuzzle;
    private KuzzleDataCollectionExtend collection;
    private ResponseListener listener;
    private String[] documentIds;

    @Before
    public void setUp() throws URISyntaxException {
        Options opts = new Options();
        opts.setConnect(Mode.MANUAL);
        KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
        extended.setSocket(mock(WebSocketClient.class));
        extended.setState(States.CONNECTED);

        kuzzle = spy(extended);
        when(kuzzle.getHeaders()).thenReturn(new JSONObject());

        collection = new KuzzleDataCollectionExtend(kuzzle, "index", "test");
        listener = mock(ResponseListener.class);

        documentIds = new String[]{"foo", "bar"};
    }

    @Test
    public void checkMDeleteDocumentSignaturesVariants() throws JSONException {
        Options opts = mock(Options.class);
        collection = spy(collection);

        collection.mDeleteDocument(documentIds, opts, listener);
        collection.mDeleteDocument(documentIds, listener);
        collection.mDeleteDocument(documentIds, opts);
        collection.mDeleteDocument(documentIds);

        verify(collection, times(4)).mDeleteDocument(any(String[].class), any(Options.class), any(ResponseListener.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMDeleteDocumentIllegalArgument() throws JSONException {
        documentIds = new String[]{};
        collection.mDeleteDocument(documentIds, listener);
    }

    @Test(expected = RuntimeException.class)
    public void testMDeleteDocumentQueryException() throws JSONException {
        documentIds = new String[]{};
        doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        collection.mDeleteDocument(documentIds, listener);
    }

    @Test(expected = RuntimeException.class)
    public void testMDeleteDocumentException() throws JSONException {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONArray().put("_id").put("id-42")));
                return null;
            }
        }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doThrow(JSONException.class).when(listener).onSuccess(any(String.class));
        collection.mDeleteDocument(documentIds, listener);
    }

    @Test
    public void testMDeleteDocument() throws JSONException {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONArray().put("foo").put("bar")));
                ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
                return null;
            }
        }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        collection.mDeleteDocument(documentIds, listener);
        ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
        verify(kuzzle, times(1)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "document");
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "mDelete");
    }
}
