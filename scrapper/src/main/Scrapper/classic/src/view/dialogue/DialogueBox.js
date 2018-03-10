Ext.define('Scrapper.view.dialogue.DialogueBox', {
    extend: 'Ext.form.Panel',

    requires: [
        'Scrapper.view.dialogue.DialogueBoxController',
        'Ext.button.Button',
        'Ext.form.field.Base'
    ],

    xtype: 'dialoguebox',

    controller: 'dialoguebox',

    floating: true,

    centered: true,

    modal: true,

    title: 'Data Access Destination',

    html: "<img src = \"https://patientengagementhit.com/images/site/article_headers/_normal/2017-01-12-data-protection.gif\"/>",

    buttons: [{
        xtype: 'button',
        text: 'Clear Database, Scrap Again',
        iconCls: 'x-fa fa-trash',
        handler: 'clearData'
    }, {
        xtype: 'button',
        text: 'Get Data From Database',
        iconCls: 'x-fa fa-home',
        handler: 'getOldData'
    }]
});
