package io.kuzzle.test.core.KuzzleDataMapping;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.CollectionMapping;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class constructorTest {
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

  @Test(expected = RuntimeException.class)
  public void testConstructorException() {
    Collection fake = spy(new Collection(k, "test", "index"));
    doThrow(JSONException.class).when(fake).getHeaders();
    dataMapping = new CollectionMapping(fake);
  }

  @Test
  public void testConstructor() throws JSONException {
    JSONObject mapping = new JSONObject();
    mapping.put("type", "string");
    dataMapping = new CollectionMapping(dataCollection, mapping);
    dataMapping = new CollectionMapping(dataMapping);
  }

  @Test
  public void testSetHeaders() throws JSONException {
    dataMapping.setHeaders(null, true);
    JSONObject headers = new JSONObject();
    headers.put("foo", "bar");
    dataMapping.setHeaders(headers, true);
    assertEquals(dataMapping.getHeaders().getString("foo"), "bar");
    headers.put("oof", "baz");
    dataMapping.setHeaders(headers);
    assertEquals(dataMapping.getHeaders().getString("foo"), "bar");
    assertEquals(dataMapping.getHeaders().getString("oof"), "baz");
  }

  @Test(expected = RuntimeException.class)
  public void testSetHeadersException() {
    JSONObject json = spy(new JSONObject());
    doThrow(JSONException.class).when(json).keys();
    dataMapping.setHeaders(json, false);
  }

  @Test
  public void testGetHeaders() throws JSONException {
    dataMapping.setHeaders(null);
    assertNotNull(dataMapping.getHeaders());
    JSONObject headers = new JSONObject();
    headers.put("foo", "bar");
    dataMapping.setHeaders(headers);
    assertEquals(dataMapping.getHeaders().getString("foo"), "bar");
  }

  @Test
  public void testSet() throws JSONException {
    JSONObject mapping = mock(JSONObject.class);
    assertThat(dataMapping.set("foo", mapping), instanceOf(CollectionMapping.class));
    assertEquals(dataMapping.getMapping().getJSONObject("foo"), mapping);
  }
}
