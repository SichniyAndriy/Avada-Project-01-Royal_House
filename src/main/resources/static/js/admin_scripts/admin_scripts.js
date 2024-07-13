/* =========================== BIDS =========================== */

const PATH_TO_BIDS = "/admin/bids";

function showBid(id) {
    window.location.href = `${PATH_TO_BIDS}/show/${id}`;
}
function deleteBid(id) {
    window.location.href = `${PATH_TO_BIDS}/delete/${id}`;
}
function changeBidStatus(id) {
    window.location.href = `${PATH_TO_BIDS}/change-bid-status/${id}`;
}

/* =========================== NEW BUILDINGS =========================== */

const PATH_TO_NEW_BUILDS = "/admin/new-blds";

function changeNewBldStatus(id) {
    window.location.href = `${PATH_TO_NEW_BUILDS}/change-new-bld-status/${id}`;
}

function showNewBld(id) {
    window.location.href = `${PATH_TO_NEW_BUILDS}/show-newbld-card/${id}`;
}

function deleteNewBld(id) {
    window.location.href = `${PATH_TO_NEW_BUILDS}/delete-new-bld/${id}`;
}

function goToNewBlds() {
    window.location.href = PATH_TO_NEW_BUILDS;
}

/* =========================== UNITS =========================== */

const PATH_TO_UNITS = "/admin/units";

function showUnit(id) {
    window.location.href = `${PATH_TO_UNITS}/show/${id}`;
}

/* =========================== COMPANY SERVICES =========================== */

const PATH_TO_SERVICES = "/admin/services";

function changeServiceStatus(id) {
    window.location.href = `${PATH_TO_SERVICES}/change-service-status/${id}`;
}

function showService(id) {
    window.location.href = `${PATH_TO_SERVICES}/service-card/${id}`;
}

function deleteService(id) {
    window.location.href = `${PATH_TO_SERVICES}/delete/${id}`;
}

function goToServices() {
    window.location.href = PATH_TO_SERVICES;
}