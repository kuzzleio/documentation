const eventEmitter = new KuzzleEventEmitter();

eventEmitter
  .addListener('connected', () => console.log('connected'))
  .addListener('disconnected', () => console.log('disconnected'));

console.log('Successfully added 2 new listeners');
