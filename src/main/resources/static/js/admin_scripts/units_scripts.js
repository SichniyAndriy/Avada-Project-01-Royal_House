let modalContent;
let form;

document.addEventListener("DOMContentLoaded", (event) => {
    document.getElementById("create__unit__btn").onclick = openUnitSaveModal;
    document.getElementById("reset__modal__form__btn").onclick = closeUnitSaveModal;
    document.getElementById("save__modal__form__btn").onclick = saveNewUnit;
    modalContent = document.getElementById("create__unit__modal__content");
    form = document.getElementById("create__unit__form");
})

function openUnitSaveModal() {
    modalContent.style.display = "block";
}

function closeUnitSaveModal() {
    modalContent.style.display =  "none";
}

function saveNewUnit() {
    const form = document.getElementById("create__unit__form");
    const formData = new FormData(form);

    fetch("/admin/units/add-new", {
        method: "POST",
        body: formData
    }).then(response => {
        if(response.ok) {
           alert("Новий об'єкт збережено");
           form.reset();
        } else {
            alert("Помилка");
            throw Error("Error creating unit")
        }
    })
}

function deleteUnit(id, pageNo) {
    fetch(`/admin/units/delete/${id}`, {
        method: "GET",   
    }).then(response => {
        if (response.ok) {
            goToUnits(pageNo);
        } else {
            throw Error(`Error deleting unit by id: ${id}`);
        }
    });
}
