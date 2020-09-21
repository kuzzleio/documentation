export default {
  mounted() {
    const head = document.head;

    const metaTitle = document.createElement('meta');
    metaTitle.setAttribute('property', 'article:tag');
    metaTitle.setAttribute('content', this.$page.title);
    head.appendChild(metaTitle);

    if (this.$page.currentSection) {
      const metaSection = document.createElement('meta');
      metaSection.setAttribute('property', 'article:section');
      metaSection.setAttribute('content', this.$page.currentSection.name);
      head.appendChild(metaSection);
    }
  }
};
