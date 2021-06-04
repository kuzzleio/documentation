import { DEFAULT_VERSION, createMetaTag } from '../helpers';

export default {
  mounted() {
    const head = document.head;

    head.appendChild(createMetaTag('article:tag', this.$page.title));
    head.appendChild(createMetaTag('og:title', this.$page.title));

    if (this.$page.frontmatter.description) {
      head.appendChild(
        createMetaTag('og:description', this.$page.frontmatter.description)
      );
    }

    if (this.$page.currentSection) {
      head.appendChild(
        createMetaTag('article:section', this.$page.currentSection.name)
      );

      if (this.$page.currentSection.kuzzleMajor < DEFAULT_VERSION) {
        head.appendChild(createMetaTag('robot', 'noindex'));
      }
    }
  }
};
