<template>
  <a :href="btnUrl" class="btnCta">{{ btnTextInside }}</a>
</template>

<script>
export default {
  name: 'Cta',
  props: {
    btnTextInside: {
      type: String,
      required: true
    },
    btnUrl: {
      type: String,
      required: true
    }
  }
};
</script>

<style lang="scss">
.btnCta {
  color: white;
  min-height: 30px;
  min-width: 140px;
  padding: 10px 20px;
  font-size: 1.3em;
  color: #fff !important;
  background-color: #e94e77;
}
</style>
