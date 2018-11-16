
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $status = $kuzzle->memoryStorage()->pexpireat('key', 1488540242465);
}
catch (ErrorException $e) {

}
