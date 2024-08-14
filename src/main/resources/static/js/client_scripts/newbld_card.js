$(() => showLocation());

const map = L.map("map");

L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "© OpenStreetMap contributors"
}).addTo(map);

function showLocation() {
    const latitude = Number.parseFloat($("#latitude").text());
    const longitude = Number.parseFloat($("#longitude").text());

    if (isNaN(latitude) || isNaN(longitude)) {
        alert("Помілкові координати");
    }

    L.marker([latitude, longitude]).addTo(map);
    map.setView([latitude, longitude], 8);
}