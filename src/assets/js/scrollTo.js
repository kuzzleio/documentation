const scrollTo = {

  minimumScroll: 50,

  init: (elSelector, targetSelector) => {
    const
      el = document.querySelector(elSelector),
      target = document.querySelector(targetSelector);
    
    if (!scrollTo.isInViewport(target)) {
      setTimeout(function() {
        el.scrollTop = target.offsetTop - scrollTo.minimumScroll;
      }, 10);
    }
  },

  isInViewport: target => {
    const rect = target.getBoundingClientRect();

    return rect.bottom > 0 &&
      rect.right > 0 &&
      rect.left < (window.innerWidth || document.documentElement.clientWidth) &&
      rect.top < (window.innerHeight || document.documentElement.clientHeight);
  },

};

$(document).ready(function() {
  scrollTo.init('.md-sidebar--primary .md-sidebar__scrollwrap', '.md-nav__link--active');
});

