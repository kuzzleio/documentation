<template>
  <div>Redirecting...</div>
</template>

<script>
import { useRouter } from '@vuepress/client';
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
        const targetUrl = this.to.endsWith('/') ? this.to : `${this.to}/`;
        window.location.replace(targetUrl);
      }
    });
    
    return {};
  },
};
</script>
