import { DEFAULT_VERSION, createMetaTag } from '../helpers';

export default {
  mounted() {
    const head = document.head;

    head.appendChild(createMetaTag('article:tag', this.$page.title));
    head.appendChild(createMetaTag('og:title', this.$page.title));
    head.appendChild(createMetaTag('og:url', document.URL));
    head.appendChild(
      createMetaTag(
        'og:image',
        `${window.location.origin}/favicon/favicon-196x196.png`
      )
    );
    head.appendChild(
      createMetaTag(
        'twitter:image',
        `${window.location.origin}/favicon/favicon-196x196.png`
      )
    );
    head.appendChild(
      createMetaTag(
        'og:description',
        this.$page.frontmatter.description || this.$site.description
      )
    );

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
