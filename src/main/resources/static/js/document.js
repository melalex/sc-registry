function DocumentCommand(parameters) {

    var {createDocumentUrl, updateDocument, updateMediaUrl, commitUrl, id} = parameters;

    var that = this;

    this.documentId = id;

    var createMedia = function () {
        var formData = new FormData();

        formData.append('id', that.documentId);
        formData.append('attachment', $('#attachment').get(0).files.get(0));

        $.ajax({
                   url: updateMediaUrl,
                   type: 'patch',
                   data: formData,
                   cache: false,
                   success: commit,
                   error: errorHandler.handle
               })
    };

    var commit = function () {
        $.ajax({
                   url: commitUrl,
                   type: 'patch',
                   cache: false,
                   success: function (data, status, request) {
                       window.location = request.getResponseHeader('Location')
                   },
                   error: errorHandler.handle
               })
    };

    var createDocument = function () {
        $.ajax({
                   url: createDocumentUrl,
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

    var editDocument = function () {
        $.ajax({
                   url: updateDocument,
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
        if (id) {
            editDocument()
        } else {
            createDocument()
        }
    };
}