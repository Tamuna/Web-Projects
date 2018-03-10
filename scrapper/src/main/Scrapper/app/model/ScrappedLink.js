Ext.define('Scrapper.model.ScrappedLink', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.proxy.Rest'
    ],

    fields: [
        {name: 'link', type: 'string'},
        {name: 'host', type: 'string'},
        {name: 'type', type: 'string'}
    ],

    proxy: {
        type: 'rest',
        url: 'http://localhost:8080/Scrapper/services/links',
        reader: {
            type: "json",
            rootProperty: 'list',
            totalProperty: 'totalResults'
        },
        writer: {
            type: "json"
        }
    }
});
