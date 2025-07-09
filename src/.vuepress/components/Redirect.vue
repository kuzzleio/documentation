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
        console.log(props.to);
        console.log('window.location', window.location);

        // Remove leading/trailing slashes and ensure proper joining
        const joinUrl = (base, path) => {
          const cleanBase = base.replace(/\/$/, '');
          const cleanPath = path.replace(/^\//, '');
          return `${cleanBase}/${cleanPath}`;
        };

        const targetUrl = props.to.endsWith('/') ? props.to : `${props.to}/`;
        const currentUrl = window.location.href;

        window.location.replace(joinUrl(currentUrl, targetUrl));
      }
    });

    return {};
  },
};
</script>
