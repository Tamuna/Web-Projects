/*
 * This file launches the application by asking Ext JS to create
 * and launch() the Application class.
 */
Ext.application({
    extend: 'Scrapper.Application',

    name: 'Scrapper',

    requires: [
        // This will automatically load all classes in the Scrapper namespace
        // so that application classes do not need to require each other.
        'Scrapper.*'
    ],

    // The name of the initial view to create.
    mainView: 'Scrapper.view.main.Main'
});
