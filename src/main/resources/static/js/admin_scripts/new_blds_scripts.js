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

function deleteNewBld(id, pageNo) {
    fetch(`/admin/new-blds/delete/${id}`, {
        method: "GET",
        headers: { "Content-Type" : "application/json" },    
    }).then(response => {
        if (response.ok) {
            goToNewBlds(pageNo);
        } else {
            throw Error(`Error deleting NewBld by id: ${id}`);
        }
    });
}
