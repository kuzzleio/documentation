<template>
  <div>
    <PageLayout v-if="$page.frontmatter.type === 'page'" />
    <BranchLayout v-else />
  </div>
</template>

<script>
import PageLayout from './PageLayout';
import BranchLayout from './BranchLayout';

export default {
  components: { PageLayout, BranchLayout }
};
</script>
