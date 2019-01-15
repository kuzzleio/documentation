const eventEmitter = new KuzzleEventEmitter();

eventEmitter.addListener('CONNECTED', () => console.log('connected'));
eventEmitter.addListener('CONNECTED', () => console.log('connected bis'));

const listeners = eventEmitter.listeners('CONNECTED');

console.log(`There is ${listeners.length} listeners binded to the event CONNECTED`);
