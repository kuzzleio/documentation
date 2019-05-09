/* eslint no-invalid-this: 0 */
this.props = {
  setMessages: () => {}
};

/* snippet:start */
kuzzle.realtime.subscribe('chat', 'messages', {}, notif => {
  if (notif.type === 'document' && notif.action === 'create') {
    const { _source: message } = notif.result;
    this.props.setMessages([message]);
  }
});
/* snippet:end */
