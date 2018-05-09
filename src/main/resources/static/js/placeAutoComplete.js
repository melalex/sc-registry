$(window).load(function () {
    const place = $('#place');
    const placeSource = place.data('autocomplete-source');

    place.typeahead({
        source: function (query, callback) {
            $.get(placeSource, {name: query})
                .done(function (data) {
                    callback(data.content.map(e => e.canonicalName))
                })
        }
    })
});
