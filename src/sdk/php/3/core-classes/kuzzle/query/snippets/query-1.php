
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

$queryArgs = [
  'controller' => 'controller',
  'action' => 'action'
];

$query = [
  'body' => [
    'foo' => 'bar'
  ],
  'other' => 'argument'
];

try {
  $response = $kuzzle->query($queryArgs, $query);
}
catch (ErrorException $e) {

}
