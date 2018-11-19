
<?php

use \Kuzzle\Document;

// ...

/**
 * @var $document Document
 */

try {
  $document->save();
} catch (ErrorException $e) {

}
