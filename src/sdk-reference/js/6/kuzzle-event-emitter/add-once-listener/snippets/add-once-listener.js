const eventEmitter = new KuzzleEventEmitter();

eventEmitter
  .addOnceListener('connected', () => console.log('connected'))
  .addOnceListener('disconnected', () => console.log('disconnected'));

console.log('Successfully added 2 new once listeners');
