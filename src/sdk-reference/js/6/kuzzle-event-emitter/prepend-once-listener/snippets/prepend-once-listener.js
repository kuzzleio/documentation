const eventEmitter = new KuzzleEventEmitter();

const listener1 = () => console.log('listener1');
const listener2 = () => console.log('listener1');

eventEmitter.addListener('connected', listener1);
eventEmitter.prependOnceListener('connected', listener2);

if (eventEmitter.listeners('connected')[0] === listener2) {
  console.log('Successfully prepended 1 new once listener');
}
