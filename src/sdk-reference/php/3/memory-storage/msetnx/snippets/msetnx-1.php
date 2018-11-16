
<?php

use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

$entries = [
  ['key' => 'key1', 'value' => 'foo'],
  ['key' => 'key2', 'value' => 'bar'],
  ['key' => '...', 'value' => '...']
];

try {
  $status = $kuzzle->memoryStorage()->msetnx(entries);
}
catch (ErrorException $e) {

}
