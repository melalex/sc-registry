const CustomErrorHandler = function () {

    function handleError(jqXHR) {
        const problem = jqXHR.responseJSON;

        $('<div/>', {'class': 'alert alert-danger', 'role': 'alert'})
            .append(problem.detail)
            .append(
                $('<button/>', {'class': 'close', 'data-dismiss': 'alert', 'aria-label': 'Close'})
                    .append($('<span/>', {'aria-hidden': 'true'}).append('&times;'))
            )
            .appendTo('#errors');

        for (let error of problem.errors) {
            $(`input#${error.field}`)
                .after($('<div/>').addClass('invalid-feedback').append(error.message))
                .addClass('is-invalid ')
        }
    }

    return {
        handle: handleError
    }
};