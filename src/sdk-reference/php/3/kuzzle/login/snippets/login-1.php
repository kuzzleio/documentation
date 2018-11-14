
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

// Expiration time is expressed as a string following the
// time conversion library: https://www.npmjs.com/package/ms
$expiresIn = "1h";

try {
  $result = $kuzzle->login('local', [
    'username' => 'myusername',
    'password' => 'secret'
  ], $expiresIn);

  // ...
}
catch (ErrorException $e) {
  // Handle error
}
