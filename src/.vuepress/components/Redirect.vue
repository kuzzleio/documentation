<template>
  <div>Redirecting...</div>
</template>

<script>
import { onMounted, nextTick } from 'vue';

export default {
  name: 'redirect',
  props: {
    to: {
      type: String,
      required: true,
    },
  },
  setup(props) {
    onMounted(async () => {
      await nextTick();
      
      if (typeof window !== 'undefined') {
        const targetUrl = props.to.endsWith('/') ? props.to : `${props.to}/`;
        window.location.replace(targetUrl);
      }
    });
    
    return {};
  },
};
</script>
