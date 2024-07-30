document.addEventListener("DOMContentLoaded", async (event) => {
    const responce = await fetch("fragments/index.html");
    const text = await responce.text();
    const page = new DOMParser().parseFromString(text, "text/html");
    
    const sideContainer = page.querySelector(".side__container");
    if (sideContainer) {
        const clone = sideContainer.cloneNode(true);
        document.querySelector(".placeholder__side__container").replaceWith(clone);
    }

    const centralHeaderContainer = page.querySelector(".central__header__container");
    if (centralHeaderContainer) {
        const clone = centralHeaderContainer.cloneNode(true);
        document.querySelector(".placeholder__central__header__container").replaceWith(clone);
    }
})
