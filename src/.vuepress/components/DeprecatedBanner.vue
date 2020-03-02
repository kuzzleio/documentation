<template>
  <div class="alert alert-warning" id="deprecatedBanner">
    <p>Beware, this SDK is deprecated.</p>
  </div>
</template>

<script>
import sdks from '../sdk.json';

export default {
  mounted() {
    console.log('Le console.log fonctionne', sdks[1][3]);
  }
};
</script>

<style>
#deprecatedBanner {
  font-size: 22px;
  vertical-align: middle;
}
</style>
