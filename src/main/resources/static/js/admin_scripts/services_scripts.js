document.addEventListener("DOMContentLoaded", event => {
    document.getElementById("create__service__btn").onclick = openServiceSaveModal;
    document.getElementById("reset__modal__form__btn").onclick = closeServiceSaveModal;
    document.getElementById("save__modal__form__btn").onclick = saveService;
})

function openServiceSaveModal() {
    document.getElementById("create__service__modal__content").style.display = "block";
}

function closeServiceSaveModal() {
    document.getElementById("create__service__modal__content").style.display = "none";
}

function saveService() {
    const form = document.getElementById("create__service__form");
    const formData = new FormData(form);

    fetch("/admin/services/add-new", {
        method: "POST",
        body: formData
    }).then(responce => {
        if (responce.ok) {
            goToServices();
        } else {
            throw new Error("Error creating service");
        }
    })
}