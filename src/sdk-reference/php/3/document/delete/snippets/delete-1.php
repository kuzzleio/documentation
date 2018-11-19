
<?php

use \Kuzzle\Document;

// ...

/**
 * @var $document Document
 */

try {
  $result = $document->delete();
} catch (ErrorException $e) {

}
