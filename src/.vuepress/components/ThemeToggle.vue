<template>
  <button
    type="button"
    class="theme-toggle"
    :aria-label="isDark ? 'Switch to the light theme' : 'Switch to the dark theme'"
    :title="isDark ? 'Light theme' : 'Dark theme'"
    @click="toggle"
  >
    <font-awesome-icon :icon="isDark ? 'fa-solid fa-sun' : 'fa-solid fa-moon'" />
  </button>
</template>

<script>
import { STORAGE_KEY, applyTheme, resolveInitialTheme } from '../theme-mode';

export default {
  name: 'ThemeToggle',
  data() {
    return { isDark: false };
  },
  methods: {
    toggle() {
      this.isDark = !this.isDark;

      const theme = this.isDark ? 'dark' : 'light';

      applyTheme(theme);

      try {
        localStorage.setItem(STORAGE_KEY, theme);
      } catch (error) {
        // Private mode and blocked storage: the choice just won't be remembered
      }
    },
  },
  mounted() {
    /**
     * The inline script in <head> has already applied the theme to avoid a
     * flash; read back what it decided rather than deciding again.
     */
    this.isDark = document.documentElement.dataset.theme === 'dark';

    const query = window.matchMedia('(prefers-color-scheme: dark)');

    this.onSystemChange = () => {
      let stored = null;

      try {
        stored = localStorage.getItem(STORAGE_KEY);
      } catch (error) {
        stored = null;
      }

      // An explicit choice wins over the system preference
      if (stored === 'dark' || stored === 'light') {
        return;
      }

      this.isDark = query.matches;
      applyTheme(resolveInitialTheme());
    };

    query.addEventListener('change', this.onSystemChange);
  },
  beforeUnmount() {
    window
      .matchMedia('(prefers-color-scheme: dark)')
      .removeEventListener('change', this.onSystemChange);
  },
};
</script>

<style>
.theme-toggle {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 4rem;
  height: 4rem;
  border-radius: 50%;
  color: var(--c-text-on-header);
  cursor: pointer;
  transition: background-color 0.125s;

  &:hover,
  &:focus-visible {
    background-color: hsla(0, 0%, 100%, 0.12);
  }
}
</style>
