const PATH_TO_NEW_BUILDS = "/new-blds";

function showNewBld(id) {
    if (id === 0) {
        return;
    }
    window.location.href = `${PATH_TO_NEW_BUILDS}/show-newbld-card/${id}`;
}
