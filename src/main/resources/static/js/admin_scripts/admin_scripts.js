/* =========================== BIDS =========================== */

const PATH_TO_BIDS = "/admin/bids";

function showBid(id) {
    window.location.href = `${PATH_TO_BIDS}/show/${id}`;
}

function goToBids(pageNo = 0) {
    window.location.href = `${PATH_TO_BIDS}?page=${pageNo}`;
}

/* =========================== NEW BUILDINGS =========================== */

const PATH_TO_NEW_BUILDS = "/admin/new-blds";

function showNewBld(id) {
    window.location.href = `${PATH_TO_NEW_BUILDS}/show-newbld-card/${id}`;
}

function goToNewBlds(pageNo = 0) {
    window.location.href = `${PATH_TO_NEW_BUILDS}?page=${pageNo}`;
}

/* =========================== UNITS =========================== */

const PATH_TO_UNITS = "/admin/units";

function showUnit(id) {
    window.location.href = `${PATH_TO_UNITS}/show/${id}`;
}

function goToUnits(pageNo = 0) {
    window.location.href = `${PATH_TO_UNITS}?page=${pageNo}`;
}

/* =========================== COMPANY SERVICES =========================== */

const PATH_TO_SERVICES = "/admin/services";

function showService(id) {
    window.location.href = `${PATH_TO_SERVICES}/service-card/${id}`;
}

function goToServices(pageNo = 0) {
    window.location.href = `${PATH_TO_SERVICES}?page=${pageNo}`;
}
