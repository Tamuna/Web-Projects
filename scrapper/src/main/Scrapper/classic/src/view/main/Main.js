/**
 * This class is the main view for the application. It is specified in app.js as the
 * "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('Scrapper.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'app-main',

    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'Scrapper.view.checkin.Checkin',
        'Scrapper.view.information.information',
        'Scrapper.view.list.LinkList',
        'Scrapper.view.main.MainController'
    ],
    
    controller: 'main',

    ui: 'navigation',

    tabBarHeaderPosition: 1,
    titleRotation: 0,
    tabRotation: 0,

    header: {
        layout: {
            align: 'stretchmax'
        },
        title: {
            text: "Page Scrapper",
            flex: 0
        },
        iconCls: 'fa fa-globe'
    },

    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        }
    },

    responsiveConfig: {
        tall: {
            headerPosition: 'top'
        },
        wide: {
            headerPosition: 'left'
        }
    },

    defaults: {
        bodyPadding: 0.5,
        tabConfig: {
            plugins: 'responsive',
            responsiveConfig: {
                wide: {
                    iconAlign: 'left',
                    textAlign: 'left'
                },
                tall: {
                    iconAlign: 'top',
                    textAlign: 'center',
                    width: 120
                }
            }
        }
    },

    id: "navigation_panel",
    items: [
        
        {
        	layout : 'center',
            title: 'Link Registration',
            iconCls: 'fa fa-external-link',
            items: [
                {
                    xtype: 'registrationForm'
                }]
        }, {
            title: 'Links',
            layout: 'center',
            iconCls: 'fa fa-bars',
            items: [
                {
                    xtype: 'linklist',
                    reference: 'currentGrid'
                }]
        },
        {
            title: 'Help',
            layout: 'center',
            iconCls: 'fa fa-info',
            items: [
                {
                    xtype: 'information'
                }
            ]
        }
    ]
});
