/**
 * This view is an example list of people.
 */
Ext.define('Scrapper.view.main.List', {
    extend: 'Ext.grid.Grid',
    xtype: 'mainlist',

    requires: [
        'Scrapper.store.Links'
    ],

    title: 'Personnel',

    store: {
        type: 'links'
    },

    columns: [{ 
        text: 'Name',
        dataIndex: 'name',
        width: 100,
        cell: {
            userCls: 'bold'
        }
    }, {
        text: 'Email',
        dataIndex: 'email',
        width: 230 
    }, { 
        text: 'Phone',
        dataIndex: 'phone',
        width: 150 
    }],

    listeners: {
        select: 'onItemSelected'
    }
});
