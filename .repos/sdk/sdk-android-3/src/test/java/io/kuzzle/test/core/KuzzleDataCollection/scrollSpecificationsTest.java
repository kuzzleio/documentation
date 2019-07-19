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
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.listeners.ResponseListener;
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
import static org.mockito.Mockito.when;

public class scrollSpecificationsTest {
    private Kuzzle kuzzle;
    private Collection collection;
    private ResponseListener listener;
    private String scrollId;
    private Options options;

    @Before
    public void setUp() throws URISyntaxException {
        Options opts = new Options();
        opts.setConnect(Mode.MANUAL);
        opts.setScroll("30s");
        KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
        extended.setSocket(mock(WebSocketClient.class));
        extended.setState(States.CONNECTED);
        kuzzle = spy(extended);
        when(kuzzle.getHeaders()).thenReturn(new JSONObject());

        collection = new Collection(kuzzle, "bar", "foo");
        listener = mock(ResponseListener.class);
        scrollId = "1337";
        options = mock(Options.class);
    }

    @Test
    public void checkScrollSpecificationsSignaturesVariants() {
        collection = spy(collection);
        collection.scrollSpecifications(scrollId, listener);
        verify(collection).scrollSpecifications(eq(scrollId), any(Options.class), eq(listener));

        collection.scrollSpecifications(scrollId, options, listener);
        verify(collection).scrollSpecifications(eq(scrollId), eq(options), eq(listener));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScrollSpecificationsIllegalListener() {
        collection.scrollSpecifications(scrollId, null);
    }

    @Test(expected = RuntimeException.class)
    public void testScrollSpecificationsQueryException() throws JSONException {
        doThrow(JSONException.class).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        collection.scrollSpecifications(scrollId, listener);
    }

    @Test(expected = RuntimeException.class)
    public void testScrollSpecificationsException() throws JSONException {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
            ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("count", 42)));
            return null;
            }
        }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doThrow(JSONException.class).when(listener).onSuccess(any(Integer.class));
        collection.scrollSpecifications(scrollId, listener);
    }

    @Test
    public void testScrollSpecifications() throws JSONException {
        JSONObject filters = new JSONObject();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                JSONObject response = new JSONObject("{\"result\": {\n" +
                "    \"hits\": [\n" +
                "      {\n" +
                "        \"_id\": \"foo#bar\",\n" +
                "        \"index\": \"foo\",\n" +
                "        \"collection\": \"bar\",\n" +
                "        \"_source\": {\n" +
                "          \"validation\": {\n" +
                "            \"strict\": true,\n" +
                "            \"fields\": {\n" +
                "              \"foo\": {\n" +
                "                \"mandatory\": true,\n" +
                "                \"type\": \"string\",\n" +
                "                \"defaultValue\": \"bar\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"bar#foo\",\n" +
                "        \"index\": \"bar\",\n" +
                "        \"collection\": \"foo\",\n" +
                "        \"_source\": {\n" +
                "          \"validation\": {\n" +
                "            \"strict\": false,\n" +
                "            \"fields\": {\n" +
                "              \"bar\": {\n" +
                "                \"mandatory\": true,\n" +
                "                \"type\": \"string\",\n" +
                "                \"defaultValue\": \"foo\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"total\": 2,\n" +
                "    \"scrollId\": \"1337\"\n" +
                "  }" +
                "}");

            ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
            ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
            return null;
            }
        }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

        collection.scrollSpecifications(scrollId, new ResponseListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    assertEquals(result.getJSONArray("hits").length(), 2);
                    assertEquals(result.getJSONArray("hits").getJSONObject(1).getJSONObject("_source").getJSONObject("validation").getBoolean("strict"), false);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(JSONObject error) {
            }
        });
        collection.scrollSpecifications(scrollId, mock(ResponseListener.class));
        ArgumentCaptor argument = ArgumentCaptor.forClass(Kuzzle.QueryArgs.class);
        verify(kuzzle, times(2)).query((Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((Kuzzle.QueryArgs) argument.getValue()).controller, "collection");
        assertEquals(((Kuzzle.QueryArgs) argument.getValue()).action, "scrollSpecifications");
    }
}
