package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Collection;
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
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class getSpecificationsTest {
    private Kuzzle kuzzle;
    private Collection collection;
    private ResponseListener listener;

    @Before
    public void setUp() throws URISyntaxException {
        Options opts = new Options();
        opts.setConnect(Mode.MANUAL);
        KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
        extended.setSocket(mock(WebSocketClient.class));
        extended.setState(States.CONNECTED);

        kuzzle = spy(extended);
        when(kuzzle.getHeaders()).thenReturn(new JSONObject());

        collection = new Collection(kuzzle, "test", "index");
        listener = mock(ResponseListener.class);
    }

    @Test
    public void checkGetSpecificationsSignaturesVariants() throws JSONException {
        collection = spy(collection);

        collection.getSpecifications(mock(Options.class), listener);
        collection.getSpecifications(listener);

        verify(collection, times(2)).getSpecifications(any(Options.class), any(ResponseListener.class));
    }

    @Test(expected = RuntimeException.class)
    public void testGetSpecificationsQueryException() throws JSONException {
        doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        collection.getSpecifications(listener);
    }

    @Test(expected = RuntimeException.class)
    public void testGetSpecificationsException() throws JSONException {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
            ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject()));
            return null;
            }
        }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doThrow(JSONException.class).when(listener).onSuccess(any(JSONObject.class));
        collection.getSpecifications(listener);
    }

    @Test
    public void testGetSpecifications() throws JSONException {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
            ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(
                new JSONObject()
                    .put("result", new JSONObject()
                        .put("validation", new JSONObject()
                            .put("strict", true)
                            .put("fields", new JSONObject()
                                .put("foo", new JSONObject()
                                    .put("mandatory", true)
                                    .put("type", "string")
                                    .put("defaultValue", "bar")
                                )
                            )
                        )
                    )
            );
            ((OnQueryDoneListener) invocation.getArguments()[3]).onError(mock(JSONObject.class));
            return null;
            }
        }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        collection.getSpecifications(mock(ResponseListener.class));
        collection.getSpecifications(new Options(), mock(ResponseListener.class));
        ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
        verify(kuzzle, times(2)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "collection");
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "getSpecifications");
    }
}
