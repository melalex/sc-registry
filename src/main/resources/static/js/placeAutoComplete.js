$(window).load(function () {
    const place = $('#place');
    const placeSource = place.data('autocomplete-source');

    place.typeahead({
          source: function (query, callback) {
              $.get(placeSource, function (data) {
                  callback(JSON.parse(data).map(e => e.canonicalName))
              })
          }
    })
});
