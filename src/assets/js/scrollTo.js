var scrollTo = {};

scrollTo.minimumScroll = 50;

scrollTo.init = function (elSelector, targetSelector) {
  var
    el = document.querySelector(elSelector),
    target = document.querySelector(targetSelector);

  if (this.hasScrollbar(el) && ! this.isInViewport(target)) {
    el.scrollTop = target.offsetTop - this.minimumScroll;
  }
};

scrollTo.hasScrollbar = function (el) {
  return el.scrollHeight > el.clientHeight;
};

scrollTo.isInViewport = function (el) {
  var rect = el.getBoundingClientRect();

  return rect.bottom > 0 &&
      rect.right > 0 &&
      rect.left < (window.innerWidth || document.documentElement.clientWidth) &&
      rect.top < (window.innerHeight || document.documentElement.clientHeight);
};


$(document).ready(function() {
  scrollTo.init('.md-sidebar--primary .md-sidebar__scrollwrap', '.md-nav__link--active');
});

