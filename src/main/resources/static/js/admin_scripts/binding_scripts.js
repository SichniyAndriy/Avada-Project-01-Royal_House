$( () => {
    $("#copy-btn").on("click", (event) => {
        const titleField = $("#title__field");
        const title = titleField.val();
        navigator.clipboard.writeText(title).then(
            () => console.log("Succesfully copied"), 
            (err) => console.error(err)
        );
        titleField.val("");
    })

    $("#save-btn").on("click", (event) => {
        const fieldList = [];
        $("input.title__field__item").each((index, item) => {
            fieldList.push(item.value);
        });

        fetch("/admin/binding/save", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fieldList)
        }).then(response => {
            if (response.ok) {
                alert("Дані збережено");
            }
        })
    })
})
