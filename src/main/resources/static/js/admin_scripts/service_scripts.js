const SERVICE_PICTURES_PATH = "pictures/services";

const serviceBannerInput = document.getElementById("service-banner-input");
const servicePreviewInput = document.getElementById("service-preview-input");


serviceBannerInput.addEventListener("input", (event) => {
    const serviceBannerFile = event.target.files[0];
    if (serviceBannerFile) {
        const reader = new FileReader();
        reader.onload = (event) => {
            document.getElementById("service-banner").src = event.target.result;
        }
        reader.readAsDataURL(serviceBannerFile);
    }
})

servicePreviewInput.addEventListener("input", (event) => {
    const servicePreviewFile = event.target.files[0];
    if (servicePreviewFile) {
        const reader = new FileReader();
        reader.onload = (event) => {
            document.getElementById("service-preview").src = event.target.result;
        }
        reader.readAsDataURL(servicePreviewFile);
    } else {
        console.error('No file selected');
    }
})

async function updateService(service) {
    service.title = document.querySelector(".service-edit-name > input").value;
    service.description = document.querySelector(".service-edit-description > textarea").value;

    const serviceBannerFile = document.getElementById("service-banner-input").files[0];
    if (serviceBannerFile) {
        const bannerFormData = new FormData();
        bannerFormData.append("new-banner", serviceBannerFile, "service-banner");
        bannerFormData.append("ext", serviceBannerFile.name.split(".")[1]);
        bannerFormData.append("timestamp", new Date().getTime());
        bannerFormData.append("path", SERVICE_PICTURES_PATH);
        fetch("/admin/services/banner/save", {
            method: "POST",
            body: bannerFormData
        }).then(responce => {
            console.log(responce);
            return responce.text();
        }).then(
            data => console.log(data)
        )
    }

    const servicePreviewFile = document.getElementById("service-preview-input").files[0];
    if (servicePreviewFile) {
        const previewformData = new FormData();
        previewformData.append("new-preview", servicePreviewFile, `service_${service.id}`);
        previewformData.append("ext", servicePreviewFile.name.split(".")[1]);
        previewformData.append("timestamp", new Date().getTime());
        previewformData.append("path", `${SERVICE_PICTURES_PATH}/previews`);
        fetch("/admin/services/previews/save", {
            method: "POST",
            body: previewformData
        }).then(responce => {
            if (!responce.ok) {
                console.log("Responce not OK");
            }
            console.log(responce);
            return responce.text();
        }).then(data => {
            console.log(data)
            service.imagePath = data;
            console.log(service);
        })
    }

    await fetch("/admin/services/service-card/edit", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(service)
    }).then(responce => {
        if (!responce.ok) {
            throw new Error("Service update error" + responce.status);
        }
        return responce.json();
    }).then(data => {
        console.log("Service successfully update")
        goToServices();
    }).catch(error => {
        console.error("Error service update")
    });
}
