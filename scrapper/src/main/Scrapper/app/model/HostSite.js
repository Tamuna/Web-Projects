Ext.define('Scrapper.model.HostSite', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'url', type: 'string'},
        {name: 'dataType', type: 'string'}
    ],

    proxy: {
        type: 'rest',
        url: 'http://localhost:8080/Scrapper/services/links',
        extraParams : {

        },
        reader: {
            type: "json"
        },
        writer: {
            type: "json"
        }
    }
});
