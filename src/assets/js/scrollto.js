
$(document).ready(function () {
    $('.md-nav--secondary .md-nav__link').on('click', function (e) {
        var anchor = $(this).attr('href')
        e.preventDefault();
        $('html, body').animate({
            scrollTop: $(anchor).offset().top - 100
        }, 1000);
    })    
});
