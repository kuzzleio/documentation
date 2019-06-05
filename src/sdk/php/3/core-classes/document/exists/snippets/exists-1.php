
<?php

use \Kuzzle\Document;

// ...

/**
 * @var $document Document
 */

try {
  $exists = $document->exists();
} catch (ErrorException $e) {

}
