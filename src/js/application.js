define(['jquery',
        'handlebars',
        'text!templates/index.hbs',
        'text!templates/templates.hbs',
        'text!templates/snippets.hbs', 'prettify'], function($, Handlebars, index, templates, snippets) {

    'use strict';

    function API() {

        this.CONFIG = {
            placeholder_id: 'placeholder'
        };

    }

    API.prototype.init = function (config) {

        /* Extend default configuration. */
        this.CONFIG = $.extend(true, {}, this.CONFIG, config);

        /* Register partials. */
        Handlebars.registerPartial('menu', $(templates).filter('#menu').html());
        Handlebars.registerPartial('introduction', $(templates).filter('#introduction').html());
        Handlebars.registerPartial('introduction_datasources', $(templates).filter('#introduction_datasources').html());
        Handlebars.registerPartial('introduction_languages', $(templates).filter('#introduction_languages').html());
        Handlebars.registerPartial('table_headers', $(templates).filter('#table_headers').html());
        Handlebars.registerPartial('table_commons', $(templates).filter('#table_commons').html());
        Handlebars.registerPartial('home', $(templates).filter('#home').html());
        Handlebars.registerPartial('home_groups', $(templates).filter('#home_groups').html());
        Handlebars.registerPartial('home_db_updates', $(templates).filter('#home_db_updates').html());
        Handlebars.registerPartial('home_news', $(templates).filter('#home_news').html());
        Handlebars.registerPartial('home_coming_up', $(templates).filter('#home_coming_up').html());
        Handlebars.registerPartial('download', $(templates).filter('#download').html());
        Handlebars.registerPartial('download_groups_domains', $(templates).filter('#download_groups_domains').html());
        Handlebars.registerPartial('download_metadata', $(templates).filter('#download_metadata').html());
        Handlebars.registerPartial('download_bulk', $(templates).filter('#download_bulk').html());
        Handlebars.registerPartial('download_filters', $(templates).filter('#download_filters').html());
        Handlebars.registerPartial('download_codelists', $(templates).filter('#download_codelists').html());
        Handlebars.registerPartial('download_data', $(templates).filter('#download_data').html());

        /* Snippets. */
        Handlebars.registerPartial('home_groups_in', $(snippets).filter('#home_groups_in').html());
        Handlebars.registerPartial('home_groups_out', $(snippets).filter('#home_groups_out').html());

        /* Load template. */
        var source = $(index).filter('#structure').html();
        var template = Handlebars.compile(source);
        var dynamic_data = {};
        var html = template(dynamic_data);
        $('#' + this.CONFIG.placeholder_id).html(html);

        prettyPrint();

    };

    return API;

});