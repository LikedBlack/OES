AUI().use(
    'node',
    function(A) {
        A.one('.input-group-btn > button').on(
            'click', function(event) {
                event.preventDefault();
                var keyword = A.one('div.input-group.taglib-input-search > input').get('value');
                A.one('div.form-group.input-text-wrapper > input').set('value', keyword);
                A.one('form.form').submit();
            }
        );
    }
);