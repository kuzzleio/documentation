import { DEFAULT_VERSION, createMetaTag, createCanonical } from '../helpers';

export default {
  mounted() {
    const head = document.head;

    const host = document.location.host; // https://docs-kuzzle-io for example 
    const sectionPath = document.location.pathname.split("/").slice(0, 4).join('/'); // /core/2/guides/introduction/what-is-kuzzle/ for example

    const url = `${host}${sectionPath}`;

    head.appendChild(createCanonical(url));
    head.appendChild(createMetaTag('article:tag', this.$page.title));
    head.appendChild(createMetaTag('og:title', this.$page.title));
    head.appendChild(createMetaTag('og:url', document.URL));
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
