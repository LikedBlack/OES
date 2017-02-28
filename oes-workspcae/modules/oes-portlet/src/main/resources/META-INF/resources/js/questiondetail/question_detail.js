AUI().use(
    'aui-modal',
    function(A) {
        var createDialog = function(questionOrder, node) {
        var modal = new A.Modal(
            {
                bodyContent: 'Are you sure to delete Question ' + questionOrder + '?',
                centered: true,
                destroyOnHide: true,
                headerContent: '<h3>Confirm Dialog</h3>',
                modal: true,
                render: '#modal',
                visible: true,
                width: 450,
                resizable: false,
                toolbars: {
                    footer: [
                        {
                            label: 'Cancel',
                            on: {
                                click: function() {
                                    modal.hide();
                                }
                            }
                        },
                        {
                            label: 'Confirm',
                            on: {
                                click: function() {
                                    var href = node.getAttribute('href');
                                    window.location.href = href;
                                }
                            }
                        }
                    ]
                }
            }
        ).render().set('zIndex', Liferay.zIndex.ALERT);

        return modal;
        }

        A.all('a').on(
        		// TODO Selector
            'click',
            function(event) {
                event.preventDefault();
                var node = event.target;
                var questionOrder = "";
                var confirmDialog = createDialog(questionOrder, node);
                confirmDialog.show();
            }
        );
    }
);