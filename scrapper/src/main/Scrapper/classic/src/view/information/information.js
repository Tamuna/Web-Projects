Ext.define('Scrapper.view.information.information', {
    extend: 'Ext.form.Panel',

    xtype: 'information',

    title: 'Page Scrapper Application',

    width: '100%',

    height: '100%',

    frame: true,

    layout: {
        type: 'hbox',
        pack: 'center'
    },

    items: [{
        html: '<h4> You can input desired address in "Link Registration" page ' +
        'and then see all resource links used in it in "Links" page </h4> '
    }]
});
