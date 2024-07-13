const allBannerCards = document.querySelectorAll(".banner-card");
const newbldsPanoramaInput = document.getElementById("newblds-panorama-input");
const newbldsPanorama = document.getElementById("newblds-panorama");
const bannersArr = new Array(10);

async function showTab(tabId) {
    document
        .querySelectorAll(".tab-content")
        .forEach(tab => (tab.style.display = "none"));
    document.getElementById(tabId).style.display = "block";
    document
        .querySelectorAll(".tab")
        .forEach(tab => tab.classList.remove("active-tab"));
    document
        .querySelector(`.tab[onclick="showTab('${tabId}')"]`)
        .classList.add("active-tab");
    if (tabId === "location") {
        showLocation();
    }
}

document.addEventListener("DOMContentLoaded", () => {
    showTab("main");
});

allBannerCards.forEach((card, i) => {
    card.children[3].addEventListener("input", (event) => {
        const bannerFile = event.target.files[0];
        if (bannerFile) {
            const reader = new FileReader();
            reader.onload = (event) => {
                card.children[1].src = event.target.result;
            }
            reader.readAsDataURL(bannerFile);
            bannersArr[i] = bannerFile;
        }
    })
})

newbldsPanoramaInput.addEventListener("input", event => {
    const panoramaFile = event.target.files[0];
    if (panoramaFile) {
        const reader = new FileReader();
        reader.onload = (event) => {
            newbldsPanorama.src = event.target.result;
        }
        reader.readAsDataURL(panoramaFile);
    }
})

async function addInfographic(type) {
    let inputPicture;
    let inputDescription;
    let infographicList;
    switch (type) {
        case 'MAIN':
            inputPicture = document.getElementById("main-infographic-input-picture");
            inputDescription = document.getElementById("main-infographic-input-description");
            infographicList = document.getElementById("main-infographic-list");
            break;
        case 'INFRASTRUCTURE':
            inputPicture = document.getElementById("infrastructure-infographic-input-picture");
            inputDescription = document.getElementById("infrastructure-infographic-input-description");
            infographicList = document.getElementById("infrastructure-infographic-list");
            break;
        case 'FLATS':
            inputPicture = document.getElementById("flats-infographic-input-picture");
            inputDescription = document.getElementById("flats-infographic-input-description");
            infographicList = document.getElementById("flats-infographic-list");
            break;
    }
    const file = inputPicture.files[0];
    const description = inputDescription.value;
    if (!file || !description) {
        alert("Введені дані не коректні");
        return;
    }
       
}

async function deleteInfographic(i) {
    const elem = document.querySelector(`button[onclick="deleteInfographic(${i})"]`).parentElement.parentElement.parentElement;
}

async function updateNewBld(newbld) {
    
}

async function setBanner(elem, newBldIndex, bannerIndex) {
    const bannerFile = elem.files[0];
    if (bannerFile) {
        const formData = new FormData();
        const bannerName = `banner_${newBldIndex}_${bannerIndex}.jpg`
        formData.append("new-banner", bannerFile, bannerName);
        formData.append("newbldIndex", newBldIndex);
        formData.append("bannerIndex", bannerIndex);
        fetch("/admin/new-blds/banners/set", {
            method: "POST",
            body: formData
        }).then(responce => {
            if (!responce.ok) {
                console.log("New Buildings main banner didn't save")
                return;
            }
            const reader = new FileReader();
            reader.onload = (event) => {
                document.getElementById("newblds-main-banner").src = event.target.result;
            }
            reader.readAsDataURL(bannerFile);
        })
    } else {
        console.error('No file selected');
    }
}

async function setPanorama(elem, newBldIndex) {
    const panoramaFile = elem.files[0];
    if (panoramaFile) {
        const panoramaName = `panorama_${newBldIndex}.jpg`
        const formData = new FormData();
        formData.append("new-panorama", panoramaFile, panoramaName);
        fetch("/admin/new-blds/panoramas/set", {
            method: "POST",
            body: formData
        }).then(responce => {
            if (!responce.ok) {
                console.log("New panorama didn't save")
                return;
            }
            const reader = new FileReader();
            reader.onload = event => {
                document.getElementById("newblds-panorama").src = event.target.result
            }
            reader.readAsDataURL(panoramaFile);
        })
    } else {
        console.error('No file selected');
    }
}
// ========================= LOCATION SCRIPTS ========================= \\

const map = L.map("map");
let marker;
let popup;

L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "© OpenStreetMap contributors"
}).addTo(map);

map.on("click", onMapClick);

function showLocation() {
    const lat = Number.parseFloat(document.getElementById("latitude").value);
    const lng = Number.parseFloat(document.getElementById("longitude").value);
    if (isNaN(lat) || isNaN(lng)) {
        alert("Помілкові координати");
    }
    if (marker) {
        map.removeLayer(marker);
    }
    marker = L.marker([lat, lng]).addTo(map);
    map.setView([lat, lng], 8);
}

function onMapClick(e) {
    popup = L.popup();
    popup
        .setLatLng(e.latlng)
        .setContent("Координати: " + e.latlng.toString())
        .openOn(map);
}
