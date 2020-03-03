<template>
  <div class="alert alert-warning" id="deprecatedBanner">
    <p>
      This SDK has been deprecated because of stability issues. It is not
      advised to use it in a production environment.
    </p>
  </div>
</template>

<script>
import sdks from '../sdk.json';
</script>

<style>
#deprecatedBanner {
  font-size: 22px;
  vertical-align: middle;
}
</style>
