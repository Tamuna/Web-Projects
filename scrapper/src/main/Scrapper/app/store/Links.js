Ext.define('Scrapper.store.Links', {
    extend: 'Ext.data.Store',
    alias: 'store.links',

    requires: [
        'Scrapper.model.ScrappedLink'
    ],

    baseParams: {start: 0, limit: 20, page : 1},

    model: 'Scrapper.model.ScrappedLink',

    pageSize: 20
});
