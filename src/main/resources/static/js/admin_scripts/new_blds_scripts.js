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
            alert("Нова новобудова збережена");
            form.reset();
        } else {
            alert("Помилка");
            throw new Error("Error creating new building")
        }
    })
}

function changeNewBldStatus(id, pageNo) {
    fetch(`/admin/new-blds/change-new-bld-status/${id}`, {
        method: "GET"
    }).then(response => {
        if (response.ok) {
            goToNewBlds(pageNo);
        } else {
            throw Error(`Error changing status NewBld by id: ${id}`);
        }
    })
}

function deleteNewBld(id, pageNo) {
    fetch(`/admin/new-blds/delete/${id}`, {
        method: "GET",
    }).then(response => {
        if (response.ok) {
            goToNewBlds(pageNo);
        } else {
            throw Error(`Error deleting NewBld by id: ${id}`);
        }
    });
}
