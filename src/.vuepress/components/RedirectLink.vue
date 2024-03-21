<template>
  <a :href="this.to">
    <slot />
  </a>
</template>

<script>
export default {
  name: 'RedirectLink',
  props: {
    to: {
      type: String,
      required: true,
    },
  }
};
</script>
