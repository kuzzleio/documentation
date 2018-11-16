
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Profile;
use \Kuzzle\Util\ProfilesSearchResult;

// optional: search only for profiles referring the listed roles
$filters = [
  'roles' => [
      'admin',
      'myrole'
  ]
];

// optional: result pagination configuration
$options = [
  'from' => 0,
  'size' => 1,
  'scroll' => '1m'
];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

try {
  $result = $security->searchProfiles($filters, $options);

  // $result instanceof ProfilesSearchResult
  foreach($result->getProfiles() as $profile) {
    // $profile instanceof Profile
  }
}
catch (ErrorException $e) {

}
