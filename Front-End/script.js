
$(".getReq").click( () => {
    $.ajax({
        url : "http://localhost:8080/app/todo",
        method : "GET",
        success : function (resp) {
            resp.map((todo) => {
                console.log(todo)
                // Create outer div
                const card = document.createElement('div');
                card.classList.add('card' , 'p-5');

                // Create h3 element
                const title = document.createElement('h3');
                title.classList.add('card-title');

                // Create paragraph element
                const description = document.createElement('p');
                description.classList.add('card-text');

                // Create time element
                const time = document.createElement('button');
                time.classList.add('card-btn' , 'btn', 'btn-primary');

                // Append inner elements to the outer div
                title.textContent = todo.name;
                description.textContent = todo.description;
                time.textContent = todo.time;

                card.appendChild(title);
                card.appendChild(description);
                card.appendChild(time);

                $(".todo-holder").append(card);
            })
        }
    })
});

// Save TODO
$("saveTodo").click(() => {
    let todoName =



    $.ajax({
        url: "http://localhost:8080/app/todo",
        method: "POST",
        contentType: 'application/json',
        data: JSON.stringify(),
        success: function () {
            console.log("OK");
        }
    })
});