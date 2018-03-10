Ext.define('Scrapper.view.checkin.Checkin', {
    extend: 'Ext.form.Panel',

    xtype: 'registrationForm',

    controller: 'checkin',

    requires: [
        'Scrapper.view.checkin.CheckinController',
        'Ext.form.field.ComboBox'
    ],

    controller: 'checkin',

    frame: true,

    title: 'Link Registration Form',

    width: '100%',

    height: '100%',

    bodyPadding: 10,

    layout: 'form',
    defaultType: 'textfield',

    items: [{
        id: "linkField",
        name: 'URL',
        emptyText: 'URL To Scrap',
        fieldLabel: 'Page to Scrap'
    }, {
        id: 'typefield',
        xtype: 'combobox',
        name: 'type',
        fieldLabel: 'Source Type',
        store: [
            {text: "Picture Sources"},
            {text: "Link Sources"},
            {text: 'Image and Link Sources'}
        ]
    }],

    buttons: [
        {
            xtype: 'button',
            text: 'Submit',
            iconCls: 'x-fa fa-check',
            handler:
                function (view, index, item, record) {
                    var win = Ext.create('Scrapper.view.dialogue.DialogueBox');
                    win.show();
                }
        }]
});
