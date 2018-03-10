/**
 * Created by tamuna on 23.10.2017.
 */
Ext.define('Scrapper.view.dialogue.DialogueBoxController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.dialoguebox',

    getOldData: function () {
        var hostUrl = Ext.ComponentQuery.query('#linkField')[0].getValue();
        var dataType = Ext.ComponentQuery.query('#typefield')[0].getValue();
        var store = Ext.getStore('mainStore');
        store.getProxy().url = 'http://localhost:8080/Scrapper/services/links';
        store.getProxy().extraParams = {
            url: hostUrl,
            type: dataType
        };
        store.load({page: 1, start: 0, limit: 20});
        var view = this.getView();
        view.destroy();
        Ext.getCmp('navigation_panel').setActiveTab(1);
    },

    clearData: function () {
        var hostUrl = Ext.ComponentQuery.query('#linkField')[0].getValue();
        var dataType = Ext.ComponentQuery.query('#typefield')[0].getValue();
        var requested = {};
        requested.url = hostUrl;
        requested.dataType = dataType;
        Ext.Ajax.request({
            method: 'DELETE',
            url: "http://localhost:8080/Scrapper/services/links",
            headers: {
                'Content-Type': 'application/json'
            },
            jsonData: JSON.stringify(requested),

            success: function (response) {
                var req = Ext.create('Scrapper.model.HostSite', {url: hostUrl, dataType: dataType});
                req.save({
                    success: function () {
                        var store = Ext.getStore('mainStore');
                        store.getProxy().url = 'http://localhost:8080/Scrapper/services/links';
                        store.getProxy().extraParams = {
                            url: hostUrl,
                            type: dataType
                        };
                        store.load({page: 1, start: 0, limit: 20});
                    }
                });
            },
            failure: function (response) {
                console.log('Delete failed.');
                console.log(response);
            }
        });
        var view = this.getView();
        view.destroy();
        Ext.getCmp('navigation_panel').setActiveTab(1);
    }
});