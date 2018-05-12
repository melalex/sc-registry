$(document).ready(function () {
    const documentForm = $("#documentForm");
    const id = documentForm.data('document-id',);
    const documentCommand = new DocumentCommand(id);

    documentForm.submit(function (event) {
        event.preventDefault();
        documentCommand.perform()
    });
});

function DocumentCommand(id) {

    const that = this;

    this.documentId = id;
    this.customErrorHandler = new CustomErrorHandler();
    this.token = $("meta[name='_csrf']").attr("content");
    this.header = $("meta[name='_csrf_header']").attr("content");

    const createMedia = function () {
        const formData = new FormData();

        formData.append('attachment', $('#attachment').get(0).files.get(0));

        $.ajax({
            url: `/api/v1/documents/${that.documentId}/media`,
            type: 'patch',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(that.header, that.token);
            },
            data: formData,
            cache: false,
            success: commit,
            error: that.customErrorHandler.handle
        })
    };

    const commit = function () {
        $.ajax({
            url: `/${that.documentId}/commit`,
            type: 'patch',
            cache: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(that.header, that.token);
            },
            success: function (data, status, request) {
                window.location = request.getResponseHeader('Location')
            },
            error: that.customErrorHandler.handle
        })
    };

    const createDocument = function () {
        $.ajax({
            url: '/api/v1/documents',
            type: 'post',
            cache: false,
            contentType: 'application/json',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(that.header, that.token);
            },
            data: JSON.stringify({
                name: $('#name').val(),
                description: $('#description').val(),
                tags: $('#tags').val().split(" "),
                place: $('#place').val()
            }),
            success: function (data) {
                that.documentId = data.id;
                createMedia()
            },
            error: that.customErrorHandler.handle
        })
    };

    const editDocument = function () {
        $.ajax({
            url: '/api/v1/documents',
            cache: false,
            type: 'patch',
            contentType: 'application/json',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(that.header, that.token);
            },
            data: JSON.stringify({
                id: that.documentId,
                name: $('#name').val(),
                description: $('#description').val(),
                tags: $('#tags').val().split(" "),
                place: $('#place').val()
            }),
            success: function (data) {
                if (!data.attachment || $('#attachment').get(0).files.length) {
                    createMedia()
                }
            },
            error: that.customErrorHandler.handle
        })
    };

    this.perform = function () {
        if (that.documentId) {
            editDocument()
        } else {
            createDocument()
        }
    };
}