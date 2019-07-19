package io.kuzzle.sdk.state;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * The type KuzzleQueue.
 *
 * @param <T> the type parameter
 */
public class KuzzleQueue<T> implements Iterable<T> {

  private Queue<T> _queue = new ArrayDeque<>();

  /**
   * Add to queue.
   *
   * @param object Item to queue
   */
  public synchronized void addToQueue(T object) {
    _queue.add(object);
  }

  /**
   * Dequeue t.
   *
   * @return Dequeued item
   */
  public synchronized T dequeue() {
    return _queue.poll();
  }

  private States _currentState = States.DISCONNECTED;

  /**
   * @param states New connection state value
   */
  public void setState(States states) {
    _currentState = states;
  }

  /**
   * @return Connection state value
   */
  public States state() {
    return _currentState;
  }

  /**
   * @return queue content
   */
  public Queue  getQueue() {
    return _queue;
  }

  @Override
  public Iterator<T> iterator() {
    return _queue.iterator();
  }
}
