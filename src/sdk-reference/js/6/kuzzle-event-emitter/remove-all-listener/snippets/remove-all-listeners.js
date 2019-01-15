const eventEmitter = new KuzzleEventEmitter();

eventEmitter
  .addListener('disconnected', () => console.log('disconnected'))
  .addListener('disconnected', () => console.log('disconnected'));

eventEmitter.removeAllListeners('disconnected');

if (eventEmitter.listeners('disconnected').length === 0) {
  console.log('Successfully removed all listeners');
}
