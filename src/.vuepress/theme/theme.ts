import { getDirname, path } from '@vuepress/utils';

const __dirname = getDirname(import.meta.url);

export const kuzdocTheme = () =>
({
  name: '@kuzzleio/vuepress-theme-kuzdoc',
  clientConfigFile: path.resolve(__dirname, './client.ts'),
});
