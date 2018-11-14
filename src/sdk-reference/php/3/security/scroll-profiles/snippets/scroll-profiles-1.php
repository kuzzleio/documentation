
<?php

use \Kuzzle\Kuzzle;

$scrollId = 'myScrollId';
$options = [];

$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->security()->scrollProfiles($profileId, $options);
}
catch (ErrorException $e) {
  // Handle error
}
