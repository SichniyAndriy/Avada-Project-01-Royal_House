function changeBidStatus(id, pageNo) {
    fetch(`/admin/bids/change-bid-status/${id}`, {
        method: "GET"
    }).then(response => {
        if (response.ok) {
            goToBids(pageNo);
        } else {
            throw Error(`Error changing bid status by id: ${id}`);
        }
    });
}

function deleteBid(id, pageNo) {
    fetch(`/admin/bids/delete/${id}`, {
        method: "GET",
    }).then(response => {
        if (response.ok) {
            goToBids(pageNo);
        } else {
            throw Error(`Error deleting bid by id: ${id}`);
        }
    });
}
