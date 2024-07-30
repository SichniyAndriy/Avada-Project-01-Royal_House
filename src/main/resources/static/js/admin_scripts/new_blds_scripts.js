document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("create__newbld__btn").onclick = openNewBldSaveModal;
    document.getElementById("reset__modal__form__btn").onclick = closeNewBldSaveModal;
    document.getElementById("save__modal__form__btn").onclick = saveNewBld;
});

function openNewBldSaveModal() {
    document.getElementById("create__newbld__modal__content").style.display = "block";
}

function closeNewBldSaveModal() {
    document.getElementById("create__newbld__modal__content").style.display = "none";
}

function saveNewBld() {
    const form = document.getElementById("create__newbld__form");
    const formData = new FormData(form);
    fetch("/admin/new-blds/add-new", {
        method: "POST",
        body: formData
    }).then(response => {
        if (response.ok) {
            goToNewBlds();
        } else {
            throw new Error("Error creating new building")
        }
    })
}