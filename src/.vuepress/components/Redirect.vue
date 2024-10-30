<template>
  <div>Redirecting...</div>
</template>

<script>
import { useRouter } from 'vuepress/client';

export default {
  name: 'redirect',
  props: {
    reload: {
      type: Boolean,
      default: false,
    },
    to: {
      type: String,
      required: true,
    },
  },
  setup() {
    return { router$: useRouter() };
  },
  mounted() {
    let target = this.to;
    if (!target.endsWith('/')) {
      target += '/';
    }

    if (!this.reload) {
      this.router$
        .push(target)
        .then(() => {
          console.log(`Redirected to ${target}`);
        })
        .catch((err) => {
          if (err) {
            // Nothing with the error
          }
        });
    } else {
      window.location.href = target;
    }
  },
};
</script>
