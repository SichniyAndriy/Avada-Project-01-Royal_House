$( () => {
    $("#copy-btn").on("click", (event) => {
        const titleField = $("#title__field");
        const title = titleField.val();
        console.log(title);
        navigator.clipboard.writeText(title).then(
            () => console.log("Succesfully copied"), 
            (err) => console.error(err)
        );
        titleField.val("");
    })

    $("#save-btn").on("click", (event) => {
        console.log(event);
        const fieldList = [];
        $("input.title__field__item").each((index, item) => {
            console.log("Item.value: " + item.value);
            fieldList.push(item.value);
        });

        console.log(fieldList);

        fetch("/admin/binding/save", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(fieldList)
        }).then(responce => {
            console.log(`Responce is ${responce.status}`);
        })
    })
})
