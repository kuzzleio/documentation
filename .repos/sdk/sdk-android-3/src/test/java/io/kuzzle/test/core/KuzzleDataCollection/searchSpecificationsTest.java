package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Collection;
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

public class searchSpecificationsTest {
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
    public void checkSearchSpecificationsSignaturesVariants() {
        JSONObject filters = mock(JSONObject.class);
        Options options = mock(Options.class);
        collection = spy(collection);

        collection.searchSpecifications(listener);
        collection.searchSpecifications(filters, listener);
        collection.searchSpecifications(options, listener);
        collection.searchSpecifications(filters, options, listener);

        verify(collection, times(4)).searchSpecifications(any(JSONObject.class), any(Options.class), any(ResponseListener.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchSpecificationsIllegalListener() {
        collection.searchSpecifications(null, null, null);
    }

    @Test(expected = RuntimeException.class)
    public void testSearchSpecificationsQueryException() throws JSONException {
        doThrow(JSONException.class).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        collection.searchSpecifications(listener);
    }

    @Test(expected = RuntimeException.class)
    public void testSearchSpecificationsException() throws JSONException {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
            ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject()));
            return null;
            }
        }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doThrow(JSONException.class).when(listener).onSuccess(any(Integer.class));
        collection.searchSpecifications(listener);
    }

    @Test
    public void testSearchSpecifications() throws JSONException {
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
        }).when(kuzzle).query(any(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

        collection.searchSpecifications(filters, new ResponseListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    assertEquals(result.getJSONArray("hits").length(), 2);
                    assertEquals(result.getJSONArray("hits").getJSONObject(1).getJSONObject("_source").getJSONObject("validation").getBoolean("strict"), false);
                    assertEquals(result.getString("scrollId"), "1337");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(JSONObject error) {
            }
        });
        collection.searchSpecifications(filters, mock(ResponseListener.class));
        ArgumentCaptor argument = ArgumentCaptor.forClass(io.kuzzle.sdk.core.Kuzzle.QueryArgs.class);
        verify(kuzzle, times(2)).query((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).controller, "collection");
        assertEquals(((io.kuzzle.sdk.core.Kuzzle.QueryArgs) argument.getValue()).action, "searchSpecifications");
    }
}
