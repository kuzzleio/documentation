import { PluginFunction } from 'vuepress';
import { getDirname, path } from 'vuepress/utils';

const __dirname = getDirname(import.meta.url);

export const PLUGIN_NAME = '@kuzzleio/vuepress-plugin-meta-tags';

export const metaTagsPlugin =
  (): PluginFunction =>
  () => {
    return {
      name: PLUGIN_NAME,
      clientConfigFile: path.resolve(__dirname, './client.ts'),
    }
  }
