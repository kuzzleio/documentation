package io.kuzzle.test.core.KuzzleDocument;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.Document;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class constructorTest {
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

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalDataCollection() throws JSONException {
    new Document(null, null, null, null);
  }

  @Test
  public void testConstructor() throws JSONException {
    doc = new Document(new Collection(k, "test", "index"), "42");
    assertEquals(doc.getId(), "42");
  }

  @Test
  public void testCollection() throws JSONException {
    Kuzzle k = mock(Kuzzle.class);
    when(k.getHeaders()).thenReturn(new JSONObject());
    Collection collection = new Collection(k, "test", "index");
    Document doc = new Document(collection);
    assertEquals(doc.getCollection(), collection.getCollection());
  }

  @Test
  public void testDocumentWithContent() throws JSONException {
    JSONObject content = new JSONObject();
    content.put("foo", "bar");

    doc = new Document(new Collection(k, "test", "index"), content);
    assertEquals(doc.getContent().getString("foo"), "bar");
  }

  @Test(expected = RuntimeException.class)
  public void testSetContentPutException() throws JSONException {
    doc = spy(doc);
    doc.setContent(mock(JSONObject.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetContentIllegalKey() throws JSONException {
    doc.setContent(null, "value");
  }

  @Test
  public void testSetContentUpdateVersion() throws JSONException {
    JSONObject content = new JSONObject()
        .put("version", 42424242);
    doc.setContent(content, false);
    assertEquals(42424242, doc.getVersion());
  }

  @Test
  public void checkSetContentVariants() throws JSONException {
    doc = spy(doc);
    doc.setContent(new JSONObject());
    verify(doc).setContent(any(JSONObject.class), eq(false));
  }

  @Test
  public void testSetContent() throws JSONException {
    assertEquals(doc.getContent().toString(), new JSONObject().toString());
    JSONObject data = new JSONObject();
    data.put("test", "some content");
    doc.setContent(data, false);
    assertEquals(doc.getContent().get("test"), "some content");
    data = new JSONObject();
    data.put("test 2", "some other content");
    doc.setContent(data, true);
    assertEquals(doc.getContent().get("test 2"), "some other content");
    assertTrue(doc.getContent().isNull("test"));
  }

  @Test
  public void testGetContent() throws JSONException {
    doc.setContent(null);
    assertNotNull(doc.getContent());
    doc.setContent("foo", "bar");
    assertEquals(doc.getContent().getString("foo"), "bar");
    assertNull(doc.getContent("!exist"));
  }

  @Test
  public void testDocumentWithMetadata() throws JSONException {
    JSONObject content = new JSONObject();
    content.put("foo", "bar");

    JSONObject meta = new JSONObject();
    meta.put("author", "foo");

    doc = new Document(new Collection(k, "test", "index"), content, meta);
    assertEquals(doc.getMeta().getString("author"), "foo");
  }

  @Test
  public void checkSetHeadersVariants() {
    doc = spy(doc);
    doc.setHeaders(new JSONObject());
    verify(doc).setHeaders(any(JSONObject.class), eq(false));
  }

  @Test(expected = RuntimeException.class)
  public void testSetHeadersException() {
    JSONObject fake = spy(new JSONObject());
    doThrow(JSONException.class).when(fake).toString();
    doc.setHeaders(fake, true);
  }

  @Test
  public void testSetHeaders() throws JSONException {
    doc.setHeaders(null, true);
    assertNotNull(doc.getHeaders());
    JSONObject headers = new JSONObject();
    headers.put("foo", "bar");
    doc.setHeaders(headers, true);
    assertEquals(doc.getHeaders().getString("foo"), "bar");
    headers.put("oof", "baz");
    doc.setHeaders(headers);
    assertEquals(doc.getHeaders().getString("foo"), "bar");
    assertEquals(doc.getHeaders().getString("oof"), "baz");
  }

  @Test
  public void testGetHeaders() throws JSONException {
    doc.setHeaders(null);
    assertNotNull(doc.getHeaders());
    JSONObject headers = new JSONObject();
    headers.put("foo", "bar");
    doc.setHeaders(headers);
    assertEquals(doc.getHeaders().getString("foo"), "bar");
  }

  @Test
  public void testGetVersion() throws JSONException {
    assertEquals(-1, doc.getVersion());
    doc.setVersion(42);
    assertEquals(42, doc.getVersion());
  }
}
