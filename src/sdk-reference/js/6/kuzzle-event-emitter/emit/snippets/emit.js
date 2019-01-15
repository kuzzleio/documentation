const eventEmitter = new KuzzleEventEmitter();

eventEmitter.addListener('connected', msg => console.log(`Hello ${msg}`));

eventEmitter.emit('connected', 'World');
