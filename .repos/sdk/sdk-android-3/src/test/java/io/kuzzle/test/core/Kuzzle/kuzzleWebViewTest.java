package io.kuzzle.test.core.Kuzzle;

import android.webkit.WebView;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import io.kuzzle.sdk.core.Options;
import io.kuzzle.sdk.enums.Mode;
import io.kuzzle.sdk.listeners.ResponseListener;
import io.kuzzle.test.testUtils.KuzzleExtend;
import tech.gusavila92.websocketclient.WebSocketClient;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class kuzzleWebViewTest {

  private KuzzleExtend kuzzle;
  private KuzzleExtend.KuzzleWebViewClient webViewClient;
  private WebSocketClient s;
  private ResponseListener listener;

  @Before
  public void setUp() throws URISyntaxException {
    Options options = new Options();
    options.setConnect(Mode.MANUAL);
    options.setDefaultIndex("testIndex");

    s = mock(WebSocketClient.class);
    kuzzle = new KuzzleExtend("localhost", options, null);
    kuzzle.setSocket(s);
    webViewClient = kuzzle.getKuzzleWebViewClient();

    listener = new ResponseListener<Object>() {
      @Override
      public void onSuccess(Object object) {

      }

      @Override
      public void onError(JSONObject error) {

      }
    };
  }

  @Test
  public void testShouldLoadFinalUrl() {
    WebView view = mock(WebView.class);
    webViewClient.shouldOverrideUrlLoading(view, "url");
    verify(view).loadUrl(eq("url"));
  }
}
