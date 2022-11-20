function swtAlert(text, data, callback) {
    swal({
        title: "Are you sure?",
        text: text,
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((x) => {
        if (x) {
            callback(data)
        } else {
            swal("Your data is safe!");
        }
    })
}