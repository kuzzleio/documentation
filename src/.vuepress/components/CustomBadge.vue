<template>
  <div>
    <Badge :type="type" :text="text" />
  </div>
</template>

<script>
import Badge from './Badge.vue';

export default {
  components: {
    Badge
  },
  props: {
    text: {
      type: String,
      required: true
    },
    type: {
      type: String,
      required: true,
      validator: function(value) {
        return ['tip', 'error', 'warning'].indexOf(value) !== -1;
      }
    }
  }
};
</script>
