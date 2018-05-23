var document = kuzzle.collection('collection', 'index').document('id');

document.deletePromise().then(result => {
    console.log('document successfully deleted');
});