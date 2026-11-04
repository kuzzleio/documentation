export const STORAGE_KEY = 'kuzdoc-theme';

export type ThemeMode = 'light' | 'dark';

export const applyTheme = (theme: ThemeMode) => {
  document.documentElement.dataset.theme = theme;
};

/**
 * Stored choice if there is one, the system preference otherwise.
 */
export const resolveInitialTheme = (): ThemeMode => {
  let stored: string | null = null;

  try {
    stored = localStorage.getItem(STORAGE_KEY);
  } catch (error) {
    stored = null;
  }

  if (stored === 'dark' || stored === 'light') {
    return stored;
  }

  return window.matchMedia('(prefers-color-scheme: dark)').matches
    ? 'dark'
    : 'light';
};
