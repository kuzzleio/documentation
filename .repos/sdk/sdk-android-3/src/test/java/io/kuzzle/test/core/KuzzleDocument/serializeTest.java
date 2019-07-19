package io.kuzzle.test.core.KuzzleDocument;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Document;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

public class serializeTest {

  private Kuzzle k;
  private Document doc;

  @Before
  public void setUp() throws URISyntaxException, JSONException {
    Options opts = new Options();
    opts.setConnect(Mode.MANUAL);
    KuzzleExtend extended = new KuzzleExtend("localhost", opts, null);
    extended.setState(States.CONNECTED);
    k = spy(extended);
    doc = new Document(new Collection(k, "test", "index"));
  }

  @Test(expected = RuntimeException.class)
  public void testException() {
    doThrow(JSONException.class).when(k).addHeaders(any(JSONObject.class), any(JSONObject.class));
    doc.serialize();
  }

  @Test
  public void testToString() throws JSONException {
    JSONObject o = new JSONObject()
        .put("foo", "bar");
    doc.setId("42");
    doc.setVersion(4242);
    doc.setContent(o);
    assertEquals(doc.toString(), "{\"_id\":\"42\",\"body\":{\"foo\":\"bar\"},\"_version\":4242}");
  }

}
