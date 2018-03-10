/**
 * Created by tamuna on 03.08.2017.
 */
Ext.define('Scrapper.view.list.LinkListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.linkGrid',

    //requires : ['Scrapper.view.popup.Popup'],

    // popupItem: function (view, record, item, index, eventObj) {
    //     var win = Ext.create('Scrapper.view.popup.Popup', {
    //         record: record,
    //         viewModel: {
    //             data: {
    //                 link: record
    //             }
    //         }
    //     });
    //     win.show();
    // },

    onFilter: function () {
        var hostUrl = Ext.ComponentQuery.query('#linkSearchField')[0].getValue();
        var dataType = Ext.ComponentQuery.query('#typeSearhField')[0].getValue();
        var host = Ext.ComponentQuery.query('#linkField')[0].getValue();
        var requested = {};
        requested.link = hostUrl;
        requested.type = dataType;
        requested.host = host;
        var req = Ext.create('Scrapper.model.ScrappedLink', {link: hostUrl, type: dataType});
        var store = Ext.getStore('mainStore');
        store.getProxy().url = 'http://localhost:8080/Scrapper/services/links/filter';
        store.getProxy().extraParams = {
            url: host,
            link : hostUrl,
            type : dataType,
        };
        store.load({page: 1, start: 0, limit: 20});
        // Ext.Ajax.request({
        //     method: 'POST',
        //     url: "http://localhost:8080/Scrapper/services/links/filter",
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     jsonData: JSON.stringify(requested),
        //
        //     success: function (response) {
        //
        //
        //     },
        //     failure: function (response) {
        //         console.log('Filter failed');
        //     }
        // });
    }
});