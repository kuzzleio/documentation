<template>
  <div class="ContentFeedback">
    <p>
      <a
        :href="`https://github.com/${repoSlug}/issues/new?title=[${$page.path}]&template=bug_report.md`"
        target="_blank"
      >
        Report an error on this page&nbsp;
        <i class="fa fa-github"></i>
      </a>
      <span class="separator">|</span>
      <a
        :href="`https://github.com/${repoSlug}/issues/new?title=[${$page.path}]&template=content_request.md`"
        target="_blank"
      >
        Request new content for this page&nbsp;
        <i class="fa fa-github"></i>
      </a>
    </p>
  </div>
</template>

<script>
export default {
  computed: {
    repoSlug() {
      return REPO_SLUG;
    }
  }
};
</script>

<style>
</style>
