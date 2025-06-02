$(".getReq").click( () => {
    $.ajax({
        url : "http://localhost:8080/app/todo",
        method : "GET",
        success: ((resp) => {
            alert("Got the Resp")
        })
    })
})