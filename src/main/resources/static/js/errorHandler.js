const errorHandler = function () {

    function handleError(jqXHR) {
        const problem = JSON.parse(jqXHR.responseText);

        $('<div/>', {'class': 'alert alert-primary', 'role': 'alert'})
            .append(problem.detail)
            .appendTo('#errors');

        for (let error in problem.errors) {
            $(`input#${error.field}`)
                .after($('<div/>').addClass('invalid-feedback').append(error.message))
                .addClass('is-invalid ')
        }
    }

    return {
        handle: handleError
    }
};