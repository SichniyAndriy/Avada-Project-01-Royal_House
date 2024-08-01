function deleteBid(id, pageNo) {
    fetch(`/admin/bids/delete/${id}`, {
        method: "GET",
        headers: { "Content-Type" : "application/json" },    
    }).then(response => {
        if (response.ok) {
            goToBids(pageNo);
        } else {
            throw Error(`Error deleting bid by id: ${id}`);
        }
    });
}
