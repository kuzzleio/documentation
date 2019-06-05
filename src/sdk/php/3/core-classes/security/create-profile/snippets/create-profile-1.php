
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Profile;

$profileId = 'myProfile';
$policies = [
  [
    'roleId' => 'myRole'
  ],
  [
    'roleId' => 'anonymous',
    'restrictedTo' => [
      ['index' => 'my-second-index', 'collection' => ['my-collection']]
    ]
  ]
];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

try {
  $profile = $security->createProfile($profileId, $profileDefinition);

  // $profile instanceof Profile
}
catch (ErrorException $e) {

}
