
<?php
use \Kuzzle\Kuzzle;
use \Kuzzle\Security\User;

$kuzzle = new Kuzzle('localhost');
$newContent = [
  'firstname' => 'My Name Is',
  'lastname' => 'Jonas'
];

try {
  $updatedUser = $kuzzle->updateSelf($newContent);
  // $updatedUser instanceof User
}
catch (ErrorException $e) {

}
