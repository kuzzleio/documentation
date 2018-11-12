document.addEventListener("DOMContentLoaded", function() {
  
  // Add overlay and fix scroll bug when
  // drawer is open 
  document.querySelector('#drawer').addEventListener('change',function() {
    document.querySelector('body').classList.toggle('noscroll');
    document.querySelector('.overlay').classList.toggle('hidden');
  });
  
  // Close drawer when click on overlay
  document.querySelector('.overlay').addEventListener('click',function() {
    document.querySelector('.md-nav__title').click();
  });
  
});