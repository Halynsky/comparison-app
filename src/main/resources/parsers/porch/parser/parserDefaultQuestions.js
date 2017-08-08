var getDefaultQuestion = function () {

    var question = {
        description : $(".smartFormV2-questionDescription")[0].innerHTML
    };

    var answers = [];

    $( ".smartFormV2-answer label" ).each(function( index, element ) {
        var answer = {
            description: element.innerHTML
        };

        answers.push(answer);

    });

    question.answers = answers;

    console.log(JSON.stringify(question));

}

