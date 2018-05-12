$(document).ready(function () {
    const place = $('#place');
    const placeSource = place.data('autocomplete-source');

    const bloodhound = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.whitespace,
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: placeSource + '?name=%QUERY',
            wildcard: '%QUERY',
            filter: function (places) {
                return $.map(places.content, function (place) {
                    return {
                        value: place.canonicalName
                    };
                });
            }
        }
    });

    bloodhound.initialize();

    place.typeahead({
            hint: true,
            highlight: true,
            minLength: 2
        },
        {
            name: 'places',
            displayKey: 'value',
            source: bloodhound.ttAdapter()
        });
});
