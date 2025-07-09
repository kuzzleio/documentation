<template>
  <div>Redirecting...</div>
</template>

<script>
import { useRouter } from 'vuepress/client';

export default {
  name: 'redirect',
  props: {
    to: {
      type: String,
      required: true,
    },
  },
  mounted() {
    const router = useRouter();
    const targetUrl = this.to.endsWith('/') ? this.to : `${this.to}/`;
    router.push(targetUrl);
  },
};
</script>
