<template>
  <div class="alert alert-warning" id="banner">
    <p>
      <slot></slot>
    </p>
  </div>
</template>

<script>
export default {
  name: 'WarningBanner'
};
</script>

<style>
#banner {
  font-size: 18px;
  vertical-align: middle;
}
</style>
