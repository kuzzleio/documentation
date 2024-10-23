import { PluginFunction } from 'vuepress';

export const PLUGIN_NAME = '@kuzzleio/vuepress-plugin-frontmatter-in-route-meta';

export const frontmatterInRouteMetaPlugin =
  (): PluginFunction =>
  () => {
    return {
      name: PLUGIN_NAME,
      extendsPage: (page) => {
        page.routeMeta.frontmatter = page.frontmatter;
      },
    };
  }
