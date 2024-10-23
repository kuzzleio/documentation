import { onMounted } from 'vue';
import { defineClientConfig, usePageData } from 'vuepress/client';

import { DEFAULT_VERSION, createMetaTag } from '../helpers';
import { ExtraPageData } from '../page-attributes';

export default defineClientConfig({
  setup() {
    onMounted(() => {
      const pageData = usePageData<ExtraPageData>();
      const head = document.head;

      head.appendChild(createMetaTag('article:tag', pageData.value.title));
      head.appendChild(createMetaTag('og:title', pageData.value.title));
      head.appendChild(createMetaTag('og:url', document.URL));
      head.appendChild(
        createMetaTag(
          'og:description',
          pageData.value.frontmatter.description || this.$site?.description
        )
      );

      if (pageData.value.currentSection) {
        head.appendChild(
          createMetaTag('article:section', pageData.value.currentSection.name)
        );

        if (pageData.value.currentSection.kuzzleMajor < DEFAULT_VERSION) {
          head.appendChild(createMetaTag('robot', 'noindex'));
        }
      }
    })
  },
});
