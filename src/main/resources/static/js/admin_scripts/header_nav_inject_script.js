document.addEventListener("DOMContentLoaded", async () => {
    try {
        const response = await fetch("fragments/navbar.html");
        const text = await response.text();

        const parser = new DOMParser();
        const page = parser.parseFromString(text, "text/html");

        const headContainer = page.getElementById("head-container");
        if (!headContainer) {
            console.error("head-container not found in the fetched HTML");
            return;
        }
        const navBlock = page.getElementById("nav-block");
        if(!navBlock) {
            console.error("nav-block not found in the fetched HTML");
            return;
        }

        const clonedHeadContainer = headContainer.cloneNode(true);
        const clonedNavBlock = navBlock.cloneNode(true);

        document.getElementById("header").appendChild(clonedHeadContainer);
        document.getElementById("nav").replaceChild(clonedNavBlock, document.getElementById("nav-block"));
    } catch (error) {
        console.error("Error fetching or processing navbar.html:", error);
    }
});

