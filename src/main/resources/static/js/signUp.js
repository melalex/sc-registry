$(document).ready(function () {
    const userForm = $("#userForm");
    userForm.submit(function (event) {
        event.preventDefault();

        const url = userForm.data('create-url');
        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
                   url: url,
                   type: 'post',
                   cache: false,
                   contentType: 'application/json',
                   dataType: 'json',
                   beforeSend: function (xhr) {
                       xhr.setRequestHeader(header, token);
                   },
                   data: JSON.stringify({
                       login: $('#login').val(),
                       password: $('#password').val(),
                       repeatPassword: $('#repeatPassword').val(),
                       firstName: $('#firstName').val(),
                       lastName: $('#lastName').val(),
                       position: $('#position').val()
                   }),
                   success: function (data, status, request) {
                       window.location = request.getResponseHeader('Location')
                   },
                   error: new customErrorHandler().handle
               })
    });
});
