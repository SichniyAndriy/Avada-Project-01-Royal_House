const NEW_BLDS_PICTURES_PATH = "pictures/new_blds";

const allBannerCards = document.querySelectorAll(".banner-card");
const newbldsPanoramaInput = document.getElementById("newblds-panorama-input");
const bannersArr = new Array(10);
const infographics = [];

document.addEventListener("DOMContentLoaded", () => {
    showTab("main");
    loadImages();
    // loadInfographics();
});

allBannerCards.forEach((card, i) => {
    card.children[3].addEventListener("input", (event) => {
        const bannerFile = event.target.files[0];
        if (!bannerFile) {
            return;
        }
        const reader = new FileReader();
        reader.onload = (event) => {
            card.children[1].src = event.target.result;
        }
        reader.readAsDataURL(bannerFile);
        bannersArr[i] = bannerFile;
    })
})

newbldsPanoramaInput.addEventListener("input", event => {
    const panoramaFile = event.target.files[0];
    if (!panoramaFile) {
        return;
    }
    const newbldsPanorama = document.getElementById("newblds-panorama");
    const reader = new FileReader();
    reader.onload = (event) => {
        newbldsPanorama.src = event.target.result;
    }
    reader.readAsDataURL(panoramaFile);
})

function showTab(tabId) {
    document
    .querySelectorAll(".tab-content")
    .forEach(tab => (tab.style.display = "none"));
    document.getElementById(tabId).style.display = "block";
    document
        .querySelectorAll(".active-tab")
        .forEach(tab => tab.classList.remove("active-tab"));
    document
        .querySelector(`.tab[onclick="showTab('${tabId}')"]`)
        .classList.add("active-tab");
    if (tabId === "location") {
        showLocation();
    }
}

function loadBanners() {
    allBannerCards.forEach(item => {
        const bannerPath = item.getElementsByTagName("img").src;
        fetch(bannerPath).then(response => {
            if(response.ok) {
                return response.blob();
            }
        })
    })
}

function loadInfographics() {
    const infoBlocks = document.querySelectorAll("li .infographic-info > div");
    infoBlocks.forEach(block => {
        infographics[block.children[0]] = {
            id : block.children[1],
            type : block.children[2],
            path : block.children[3],
            desc : block.children[4]
        }
    })
}

async function addInfographic(type, id) {
    let infographicList;
    let inputPicture;
    let inputDescription;
    switch (type) {
        case 'MAIN':
            infographicList = document.getElementById("main-infographic-list");
            inputPicture = document.getElementById("main-infographic-input-picture");
            inputDescription = document.getElementById("main-infographic-input-description");
            break;
        case 'INFRASTRUCTURE':
            infographicList = document.getElementById("infrastructure-infographic-list");
            inputPicture = document.getElementById("infrastructure-infographic-input-picture");
            inputDescription = document.getElementById("infrastructure-infographic-input-description");
            break;
        case 'FLATS':
            infographicList = document.getElementById("flats-infographic-list");
            inputPicture = document.getElementById("flats-infographic-input-picture");
            inputDescription = document.getElementById("flats-infographic-input-description");
            break;
    }
    const file = inputPicture.files[0];
    const description = inputDescription.value;
    if (!file || !description) {
        alert("Введені дані не коректні");
        return;
    }
    const index = infographics.length;
    const newInfographicPath = await setInfographic(file, id, index);
    infographics[index] = {
        path: newInfographicPath,
        description: description,
        type: type
    }

    const newListItem = document.createElement("li");
    newListItem.classList.add("infographic-info");
    newListItem.innerHTML = `<div>
                                <p><span>${newInfographicPath}</span></p>
                                <p><span>${description}</span></p>
                                <button onclick="deleteInfographic(${index})">
                                <img src="/img/admin/x-octagon-fill.svg" alt="Delete infographic">
                                </button>
                            </div>`;

    infographicList.appendChild(newListItem);
    inputPicture.files[0] = null;
    inputPicture.value = "";
    inputDescription.value = "";
}

async function deleteInfographic(i) {
    const elem = document.querySelector(`button[onclick="deleteInfographic(${i})"]`).parentElement.parentElement;
    const list = elem.parentElement;
    delete infographics[i];
    list.removeChild(elem);
}

async function setInfographic(infographicFile, newBldIndex, index) {
    if (infographicFile) {
        const infographicName = `infographic-${newBldIndex}-${index}`;
        const timestamp = new Date().getTime();
        const ext = infographicFile.name.split(".")[1];

        const formData = new FormData();
        formData.append("new-infographic", infographicFile, infographicName);
        formData.append("path", `${NEW_BLDS_PICTURES_PATH}/infographics`);
        formData.append("timestamp", timestamp);
        formData.append("ext", ext);

        const responce = await fetch("/admin/new-blds/infographics/add-new", {
            method: "POST",
            body: formData
        });

        if (responce.ok) {
            return responce.text();
        } else {
            console.log("New Buildings infographic didn't save")
            return null;
        }
    } else {
        console.error('No file selected');
    }
}

async function setBanner(bannerFile, newBldIndex, bannerIndex) {
    if (bannerFile) {
        const bannerName = `banner-${newBldIndex}-${bannerIndex}`;
        const timestamp = new Date().getTime();
        const ext = bannerFile.name.split(".")[1];

        const formData = new FormData();
        formData.append("new-banner", bannerFile, bannerName);
        formData.append("path", `${NEW_BLDS_PICTURES_PATH}/banners`);
        formData.append("timestamp", timestamp);
        formData.append("ext", ext);

        const responce = await fetch("/admin/new-blds/banners/add-new", {
            method: "POST",
            body: formData
        });

        if (responce.ok) {
            return responce.text();
        } else {
            console.log("New Buildings banner didn't save")
            return null;
        }
    } else {
        console.error('No file selected');
    }
}

async function setPanorama(panoramaFile, id) {
    if (panoramaFile) {
        const panoramaName = `panorama-${id}`;
        const timestamp = new Date().getTime();
        const ext = panoramaFile.name.split(".")[1];

        const formData = new FormData();
        formData.append("new-panorama", panoramaFile, panoramaName);
        formData.append("path", `${NEW_BLDS_PICTURES_PATH}/panoramas`);
        formData.append("timestamp", timestamp);
        formData.append("ext", ext);

        const responce = await fetch("/admin/new-blds/panoramas/add-new", {
            method: "POST",
            body: formData
        });

        if (responce.ok) {
            return responce.text();
        } else {
            console.log("New panorama didn't save")
            return "";
        }
    } else {
        console.error('No file selected');
    }
}

async function getLocation() {
    const latitude = document.getElementById("latitude").value;
    const longitude = document.getElementById("longitude").value;

    return {
        latitude: latitude,
        longitude: longitude
    }
}

async function getDescription() {
    const aboutDesc = document.getElementById("about-new-bld-description").value;
    const locationDesc = document.getElementById("location-new-bld-description").value;
    const infrastructureDesc = document.getElementById("infrastructure-new-bld-description").value;
    const flatsDesc = document.getElementById("flats-new-bld-description").value;

    return {
        about: aboutDesc,
        location: locationDesc,
        infrastructure: infrastructureDesc,
        flats: flatsDesc
    }
}

async function getAddress(id) {
    const fullAddress = document.getElementById("address-bld-input").value;
    const addressArr = fullAddress.split(",");
    for (let i = 0; i < addressArr.length; ++i) {
        addressArr[i] = addressArr[i].trim();
    }

    return {
        id: id,
        city: addressArr[0],
        street: addressArr[1],
        houseNumber: addressArr[2]
    }
}

async function updateNewBld(newbld) {
    const newId = newbld.id;
    const newTitle = document.getElementById("name-bld-input").value;
    const newLocation = await getLocation();
    const newStatus = newbld.status;
    const newDescription = await getDescription();
    const newAddress = await getAddress(newbld.id);
    const panoramaFile = newbldsPanoramaInput.files[0];
    let newPanoramaPath = newbld.panoramaPath;
    if (panoramaFile) {
        newPanoramaPath = await setPanorama(panoramaFile, newbld.id);
    }

    const newBanners = newbld.banners;
    for (let i = 0; i < bannersArr.length; ++i) {
        if (!newBanners[i]) {
            newBanners[i] = {
                id: null,
                path: null,
                newBuilding: null
            }
        }
        if (bannersArr[i]) {
            newBanners[i].path = await setBanner(bannersArr[i], newbld.id, i);
        }
    }

    const newInfographics = [];
    for (const infographic of infographics) {
        newInfographics.push(infographic);
    }

    const newUnits = [];
    for(const unit of newbld.units) {
        newUnits.push(unit);
    }

    const updatedNewBld = {
        id: newId,
        title: newTitle,
        location: newLocation,
        status: newStatus,
        description: newDescription,
        address: newAddress,
        panoramaPath: newPanoramaPath,
        banners: newBanners,
        infographics: newInfographics,
        units: newUnits
    }

    const responce = await fetch("/admin/new-blds/update-new-bld", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedNewBld)
    })

    if (responce.ok) {
        goToNewBlds();
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
