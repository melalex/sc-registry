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

    const createMedia = function () {
        const formData = new FormData();

        formData.append('id', that.documentId);
        formData.append('attachment', $('#attachment').get(0).files.get(0));

        $.ajax({
                   url: `/api/v1/documents/${that.documentId}/media`,
                   type: 'patch',
                   data: formData,
                   cache: false,
                   success: commit,
                   error: errorHandler.handle
               })
    };

    const commit = function () {
        $.ajax({
                   url: `/${that.documentId}/commit`,
                   type: 'patch',
                   cache: false,
                   success: function (data, status, request) {
                       window.location = request.getResponseHeader('Location')
                   },
                   error: errorHandler.handle
               })
    };

    const createDocument = function () {
        $.ajax({
                   url: '/api/v1/documents',
                   type: 'post',
                   cache: false,
                   data: {
                       name: $('#name').val(),
                       description: $('#description').val(),
                       tags: $('#tags').val(),
                       place: $('#place').val()
                   },
                   success: function (data) {
                       that.documentId = data.id;
                       createMedia()
                   },
                   error: errorHandler.handle
               })
    };

    const editDocument = function () {
        $.ajax({
                   url: '/api/v1/documents',
                   cache: false,
                   type: 'patch',
                   data: {
                       id: that.documentId,
                       name: $('#name').val(),
                       description: $('#description').val(),
                       tags: $('#tags').val().split(" "),
                       place: $('#place').val()
                   },
                   success: function (data) {
                       if (!data.attachment || $('#attachment').get(0).files.length) {
                           createMedia()
                       }
                   },
                   error: errorHandler.handle
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