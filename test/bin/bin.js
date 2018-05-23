const Kuzzle = require('kuzzle-sdk');

let kuzzle = new Kuzzle('kuzzleHost');

return 
    kuzzle.connect()
    .then(kuzzle => {
        var document = kuzzle.collection('collection', 'index').document('id');

document.deletePromise().then(result => {
    console.log('document successfully deleted');
});   
    });