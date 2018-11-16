
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

$elements = [
  ['score' => 1, 'member' => 'foo'],
  ['score' => 2, 'member' => 'bar'],
  ['score' => 3, 'member' => 'baz']
];

try {
  $value = $kuzzle->memoryStorage()->zadd('key', elements);
}
catch (ErrorException $e) {

}
