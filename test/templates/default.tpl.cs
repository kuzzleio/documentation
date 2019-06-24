#r "Kuzzle.dll"
using System;
using KuzzleSdk;
using KuzzleSdk.API;
using KuzzleSdk.Protocol;
using System.Threading.Tasks;

KuzzleSdk.Kuzzle kuzzle = new KuzzleSdk.Kuzzle(new WebSocket("kuzzle"));

await kuzzle.ConnectAsync();

[snippet-code]
