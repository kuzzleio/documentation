<template>
  <div class="alert alert-warning deprecatedBanner">
    <p>
      You are currently looking at the documentation of a previous version of
      Kuzzle. We strongly recommend that you use the
      <a :href="`/core/${kuzzleLatestMajor}/guides/introduction/what-is-kuzzle/`">latest version</a>.
      You can also use the version selector in the top menu.
    </p>
  </div>
</template>

<script>
import { DEFAULT_VERSION } from "../helpers";

export default {
  computed: {
    kuzzleLatestMajor() {
      return DEFAULT_VERSION;
    },
  },
};
</script>
