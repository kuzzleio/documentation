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

public class validateSpecificationsTest {
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
    public void checkValidateSpecificationsSignaturesVariants() throws JSONException {
        JSONObject specifications = mock(JSONObject.class);
        collection = spy(collection);

        collection.validateSpecifications(specifications, listener);
        collection.validateSpecifications(specifications, mock(Options.class), listener);

        verify(collection, times(2)).validateSpecifications(any(JSONObject.class), any(Options.class), any(ResponseListener.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateSpecificationsIllegalArgument() throws JSONException {
        collection.validateSpecifications(null, listener);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateSpecificationsIllegalListener() throws JSONException {
        collection.validateSpecifications(null, null);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateSpecificationsQueryException() throws JSONException {
        doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        collection.validateSpecifications(mock(JSONObject.class), listener);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateSpecificationsException() throws JSONException {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("valid", true)));
                return null;
            }
        }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doThrow(JSONException.class).when(listener).onSuccess(any(JSONObject.class));
        collection.validateSpecifications(mock(JSONObject.class), listener);
    }

    @Test
    public void testValidateSpecifications() throws JSONException {
        final JSONObject validateSpecificationsResponse = new JSONObject().put("valid", true);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
            JSONObject response = new JSONObject()
                .put("result", validateSpecificationsResponse);
            if (invocation.getArguments()[3] != null) {
                ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
                ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
            }
            return null;
            }
        }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

        collection.validateSpecifications(new JSONObject(), new ResponseListener<Boolean>() {
            @Override
            public void onSuccess(Boolean isValid) {
                try {
                    assertEquals(isValid, validateSpecificationsResponse.getBoolean("valid"));
                    assertEquals(isValid, validateSpecificationsResponse.getBoolean("valid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(JSONObject error) {

            }
        });
        collection.validateSpecifications(new JSONObject(), new Options(), new ResponseListener<Boolean>() {
            @Override
            public void onSuccess(Boolean isValid) {
                try {
                    assertEquals(isValid, validateSpecificationsResponse.getBoolean("valid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(JSONObject error) {

            }
        });
        ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
        verify(kuzzle, times(2)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "collection");
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "validateSpecifications");
    }
}
