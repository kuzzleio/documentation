package io.kuzzle.test.security.KuzzleSecurity;

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
import io.kuzzle.sdk.listeners.OnQueryDoneListener;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.responses.SecurityDocumentList;
import io.kuzzle.sdk.security.Security;
import io.kuzzle.sdk.state.States;
import io.kuzzle.sdk.util.Scroll;
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

public class scrollProfilesTest {
    private Kuzzle kuzzle;
    private Security security;
    private ResponseListener listener;
    private Options options;
    private Scroll scroll;
    private String scrollId;

    @Before
    public void setUp() throws URISyntaxException {
        Options opts = new Options();
        opts.setConnect(Mode.MANUAL);
        KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
        extended.setSocket(mock(WebSocketClient.class));
        extended.setState(States.CONNECTED);
        kuzzle = spy(extended);
        when(kuzzle.getHeaders()).thenReturn(new JSONObject());

        security = new Security(kuzzle);
        listener = mock(ResponseListener.class);
        options = mock(Options.class);
        scroll = new Scroll();
        scrollId = "f00ba5";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScrollProfilesNoScrollId() throws JSONException {
        security.scrollProfiles(scroll, null, listener);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScrollProfilesIllegalListener() throws JSONException {
        scroll.setScrollId(scrollId);
        security.scrollProfiles(scroll, null);
    }

    @Test
    public void checkSignaturesVariants() throws JSONException {
        security = spy(security);
        scroll.setScrollId(scrollId);

        security.scrollProfiles(scroll, listener);
        verify(security).scrollProfiles(eq(scroll), eq(listener));

        security.scrollProfiles(scroll, options, listener);
        verify(security).scrollProfiles(eq(scroll), eq(options), eq(listener));
    }

    @Test(expected = RuntimeException.class)
    public void testScrollProfilesQueryException() throws JSONException {
        scroll.setScrollId(scrollId);
        doThrow(JSONException.class).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        security.scrollProfiles(scroll, listener);
    }

    @Test(expected = RuntimeException.class)
    public void testScrollProfilesException() throws JSONException {
        scroll.setScrollId(scrollId);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(new JSONObject().put("result", new JSONObject().put("count", 42)));
                return null;
            }
        }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        doThrow(JSONException.class).when(listener).onSuccess(any(Integer.class));
        security.scrollProfiles(scroll, listener);
    }

    @Test
    public void testScrollProfiles() throws JSONException {
        scroll.setScrollId("f00ba5");
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
            JSONObject response = new JSONObject("{\"result\": {\n" +
                "    \"_shards\": {\n" +
                "      \"failed\": 0,\n" +
                "      \"successful\": 5,\n" +
                "      \"total\": 5\n" +
                "    },\n" +
                "    \"hits\": [\n" +
                "      {\n" +
                "        \"_id\": \"AVJAwyDMZAGQHg9Dhfw2\",\n" +
                "        \"_index\": \"cabble\",\n" +
                "        \"_score\": 1,\n" +
                "        \"_source\": {\n" +
                "          \"pos\": {\n" +
                "            \"lat\": 43.6073821,\n" +
                "            \"lon\": 3.9130721\n" +
                "          },\n" +
                "          \"sibling\": \"none\",\n" +
                "          \"status\": \"idle\",\n" +
                "          \"type\": \"customer\"\n" +
                "        },\n" +
                "        \"_meta\": {},\n" +
                "        \"_type\": \"users\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"AVJAwyOvZAGQHg9Dhfw3\",\n" +
                "        \"_index\": \"cabble\",\n" +
                "        \"_score\": 1,\n" +
                "        \"_source\": {\n" +
                "          \"pos\": {\n" +
                "            \"lat\": 43.6073683,\n" +
                "            \"lon\": 3.8999983\n" +
                "          },\n" +
                "          \"sibling\": \"none\",\n" +
                "          \"status\": \"idle\",\n" +
                "          \"type\": \"cab\"\n" +
                "        },\n" +
                "        \"_meta\": {},\n" +
                "        \"_type\": \"users\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"max_score\": 1,\n" +
                "    \"timed_out\": false,\n" +
                "    \"took\": 307,\n" +
                "    \"total\": 2\n" +
                "  }" +
                "}");

            ((OnQueryDoneListener) invocation.getArguments()[3]).onSuccess(response);
            ((OnQueryDoneListener) invocation.getArguments()[3]).onError(new JSONObject());
            return null;
            }
        }).when(kuzzle).query(any(Kuzzle.QueryArgs.class), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));

        security.scrollProfiles(scroll, new ResponseListener<SecurityDocumentList>() {
            @Override
            public void onSuccess(SecurityDocumentList result) {
                assertEquals(result.getTotal(), 2);
                assertEquals(result.getDocuments().get(1).getId(), "AVJAwyOvZAGQHg9Dhfw3");
            }

            @Override
            public void onError(JSONObject error) {
            }
        });
        security.scrollProfiles(scroll, mock(ResponseListener.class));
        ArgumentCaptor argument = ArgumentCaptor.forClass(Kuzzle.QueryArgs.class);
        verify(kuzzle, times(2)).query((Kuzzle.QueryArgs) argument.capture(), any(JSONObject.class), any(Options.class), any(OnQueryDoneListener.class));
        assertEquals(((Kuzzle.QueryArgs) argument.getValue()).controller, "security");
        assertEquals(((Kuzzle.QueryArgs) argument.getValue()).action, "scrollProfiles");
    }
}
