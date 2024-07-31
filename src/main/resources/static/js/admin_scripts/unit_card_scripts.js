const emptyCard = document.getElementById("empty__card").cloneNode(true);
const UNITS_IMAGES_PATH = "pictures/units";

document.addEventListener("DOMContentLoaded", (event) => {
    document.getElementById("add__card__btn").onclick = createNewCard;
    document.querySelectorAll(".unit__img__card").forEach(elem => {
        elem.querySelector("input").oninput = setImage;
    });
})

function createNewCard() {
    const addBtnContainer = document.getElementById("add__card__btn__container")
    const ajacentEmptyCard = addBtnContainer.insertAdjacentElement("beforebegin", emptyCard.cloneNode(true));
    ajacentEmptyCard.oninput = setImage;
}

const setImage = event => {
    const file = event.target.files[0];
    if (!file) {
        return;
    }
    const fileReader = new FileReader();
    const img = event.target.parentElement.querySelector("img");
    fileReader.onload = e => {
        img.src = e.target.result;
    }
    fileReader.readAsDataURL(file);
}

async function updateUnit(unit) {
    const newImages = await getImages(unit.id);
    const type = document.getElementById("unitType").value;
    const square = document.getElementById("unit__input__square").value;
    const totalPrice = document.getElementById("unit__input__totalPrice").value;
    const pricePerSqM = document.getElementById("unit__input__pricePerSqM").value;
    const rooms = document.getElementById("unit__input__rooms").value;
    const floor = document.getElementById("unit__input__floor").value;
    const totalFloors = document.getElementById("unit__input__totalFloors").value;
    const flatNumber = document.getElementById("unit__input__flat_number").value;
    const date = document.getElementById("unit__input__date").value;


    const updatedUnit = {
        id: unit.id,
        type,
        square,
        totalPrice,
        pricePerSqM,
        rooms,
        floor,
        totalFloors,
        flatNumber,
        date,
        address: unit.address,
        images: newImages
    }

    fetch("/admin/units/update-unit", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedUnit)
    }).then(response => {
        if (response.ok) {
            goToUnits();
        }
    });
}

async function getImages(unitId) {
    const listCard = document.querySelectorAll(".card__container input");
    const newImages = [];
    let i = 0;

    const uploadPromises = Array.from(listCard).map(async elem => {
        const file = elem.files[0];
        if (!file) {
            return null;
        }
        const imageFormData = new FormData();
        imageFormData.append("new-image", file, `unit-image-${unitId}-${i++}`);
        imageFormData.append("path", UNITS_IMAGES_PATH);
        imageFormData.append("timestamp", new Date().getTime());
        imageFormData.append("ext", file.name.split(".")[1]);

        const response = await fetch("/admin/units/image/save", {
            method: "POST",
            body: imageFormData
        });

        if (response.ok) {
            const text = await response.text();
            return { id: null, path: text };
        } else {
            console.error("Failed to upload image");
            return null;
        }
    });

    const results = await Promise.all(uploadPromises);
    results.forEach(image => {
        if (image) {
            newImages.push(image);
        }
    });

    return newImages;
}
