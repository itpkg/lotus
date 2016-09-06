//= require jquery
//= require jquery.turbolinks
//= require jquery_ujs

//= require bootstrap-sass/assets/javascripts/bootstrap-sprockets
//= require marked/lib/marked

$(function () {
    $("p.markdown").each(function () {
        $(this).html(marked($(this).text()));
    });
});


//= require turbolinks