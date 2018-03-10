/**
 * Created by tamuna on 03.08.2017.
 */
Ext.define('Scrapper.view.list.LinkList', {
    extend: 'Ext.grid.Panel',

    xtype: 'linklist',

    requires: [
        'Scrapper.store.Links',
        'Scrapper.view.list.LinkListController',
        'Ext.toolbar.Paging',
        'Ext.grid.filters.Filters'
    ],

    reference: 'linkGrid',

    frame: true,

    controller: 'linkGrid',

    title: 'Links',

    height: '100%',

    width: '100%',

    loadmask: true,

    emptyText: 'No Matching Records',

    listeners: {
        itemclick:
            function (view, record, item, index, eventObj) {
                var win = Ext.create('Scrapper.view.popup.Popup', {
                    record: record,
                    viewModel: {
                        data: {
                            link: record
                        }
                    }
                });
                win.show();
            }
    },

    store: {
        type: 'links',
        autoLoad: false,
        grouper: 'type',
        id: 'mainStore'
    },

    autoLoad: false,

    plugins: {
        gridfilters: true
    },

    columns: [{
        text: 'Link',
        dataIndex: 'link',
        filter: {
            type: 'string',
            itemDefaults: {
                emptyText: 'Search for...'
            }
        },
        flex: 1
    }, {
        text: 'Type',
        dataIndex: 'type',
        filter: 'list',
        flex: 1
    }, {
        text: 'Host Site',
        dataIndex: 'host',
        flex: 1
    }],

    bbar: {
        layout: {
            type: 'hbox',
            pack: 'center'
        },
        xtype: 'pagingtoolbar',
        displayMsg: 'Displaying topics {0} - {1} of {2}',
        emptyMsg: "No Links To Display"
    },

    tbar: [
        {
            id: "linkSearchField",
            width: '33.3%',
            xtype: "textfield",
            emptyText: 'URL To Search'
        },
        {
            id: 'typeSearhField',
            width: '33.3%',
            xtype: 'combobox',
            name: 'type',
            emptyText: 'Select Url Type',

            store:
                [
                    {text: "Picture Sources"},
                    {text: "Link Sources"},
                    {text: "Image and Link Sources"}
                ]
        },
        {
            xtype: 'button',
            width: '33.3%',
            iconCls: "fa fa-filter",
            text: "Filter By Selected Values",
            value: 'link',
            handler: 'onFilter'

        }
    ]
});