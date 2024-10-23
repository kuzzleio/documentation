<script setup lang="ts">
import type { VNode } from 'vue'

withDefaults(
  defineProps<{
    /**
     * The type of the badge
     */
    type?: string
    /**
     * The text of the badge
     */
    text?: string
    /**
     * The vertical align of the badge
     */
    vertical?: string
  }>(),
  {
    type: 'tip',
    text: '',
    vertical: undefined,
  },
)

defineSlots<{
  default?: () => VNode | VNode[] | string | null
}>()
</script>

<template>
  <span
    class="vp-badge"
    :class="type"
    :style="{
      verticalAlign: vertical,
    }"
  >
    <slot>{{ text }}</slot>
  </span>
</template>