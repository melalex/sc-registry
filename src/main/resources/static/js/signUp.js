$(document).ready(function () {
    const userForm = $("#userForm");
    userForm.submit(function (event) {
        event.preventDefault();

        const url = userForm.data('create-url');

        $.ajax({
                   url: url,
                   type: 'post',
                   cache: false,
                   contentType: 'application/json',
                   dataType: 'json',
                   data: {
                       login: $('#login').val(),
                       password: $('#password').val(),
                       repeatPassword: $('#repeatPassword').val(),
                       firstName: $('#firstName').val(),
                       lastName: $('#lastName').val(),
                       position: $('#position').val()
                   },
                   success: function (data, status, request) {
                       window.location = request.getResponseHeader('Location')
                   },
                   error: errorHandler.handle
               })
    });
});
