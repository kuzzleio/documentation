package io.kuzzle.test.core.KuzzleDataCollection;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Collection;
import io.kuzzle.sdk.core.CollectionMapping;
import io.kuzzle.sdk.core.Document;
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.core.Room;
import io.kuzzle.sdk.core.RoomOptions;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.sdk.state.States;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class factoriesTest {
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
  public void testRoomFactory() {
    assertThat(collection.room(mock(RoomOptions.class)), instanceOf(Room.class));
    assertThat(collection.room(), instanceOf(Room.class));
  }

  @Test
  public void testDocumentFactory() throws JSONException {
    assertThat(collection.document(), instanceOf(Document.class));
    assertThat(collection.document("id"), instanceOf(Document.class));
    assertThat(collection.document("id", new JSONObject()), instanceOf(Document.class));
    assertThat(collection.document(new JSONObject()), instanceOf(Document.class));
  }

  @Test
  public void testDataMappingFactory() {
    assertThat(collection.collectionMapping(), instanceOf(CollectionMapping.class));
    assertThat(collection.collectionMapping(new JSONObject()), instanceOf(CollectionMapping.class));
  }
}
