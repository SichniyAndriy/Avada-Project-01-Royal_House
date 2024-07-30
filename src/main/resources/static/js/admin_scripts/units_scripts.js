let modalContent;
let form;

document.addEventListener("DOMContentLoaded", (event) => {
    document.getElementById("create__unit__btn").onclick = openUnitSaveModal;
    document.getElementById("reset__modal__form__btn").onclick = closeUnitSaveModal;
    document.getElementById("save__modal__form__btn").onclick = saveNewUnit;
    modalContent = document.getElementById("create__unit__modal__content");
    form = document.getElementById("create__unit__form");
})

function deleteUnit(id) {
    fetch(`/admin/units/delete/${id}`, {
        method: "GET",
        headers: { "Content-Type" : "application/json" },    
    }).then(response => {
        if (response.ok) {
            goToUnits();
        } else {
            throw Error(`Error deleting unit by id: ${id}`);
        }
    });
}

function openUnitSaveModal() {
    modalContent.style.display = "block";
}

function closeUnitSaveModal() {
    modalContent.style.display =  "none";
}

c