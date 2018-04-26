$("#documentForm").submit(function (event) {
    event.preventDefault();

    const url = $(this).attr('action');

    $.ajax({
               url: url,
               type: 'post',
               cache: false,
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
