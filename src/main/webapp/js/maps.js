/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var selected = false;
var current_mark;
var map_ref;
var markers = [];

var heatmap;
var heatMapCoordinates = [];
var heatmap_activated = false;

function placeMarkerByLocation(location) {

    selected = true;

    if (current_mark != null)
        current_mark.setMap(null);

    var marker = new google.maps.Marker({
        position: location,
        map: map_ref,
        title: 'Hello World!',
        animation: google.maps.Animation.DROP,
        draggable: true
    });

    map_ref.setCenter(location);
    current_mark = marker;
    current_mark.setMap(map_ref);

    setCoordinateOnInputs();

}

function setCoordinateOnInputs() {
    if (document.getElementById("lat") != null) {
        document.getElementById("lat").value = current_mark.position.lat();
        document.getElementById("lng").value = current_mark.position.lng();
    }
}

function initialize() {
    var maps = google.maps;

    var myLatlng = new google.maps.LatLng(-6.8918042999999996, -38.5592176);
    var mapOptions = {
        zoom: 15,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        backgroundColor: "lightgrey",
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
            position: google.maps.ControlPosition.BOTTOM_LEFT
        }

    }

    var map = new google.maps.Map(document.getElementById("map"), mapOptions);
    map_ref = map;

    placeMarkerByLocation(myLatlng);

    google.maps.event.addListener(map, 'click', function (event) {
        placeMarkerByLocation(event.latLng);
    });

    function initSearchBox() {
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);

        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        map.addListener('bounds_changed', function () {
            searchBox.setBounds(map.getBounds());
        });


        searchBox.addListener('places_changed', function () {
            var places = searchBox.getPlaces();

            if (places.length === 0) {
                return;
            }

            var bounds = new google.maps.LatLngBounds();

            places.forEach(function (place) {
                if (place.geometry.viewport) {
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }

            });

            map.fitBounds(bounds);
        });
        loadAllMarks();
    }
    function initGeolocation() {
        var infoWindow = new google.maps.InfoWindow({map: map});

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                infoWindow.setPosition(pos);
                infoWindow.setContent('Você está aqui');
                map.setCenter(pos);
            }, function () {
                handleLocationError(true, infoWindow, map.getCenter());
            });
        } else {
            // Browser doesn't support Geolocation
            handleLocationError(false, infoWindow, map.getCenter());
        }
    }

    initGeolocation();
    initSearchBox();
}

function loadScript() {
    var myKey = "AIzaSyD4J2fNzE3EUAmCMz4XdphEg-d8ctudimA";
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "https://maps.googleapis.com/maps/api/js?key=" + myKey + "&sensor=false&callback=initialize&libraries=places";
    document.body.appendChild(script);
}

window.onload = loadScript;

function loadAllMarks() {
    setMapOnAllMarkers(null);
    var url = "http://localhost:8082/AlertaDor/GetAllMarksControll";
    loadMarks(url);
}

function setMapOnAllMarkers(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function loadMarks(url) {
    cleanMarks();
    cleanCoordinates();
    requestComplaints(function (data) {
        data.forEach(function (denuncia) {
            addCoordinate(new google.maps.LatLng(denuncia.location.coordinates.coordinates[0].x, denuncia.location.coordinates.coordinates[0].y));
            placeMarkerByJson(denuncia);
        });
    }, url);
}

function requestComplaints(successCallback, url) {
    $.ajax({
        type: "POST",
        url: url,
        success: successCallback,
        error: function (xhr, status) {
            console.log(status);
            console.log(xhr.responseText);
        }
    });
}

function cleanMarks() {
    while (markers.length > 0) {
        markers.pop();
    }
}

function cleanCoordinates() {
    while (heatMapCoordinates.length > 0) {
        heatMapCoordinates.pop();
    }
}

function addCoordinate(coordinate) {
    this.heatMapCoordinates.push(coordinate);
}

function placeMarkerByJson(json) {
    var location = new google.maps.LatLng(json.location.coordinates.coordinates[0].x, json.location.coordinates.coordinates[0].y);

    var pinColor = '8B008B';

    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
            new google.maps.Size(21, 34),
            new google.maps.Point(0, 0),
            new google.maps.Point(10, 34));

    var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
            new google.maps.Size(40, 37),
            new google.maps.Point(0, 0),
            new google.maps.Point(12, 35));

    var markOptions = {
        position: location,
        map: map_ref,
        title: json.descricao,
        icon: pinImage,
        shadow: pinShadow
    };

    var denuncia_content = "Descrição: " + json.description + " </br>";
    denuncia_content += "Tipo de Ocorrencia: " + json.squealerType + " </br>";
    if (json.anonymous) {
        denuncia_content += "Denunciante: Anonimo </br>";

    } else {
        denuncia_content += "Denunciante: " + json.squealer.name;
    }

    var marker_ref = placeMarkerByOptions(markOptions);

    var infoWindow = new google.maps.InfoWindow({
        content: denuncia_content
    });

    google.maps.event.addListener(marker, 'click', (function (marker, infowindow) {
        return function () {
            infowindow.open(map_ref, marker);
        };
    })(marker_ref, infoWindow));
}

function placeMarkerByOptions(markOptions) {
    marker = new google.maps.Marker(markOptions);
    addMarker(marker);
    return marker;
}

function addMarker(marker) {
    markers.push(marker);
}