<template>
  <div class="btnCtaMain">
    <a :href="btnUrl" class="btnCta">{{ btnTextInside }}</a>
  </div>
</template>

<script>
export default {
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
.btnCtaMain {
  text-align: center;
  padding: 10px 0;
}
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
