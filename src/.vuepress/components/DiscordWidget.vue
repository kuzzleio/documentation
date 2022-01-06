<template>
  <div class="discord-widget">
    <div class="discord-widget-iframe" v-if="isIframeShown">
      <iframe
        src="https://discord.com/widget?id=306054601436561410&theme=dark"
        width="250"
        height="520"
        allowtransparency="true"
        frameborder="0"
        sandbox="allow-popups allow-popups-to-escape-sandbox allow-same-origin allow-scripts"
      ></iframe>
    </div>
    <div class="discord-widget-button">
      <img src="/logos/discord.png" alt="Discord logo" @click="onButtonClick" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'DiscordWidget',

  data() {
    return {
      isIframeShown: false,
    }
  },

  methods: {
    onButtonClick() {
      this.isIframeShown = !this.isIframeShown
    }
  }


};
</script>

<style lang="scss">
.discord-widget {
  position: fixed;
  cursor: pointer;
  right: 1.2em;
  bottom: 0.5em;

  &-button {
    width: 60px;

    &:hover {
      width: 62px;
    }

    img {
      width: 100%;
    }
  }

  &-iframe {
    position: fixed;
    right: 1.2em;
    bottom: 6.3em;
  }
}
</style>
