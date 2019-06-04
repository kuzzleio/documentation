
<?php

use \Kuzzle\Document;

// ...

/**
 * @var $document Document
 */

try {
  $document->publish();
} catch (ErrorException $e) {

}
