
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\MemoryStorage;

$kuzzle = new Kuzzle('localhost');

// using the static instance
$memoryStorage = $kuzzle->memoryStorage();

// or instantiating a new MemoryStorage object
$memoryStorage = new MemoryStorage($kuzzle);
