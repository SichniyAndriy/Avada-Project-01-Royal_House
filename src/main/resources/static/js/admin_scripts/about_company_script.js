$( () => {
    $("#about-banner-input").on("input", (event) => {
        const bannerFile = event.target.files[0];
        if (bannerFile) {
            const fileReader = new FileReader();
            fileReader.onload = event => {
                $("#about-banner").attr("src", event.target.result)
            }
            fileReader.readAsDataURL(bannerFile);
        }
    })

    $("#save-btn").on("click", async event => {
        const title = $("#about-title-input").val();
        const desc = $("#about-description-input").val();
        const bannerInput = $("#about-banner-input")[0];
        let bannerUrl = "";
        const bannerFile = bannerInput.files[0];
        if (bannerFile) {
            const formData =  new FormData();
            formData.append("new-about-banner", bannerFile, "about-banner");
            formData.append("path", "pictures");
            formData.append("timestamp", new Date().getTime());
            formData.append("ext", bannerFile.name.split(".")[1]);
            const response = await fetch("/admin/about/save-banner", {
                method: "POST",
                body: formData
            });
            bannerUrl = await response.text(); 
        }

        console.log(bannerUrl);
        const formData = new FormData();
        formData.append("title", title);
        formData.append("desc", desc);
        if (bannerUrl) {
            formData.append("bannerUrl", bannerUrl);
        }
        
        fetch("/admin/about", {
            method: "POST",
            body: formData
        }).then(response => {
            if (response.ok) {
                alert("Дані збережено");
            }
            response => console.log(response.status);
        })
    })
})
