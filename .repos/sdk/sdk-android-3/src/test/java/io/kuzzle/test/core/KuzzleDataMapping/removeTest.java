package io.kuzzle.test.core.KuzzleDataMapping;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.kuzzle.sdk.core.CollectionMapping;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class removeTest {
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
  public void testRemove() throws JSONException {
    JSONObject mapping = new JSONObject();
    mapping.put("type", "string");
    dataMapping.set("foo", mapping);
    assertEquals(dataMapping.getMapping().getJSONObject("foo").getString("type"), "string");
    dataMapping.remove("foo");
    assertTrue(dataMapping.getMapping().isNull("foo"));
  }
}
