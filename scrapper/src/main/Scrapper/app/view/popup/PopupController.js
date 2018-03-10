Ext.define('Scrapper.view.popup.PopupController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.popupform',

    cancelUpdate: function () {
        var view = this.getView();
        view.destroy();
    }
});
