$(async () => {
    const response = await fetch("fragments/index.html");
    const text = await response.text();

    const parser = new DOMParser();
    const donorPage = parser.parseFromString(text, "text/html");

    const $donorPage = $(donorPage);
    const cloneSideContainer =  $donorPage.find("#side__nav__container").clone(true, true)[0];
    const cloneHeaderContainer = $donorPage.find("#main__header").clone(true, true)[0];

    $("#placeholder__side__nav__container").replaceWith(cloneSideContainer);
    $("#placeholder__main__header").replaceWith(cloneHeaderContainer);
});
