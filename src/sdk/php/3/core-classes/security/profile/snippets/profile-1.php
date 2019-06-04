
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Profile;

$profileId = 'myProfile';
$profileDefinition = [
  'policies' => [
    [
      'roleId' => 'myRole'
    ],
    [
      'roleId' => 'anonymous',
      'restrictedTo' => [
        ['index' => 'my-second-index', 'collection' => ['my-collection']]
      ]
    ]
  ]
];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

$profile = $security->profile($profileId, $profileDefinition);

// $profile instanceof Profile
