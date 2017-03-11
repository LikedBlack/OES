AUI().use(
    'aui-io-request',
    'anim',
    function (A) {
        var resourceURL = A.one('input[name*=resourceURL]').get('value');
        var examId = A.one('input[name*=examId]').get('value');
        var studentId = A.one('input[name*=studentId]').get('value');

        /*var questionList = A.one('div.question-list');
        var questionListWidth = questionList.width();*/
        var prevQuestionButton = A.one('button[name*=prevQuestion]');
        var nextQuestionButton = A.one('button[name*=nextQuestion]');

        A.all('input[type="radio"]').on(
            'click',
            function(event) {
                var node = event.target;
                var result = node.get('value');
                var questionOrder = node.get('name').split('_').pop();
                A.io.request(resourceURL, {
                    method: 'post',
                    data: {
                        'examId': examId,
                        'studentId': studentId,
                        'questionOrder': questionOrder,
                        'result': result
                    }
                });
            }
        );

        prevQuestionButton.on(
            'click',
            function(e) {
            	prevQuestionButton.addClass('disabled');
            	nextQuestionButton.addClass('disabled');
            	
            	var questionList = A.one('div.question-list');
                var questionListWidth = questionList.width();
                var currentMarginLeft = 0 - questionList.getMargin('left');

                if (currentMarginLeft >= 0) {
                	prevQuestionButton.addClass('disabled');
                    return;
                }

                var targetMarginLeft = currentMarginLeft + 500;
                new A.Anim({
                    node: 'div.question-list',
                    duration: 1,
                    to: {
                        'margin-left': targetMarginLeft.toString(),
                    },
                    easing: A.Easing.bounceOut
                }).run();

                A.one('button[name*=nextQuestion]').removeClass('disabled');
                if (targetMarginLeft >= 0) {
                	prevQuestionButton.addClass('disabled');
                }
            }
        );

        nextQuestionButton.on(
            'click',
            function(e) {
            	prevQuestionButton.addClass('disabled');
            	nextQuestionButton.addClass('disabled');
            	
            	var questionList = A.one('div.question-list');
                var questionListWidth = questionList.width();
                var currentMarginLeft = 0 - questionList.getMargin('left');

                if (Math.abs(currentMarginLeft) >= questionListWidth - 500) {
                	nextQuestionButton.addClass('disabled');
                    return;
                }

                var targetMarginLeft = currentMarginLeft - 500;
                new A.Anim({
                    node: 'div.question-list',
                    duration: 1,
                    to: {
                        'margin-left': targetMarginLeft.toString(),
                    },
                    easing: A.Easing.bounceOut
                }).run();

                A.one('button[name*=prevQuestion]').removeClass('disabled');
                if (Math.abs(targetMarginLeft) >= questionListWidth - 500) {
                	nextQuestionButton.addClass('disabled');
                }
            }
        );

    }
);