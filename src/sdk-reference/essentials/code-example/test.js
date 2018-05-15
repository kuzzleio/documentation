var kuzzle = new Kuzzle('localhost', {
    defaultIndex: 'some index',
    autoReconnect: true,
    headers: { someheader: "value" },
    port: 7512
});