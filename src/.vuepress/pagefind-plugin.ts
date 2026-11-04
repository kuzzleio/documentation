import type { Plugin } from 'vuepress';
import { path } from '@vuepress/utils';

/**
 * Builds the Pagefind search index of this VuePress instance once its pages
 * are generated, in `<dest>/pagefind/`.
 *
 * Every section is its own VuePress instance deployed under its own path, so
 * each one ships its own index next to its pages (`/sdk/js/7/pagefind/`...).
 * The search box merges them in the browser (see components/SiteSearch.vue).
 * A private section's index lives under the section's path, so CloudFront
 * serves it to signed-in visitors only, like the pages themselves.
 *
 * Only the `data-pagefind-body` element of the layout is indexed: header,
 * sidebars and footer stay out of the results.
 */
export const pagefindPlugin = (): Plugin => ({
  name: 'kuzdoc-pagefind',

  async onGenerated(app) {
    const { close, createIndex } = await import('pagefind');
    const dest = app.dir.dest();

    try {
      const { errors: createErrors, index } = await createIndex({});

      if (!index) {
        throw new Error(createErrors.join('\n'));
      }

      const { errors, page_count } = await index.addDirectory({ path: dest });

      if (errors.length) {
        throw new Error(errors.join('\n'));
      }

      const written = await index.writeFiles({
        outputPath: path.join(dest, 'pagefind'),
      });

      if (written.errors.length) {
        throw new Error(written.errors.join('\n'));
      }

      console.log(`Pagefind: ${page_count} pages indexed`);
    } finally {
      await close();
    }
  },
});
