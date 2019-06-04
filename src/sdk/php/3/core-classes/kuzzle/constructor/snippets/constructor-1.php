
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost', [
  'defaultIndex' => 'some index',
  'port' => 7512
]);
