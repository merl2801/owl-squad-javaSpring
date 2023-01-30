var map;
var marker = [];
var infoWindow = [];
var marks = {}
var centerPosition = { lat: 35.4604357464714, lng: 139.51415686131762 };
const zoom = 15
var radius = 1000
var geocoder


function setMarker(markerData) {

	// console.log(markerData);
	// console.log(markerData.length);

	//マーカー生成
	var sidebar_html = "";
	var icon;

	for (var i = 0; i < markerData.length; i++) {

		var latNum = parseFloat(markerData[i]['lat']);
		var lngNum = parseFloat(markerData[i]['lng']);

		// マーカー位置セット
		var markerLatLng = new google.maps.LatLng({
			lat: latNum,
			lng: lngNum
		});
		// マーカーアイコンのセット(行った所はアイコンを変える)
		if (markerData[i]['status'] === 'went') {
			icon = new google.maps.MarkerImage('./icon_color/went' + markerData[i]['classNo'] + '.png');
		} else {
			icon = new google.maps.MarkerImage('./icon_color/list' + markerData[i]['classNo'] + '.png');
		}
		// マーカーのセット
		marker[i] = new google.maps.Marker({
			position: markerLatLng,          // マーカーを立てる位置を指定
			map: map,                        // マーカーを立てる地図を指定
			icon: icon                       // アイコン指定
		});
		// 吹き出しの追加
		infoWindow[i] = new google.maps.InfoWindow({
			content: markerData[i]['class'] + '：' + markerData[i]['name'] + '<br><br>' + markerData[i]['text'] + '<br><br>' + markerData[i]['img']
		});
	}
}

const alphabets = () => {
	const c = 'A'.charCodeAt(0);
	const alphabets = Array.apply(null, new Array(26)).map((v, i) => {
		return String.fromCharCode(c + i)
	})
	return alphabets
}


const initMap = () => {
	map = new google.maps.Map(document.getElementById("map"), {
		center: centerPosition,
		zoom: zoom,
	})
	geocoder = new google.maps.Geocoder();
	setCenter(centerPosition)

}

const getRadius = () => {
	var radius = document.getElementById('radius').value
	return parseInt(radius)
}


const setCenter = (location) => {
	addMarker(location, 'X');
	addCircle(location)
}

function initialize() {
	var latlng = new google.maps.LatLng(35.681143, 139.767165);
	var myOptions = {
		zoom: 15,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
}

const resetCenter = () => {
	var address = document.getElementById('address').value
	if (!address) return
	geocoder.geocode({ address: address }, (results, status) => {
		if (status === google.maps.GeocoderStatus.OK) {
			var place = results[0]
			var latlng = {
				lat: place.geometry.location.lat(),
				lng: place.geometry.location.lng()
			}
			map = new google.maps.Map(document.getElementById("map"), {
				center: latlng,
				zoom: zoom,
			})
			setCenter(latlng)
		}
	})

}


const searchShops = () => {
	const markShops = (shops) => {
		shops.map((shop) => {
			const contentString = `<div id="content"><p><a href="/map/${shop.name_req}">${shop.name}</a></p></div>`;
			const infowindow = new google.maps.InfoWindow({ content: contentString, });

			const marker = new google.maps.Marker({
				position: { lat: shop.lat, lng: shop.lng },
				map,
				title: shop.name,
			});

			marker.addListener("click", () => {
				infowindow.open({
					anchor: marker,
					map,
					shouldFocus: false,
				});
			});
		})

	}


	var address = document.getElementById('address').value
	if (!address) return
	geocoder.geocode({ address: address }, (results, status) => {
		if (status === google.maps.GeocoderStatus.OK) {
						const url = "http://localhost:8088/userMarket/all"
						$.ajax({
							type: "get",
							url: url,
							dataType: "text",
						}).done((data) => {
							var shops = JSON.parse(data)
							console.log(shops)
							markShops(shops)
						});
		}
	})

    //ここは後で削除
	// shops = [
	// 	{ id: 1, name: 'ロピア希望が丘店', lat: 35.46032307715981, lng: 139.51543836737318 },
	// 	{ id: 2, name: 'ローゼン希望が丘店', lat: 35.45948082672923, lng: 139.51539818318685 },
	// 	{ id: 3, name: 'ライフ希望が丘店', lat: 35.46015752879825, lng: 139.51003677227706 },
	// ]
	// markShops(shops)

}


const addMarkers = () => {
	var index = 0
	for (id in marks) {
		var place = marks[id]
		var latlng = {
			lat: place.geometry.location.lat(),
			lng: place.geometry.location.lng()
		}
		addMarker(latlng, alphabets[index])
		index++
	}
	map.setCenter(latlng)
}

const addMarker = (location, label) => {
	new google.maps.Marker({
		position: location,
		label: label,
		map: map,
	});
}

const addCircle = (location) => {
	radius = getRadius()
	if (!radius) radius = 1000
	var options = {
		center: location,
		map: map,
		radius: radius,
		strokeColor: 'rgba(255, 0, 0, 0.5)',
		fillColor: 'rgba(255, 0, 0, 0.1)',
	}
	new google.maps.Circle(options);
}

function attachMessage(features, msg) {
	google.maps.event.addListener(features, 'click', function(event) {
		new google.maps.InfoWindow({
			content: msg
		}).open(features.getMap(), features);
	});
}


window.onload = (event) => {
	initMap()
}
