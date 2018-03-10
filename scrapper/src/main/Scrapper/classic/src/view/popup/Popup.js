Ext.define('Scrapper.view.popup.Popup', {
    extend: 'Ext.panel.Panel',

    requires: [
        'Scrapper.view.popup.PopupController',
        'Ext.form.field.Base',
        'Ext.button.Button'
    ],

    xtype: 'popupform',

    controller: 'popupform',

    width: '52%',

    height: '60%',

    floating: true,

    centered: true,

    modal: true,

    title: 'Link Management Form',

    items: [{
        xtype: 'form',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        bodyPadding: 10,
        border: false,
        items: [{
            name: 'URL',
            fieldLabel: 'Link',
            xtype: 'textfield',
            readOnly: 'true',
            flex: 1,
            bind: {
                html: '<p> <a href = \"{link.link}\"> {link.link} </a></p>'
            }
        }, {
            name: 'type',
            xtype: 'textfield',
            readOnly: 'true',
            fieldLabel: 'Source Type',
            flex: 1,
            bind: '{link.type}'
        }, {
            name: 'host',
            xtype: 'textfield',
            readOnly: 'true',
            fieldLabel: 'Host Site',
            flex: 1,
            bind: '{link.host}'
        }]
    }],
    buttons: [
        {
            xtype: 'button',
            text: 'Close',
            iconCls: 'x-fa fa-close',
            handler: 'cancelUpdate'
        }
    ]
});
